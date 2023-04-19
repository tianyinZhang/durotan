package com.tianyin.lottery.domain.strategy.model.aggregates;

import com.tianyin.lottery.infrastructure.po.Strategy;
import com.tianyin.lottery.infrastructure.po.StrategyDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 策略聚合类
 */
@Data
@AllArgsConstructor
public class StrategyRich {

    // 策略ID
    private Long strategyId;

    // 策略配置
    private Strategy strategy;

    // 策略明细
    private List<StrategyDetail> strategyDetailList;

}
