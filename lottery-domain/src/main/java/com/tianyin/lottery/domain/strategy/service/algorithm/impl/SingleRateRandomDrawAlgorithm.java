package com.tianyin.lottery.domain.strategy.service.algorithm.impl;

import com.tianyin.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * SingleRateRandomDrawAlgorithm
 * 当部分奖品已经送出时，其他奖品的抽中概率不变
 * 即随机到已经送出的奖品时，返回“未中奖”
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 获取抽奖策略所对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int randomVal = this.generateSecureRandomIntCode(100);
        int idx = super.hashIdx(randomVal);

        // 判断是否被排除，并返回结果
        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) return null;

        return awardId;

    }

}
