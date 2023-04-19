package com.tianyin.lottery.domain.strategy.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 绘制结果对象
 */
@Data
@AllArgsConstructor
public class DrawResult {

    // 用户ID
    private String uId;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private String awardId;

    // 奖品名称
    private String awardName;

}
