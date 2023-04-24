package com.tianyin.lottery.domain.strategy.service.draw;

import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @description: 提供抽奖策略数据支持，便于查询策略配置、奖品信息，实现职责隔离
 * @author：Tianyin Zhang
 * @date: 2023/4/20
 */
public class DrawStrategySupport extends DrawConfig {

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }

}
