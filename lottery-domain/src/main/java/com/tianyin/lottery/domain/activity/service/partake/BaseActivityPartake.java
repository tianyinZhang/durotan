package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.res.PartakeResult;
import com.tianyin.lottery.domain.activity.model.vo.ActivityBillVO;

/**
 * @description: 活动领取抽象类
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Override
    public PartakeResult doPartake(PartakeReq req) {

        // 1. 查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 2. 活动信息校验处理（活动库存、状态、日期、个人参与次数）
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 3. 扣减活动库存（目前直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减）
        Result subResult = subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subResult.getCode())) {
            return new PartakeResult(subResult.getCode(), subResult.getInfo());
        }

        // 4. 领取活动信息（个人用户把活动信息写入到用户表）
        Result grabResult = grabActivity(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        // 5. 封装结果（返回策略ID，用于继续完成抽奖步骤）
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(activityBillVO.getStrategyId());
        return partakeResult;
    }

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
    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO bill);

}
