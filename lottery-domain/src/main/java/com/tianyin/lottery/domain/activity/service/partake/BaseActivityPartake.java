package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.res.PartakeResult;
import com.tianyin.lottery.domain.activity.model.vo.ActivityBillVO;
import com.tianyin.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.tianyin.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动领取抽象类
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeResult doPartake(PartakeReq req) {

        // 1. 查询是否存在未执行抽奖领取的活动单
        //    表 user_take_activity 存在 state = 0， 领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getUId());
        if (null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId());
        }

        // 2. 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 3. 活动信息校验处理（活动库存、状态、日期、个人参与次数）
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 4. 扣减活动库存（目前直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减）
        Result subResult = subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subResult.getCode())) {
            return new PartakeResult(subResult.getCode(), subResult.getInfo());
        }

        // 5. 插入领取活动信息（个人用户把活动信息写入到用户表）
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = grabActivity(req, activityBillVO, takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        return buildPartakeResult(activityBillVO.getStrategyId(), takeId);
    }

    /**
     * 封装结果
     *
     * @param strategyId 策略ID
     * @param takeId     领取ID
     * @return           封装结果
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId) {
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }

    /**
     * 查询未执行抽奖领取的活动单
     *
     * @param activityId 活动ID
     * @param uId        用户ID
     * @return           领取单
     */
    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    /**
     * 活动信息校验处理（活动库存、状态、日期、个人参与次数）
     *
     * @param req   参与活动请求
     * @param bill  活动账单
     * @return      校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq req, ActivityBillVO bill);

    /**
     * 扣减活动库存
     *
     * @param req   活动参与请求
     * @return      扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 领取活动
     *
     * @param req   活动参与请求
     * @param bill  活动账单
     * @return      领取结果
     */
    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO bill, Long takeId);

}
