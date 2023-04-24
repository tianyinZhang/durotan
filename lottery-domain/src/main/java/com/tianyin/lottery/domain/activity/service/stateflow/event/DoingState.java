package com.tianyin.lottery.domain.activity.service.stateflow.event;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @description: 活动执行状态类
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
@Component
public class DoingState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可审核撤销");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess = activityRepository.alterStatus(activityId, currentState, Constants.ActivityState.CLOSE);
        return isSuccess ? Result.buildSuccessResult("活动关闭：成功") : Result.buildErrorResult("活动关闭：失败");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildErrorResult("活动执行状态不可执行活动");
    }

}
