package com.tianyin.lottery.domain.strategy.repository;

import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.infrastructure.po.Award;

/**
 * 策略仓储类，获取策略聚合根 & 奖品信息
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);

}
