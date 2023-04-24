package com.tianyin.lottery.domain.activity.service.stateflow;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;

/**
 * @description: 活动状态处理接口
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
public interface IStateHandler {

    /**
     * 活动提审
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核通过
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核拒绝
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 撤审撤销
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动关闭
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result close(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动开启
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result open(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动执行
     *
     * @param activityId    活动ID
     * @param currentState  当前状态
     * @return              执行结果
     */
    Result doing(Long activityId, Enum<Constants.ActivityState> currentState);

}
