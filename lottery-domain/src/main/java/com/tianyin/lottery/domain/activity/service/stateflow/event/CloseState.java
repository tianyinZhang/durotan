package com.tianyin.lottery.domain.activity.service.stateflow.event;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 活动关闭状态类
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
@Component
public class CloseState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可审核撤销");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可关闭");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.OPEN);
        return isSuccess ? Result.buildSuccessResult("活动开启：成功") : Result.buildErrorResult("活动开启：失败");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动关闭状态不可执行活动中变更");
    }

}
