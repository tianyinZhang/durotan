package com.tianyin.lottery.domain.activity.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 策略详情配置
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
public class StrategyDetailVO {

    /** 策略ID */
    private Long strategyId;

    /** 奖品ID */
    private String awardId;

    /** 奖品名称 */
    private String awardName;

    /** 奖品数量 */
    private Integer awardCount;

    /** 奖品剩余数量 */
    private Integer awardSurplusCount;

    /** 中奖概率 */
    private BigDecimal awardRate;


}
