package com.tianyin.lottery.domain.strategy.repository.impl;

import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;
import com.tianyin.lottery.infrastructure.dao.IAwardDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDetailDao;
import com.tianyin.lottery.infrastructure.po.Award;
import com.tianyin.lottery.infrastructure.po.Strategy;
import com.tianyin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId, strategy, strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {
        return awardDao.queryAwardInfo(awardId);
    }

}
