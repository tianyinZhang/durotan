package com.tianyin.lottery.domain.strategy.service.draw.impl;

import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.domain.strategy.model.req.DrawReq;
import com.tianyin.lottery.domain.strategy.model.res.DrawResult;
import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.tianyin.lottery.domain.strategy.service.draw.DrawBase;
import com.tianyin.lottery.domain.strategy.service.draw.IDrawExec;
import com.tianyin.lottery.infrastructure.po.Award;
import com.tianyin.lottery.infrastructure.po.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Slf4j
@Service("drawExec")
public class DrawExecImpl extends DrawBase implements IDrawExec {

    @Resource
    IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExecute(DrawReq req) {

        // 1. 打印日志
        log.info("执行抽奖 用户id：{}， 策略ID：{}", req.getUId(), req.getStrategyId());

        // 2. 获取相关数据用于初始化
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());

        // 3. 完成初始化
        checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 4. 根据选定的策略方式抽奖
        String awardId = drawAlgorithm.randomDraw(req.getStrategyId(), new ArrayList<>());

        // 5. 获取奖品信息并打印日志
        Award award = strategyRepository.queryAwardInfo(awardId);
        log.info("中奖用户：{}, 奖品ID：{}, 奖品名称：{}", req.getUId(), award.getAwardId(), award.getAwardContent());

        // 6. 封装结果
        return new DrawResult(req.getUId(), strategy.getStrategyId(), award.getAwardId(), award.getAwardName());

    }


}
