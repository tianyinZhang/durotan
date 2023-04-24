package com.tianyin.lottery.domain.activity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 变更活动状态
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
@AllArgsConstructor
public class AlterStateVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 更新前状态
     */
    private Integer beforeState;

    /**
     * 更新后状态
     */
    private Integer afterState;

}
