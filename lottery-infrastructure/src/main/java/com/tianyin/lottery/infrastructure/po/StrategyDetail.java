package com.tianyin.lottery.infrastructure.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 策略明细的持久化对象
 */
@Data
public class StrategyDetail {

    // 自增ID
    private String id;

    // 策略ID
    private Long strategyId;

    // 奖品ID
    private String awardId;

    // 奖品名称
    private String awardName;

    // 奖品数量
    private String awardCount;

    // 奖品剩余数量
    private Integer awardSurplusCount;

    // 中奖概率
    private BigDecimal awardRate;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

}
