package com.tianyin.lottery.domain.strategy.service.draw;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 配置抽奖策略
 * @author：Tianyin Zhang
 * @date: 2023/4/18
 */
public class DrawConfig {

    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(Constants.StrategyMode.ENTIRETY.getCode(), entiretyRateRandomDrawAlgorithm);
    }

}
