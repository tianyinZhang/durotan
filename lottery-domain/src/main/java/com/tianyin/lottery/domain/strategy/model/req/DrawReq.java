package com.tianyin.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 绘制请求对象
 */
@Data
@AllArgsConstructor
public class DrawReq {

    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

}
