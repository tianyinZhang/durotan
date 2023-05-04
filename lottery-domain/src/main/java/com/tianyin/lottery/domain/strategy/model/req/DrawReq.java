package com.tianyin.lottery.domain.strategy.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 绘制请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrawReq {

    /** 用户ID */
    private String uId;

    /** 策略ID */
    private Long strategyId;

    /** 防重 ID */
    private String uuid;

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }
}
