package com.tianyin.lottery.domain.activity.service.stateflow.impl;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.service.stateflow.IStateHandler;
import com.tianyin.lottery.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @description: 状态处理服务
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).arraignment(activityId, currentState);
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).checkPass(activityId, currentState);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).checkRefuse(activityId, currentState);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).checkRevoke(activityId, currentState);
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).close(activityId, currentState);
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).open(activityId, currentState);
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return stateGroup.get(currentState).doing(activityId, currentState);
    }

}
