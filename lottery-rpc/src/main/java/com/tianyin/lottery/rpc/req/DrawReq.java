package com.tianyin.lottery.rpc.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 抽奖请求
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawReq implements Serializable {

    /** 用户ID */
    private String uId;

    /** 活动ID */
    private Long activityId;

}
