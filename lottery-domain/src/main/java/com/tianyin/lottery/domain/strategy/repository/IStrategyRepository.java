package com.tianyin.lottery.domain.strategy.repository;

import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.infrastructure.po.Award;

import java.util.List;

/**
 * 策略仓储类，获取策略聚合根 & 奖品信息
 */
public interface IStrategyRepository {

    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);

    /**
     * 查询策略下没有库存的奖品列表
     * @param strategyId 策略ID
     * @return 没有库存的奖品ID列表
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @return 扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);

}
