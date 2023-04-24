package com.tianyin.lottery.domain.strategy.model.aggregates;

import com.tianyin.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.tianyin.lottery.domain.strategy.model.vo.StrategyBriefVO;
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
    private StrategyBriefVO strategy;

    // 策略明细
    private List<StrategyDetailVO> strategyDetailList;

}
