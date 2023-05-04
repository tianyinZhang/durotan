package com.tianyin.lottery.application.process.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 抽奖请求
 * @author：Tianyin Zhang
 * @date: 2023/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawProcessReq {

    /** 用户ID */
    private String uId;

    /** 活动ID */
    private Long activityId;

}
