package com.tianyin.lottery.domain.activity.model.vo;

import lombok.Data;

/**
 * @description: 用户领取活动记录
 * @author：Tianyin Zhang
 * @date: 2023/5/1
 */
@Data
public class UserTakeActivityVO {

    /** 活动ID */
    private Long activityId;

    /** 活动领取ID */
    private Long takeId;

    /** 策略ID */
    private Long strategyId;

    /** 活动单使用状态 0未使用、1已使用
     * Constants.TaskState
     */
    private Integer state;

}
