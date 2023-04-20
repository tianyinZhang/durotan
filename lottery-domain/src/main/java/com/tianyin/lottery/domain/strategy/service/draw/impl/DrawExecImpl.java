package com.tianyin.lottery.domain.strategy.service.draw.impl;

import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.tianyin.lottery.domain.strategy.service.draw.AbstractDrawBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("drawExec")
public class DrawExecImpl extends AbstractDrawBase {

    @Resource
    IStrategyRepository strategyRepository;

    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String> awardList = strategyRepository.queryNoStockStrategyAwardList(strategyId);
        log.info("执行抽奖策略 strategyId：{} 排除的奖品ID列表：{}", strategyId, awardList);
        return awardList;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {

        // 1. 执行抽奖策略
        String awardId = drawAlgorithm.randomDraw(strategyId, excludeAwardIds);

        // 2. 判断抽奖结果
        if (null == awardId) return null;

        // 3. 若抽奖成功，扣减对应奖品库存
        // 先采用数据库行级锁（通常不满足并发需求） 后续优化为 Redis 分布式锁
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        return isSuccess ? awardId : null;

    }


}
