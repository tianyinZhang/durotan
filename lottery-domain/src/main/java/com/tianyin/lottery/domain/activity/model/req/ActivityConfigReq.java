package com.tianyin.lottery.domain.activity.model.req;

import com.tianyin.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 活动配置请求对象
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityConfigReq {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动配置信息
     */
    private ActivityConfigRich activityConfigRich;

}
