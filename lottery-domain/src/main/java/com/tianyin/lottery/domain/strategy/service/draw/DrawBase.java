package com.tianyin.lottery.domain.strategy.service.draw;

import com.tianyin.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.tianyin.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

public class DrawBase extends DrawConfig{

    /**
     * 若采用单项概率，需初始化散射表
     * @param strategyId 数据库中策略对应的ID
     * @param strategyMode 人为规定的映射
     * @param strategyDetailList 策略细节List
     */
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {

        // 判断是否为默认方式，若默认方式无需初始化
        if (strategyMode != 1) return;

        // 获取对应的抽奖算法实现类
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        // 判断是否已经初始化过
        if (drawAlgorithm.isExistRateTuple(strategyId)) return;

        // 将 strategyDetailList 转换为 awardRateInfoList 实现初始化
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList)
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);

    }





}
