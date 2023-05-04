package com.tianyin.lottery.domain.activity.service.partake.impl;

import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.vo.ActivityBillVO;
import com.tianyin.lottery.domain.activity.model.vo.DrawOrderVO;
import com.tianyin.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.tianyin.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.tianyin.lottery.domain.activity.service.partake.BaseActivityPartake;
import com.tianyin.lottery.domain.support.ids.IIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动参与功能实现
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
@Slf4j
@Service
public class ActivityPartakeImpl extends BaseActivityPartake {

    @Resource
    private IDBRouterStrategy dbRouter;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;

    @Resource
    private Map<Constants.Ids, IIdGenerator> iIdGenerator;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq req, ActivityBillVO bill) {
        // 1. 校验活动状态
        if (!Constants.ActivityState.DOING.getCode().equals(bill.getState())) {
            log.warn("当前活动状态非可用 state: {}", bill.getState());
            return Result.buildErrorResult("当前活动状态非可用");
        }

        // 2. 校验活动日期
        if (req.getPartakeDate().before(bill.getBeginDateTime()) || req.getPartakeDate().after(bill.getEndDateTime())) {
            log.warn("请求参与日期未处于活动时间范围 beginDateTime: {} endDateTime: {}", bill.getBeginDateTime(), bill.getEndDateTime());
            return Result.buildErrorResult("请求参与日期未处于活动时间范围");
        }

        // 3. 校验活动库存
        if (bill.getStockSurplusCount() <= 0) {
            log.warn("活动剩余库存不足 stockSurplusCount: {}", bill.getStockSurplusCount());
            return Result.buildErrorResult("活动剩余库存不足");
        }

        // 4. 校验当前活动个人剩余可领取次数（避免一个用户在一个活动中领取过多奖品）
        if (bill.getUserTakeLeftCount() <= 0) {
            log.warn("个人领取次数不足 userTakeLeftCount: {}", bill.getUserTakeLeftCount());
            return Result.buildErrorResult("个人领取次数不足");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        if (0 == count) {
            log.error("扣减活动库存失败 activityId: {}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }

    @Override
    protected Result grabActivity(PartakeReq req, ActivityBillVO bill, Long takeId) {
        // 使用事务获取活动（扣减个人剩余可参与次数、插入活动领取信息）
        try {
            dbRouter.doRouter(req.getUId());
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人剩余可参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(),
                            bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), bill.getUId(), req.getPartakeDate());
                    if (0 == updateCount) {
                        status.setRollbackOnly();
                        log.error("领取活动，扣减个人已参与次数失败 activityId: {} uId: {}", req.getActivityId(), req.getUId());
                        return Result.buildNoUpdateResult();
                    }

                    // 插入活动领取信息
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(),bill.getStrategyId(),
                            bill.getTakeCount(), bill.getUserTakeLeftCount(), bill.getUId(), req.getPartakeDate(), takeId);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("领取活动，唯一索引冲突 activityId: {} uId: {}", req.getActivityId(), req.getUId());
                    return Result.buildNoUpdateResult();
                }
                return Result.buildSuccessResult();
            });
        } finally {
             dbRouter.clear();
        }
    }

    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getUId());
            return transactionTemplate.execute(status -> {
                try {
                    // 锁定活动领取记录
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getUId(), drawOrder.getActivityId(), drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        log.error("记录中奖单，个人参与活动抽奖已消耗完 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getUId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 保存抽奖信息
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    log.error("记录中奖单，唯一索引冲突 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getUId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
            dbRouter.clear();
        }
    }

}
