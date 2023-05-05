package com.tianyin.lottery.domain.strategy.service.algorithm.impl;

import com.tianyin.lottery.domain.strategy.model.vo.AwardRateVO;
import com.tianyin.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * EntiretyRateRandomDrawAlgorithm
 * 当部分奖品已经送出时，提升剩余奖品的中奖概率，使得用户在奖池被抽空前的中奖概率是一致的
 */
@Component("entiretyRateRandomDrawAlgorithm")    // IDrawAlgorithm 有多个实现，便于Resource注入
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        // 初始化一个BigDecimal类型的变量differenceDenominator，用于记录所有在抽奖范围内的奖品的抽奖概率之和
        BigDecimal differenceDenominator = new BigDecimal(0);

        // 初始化剩余奖品集合，同时计算初始化一个 List<AwardRateInfo> 类型的变量 differenceAwardRateList
        // 遍历对应策略的awardRateIntervalValList，若奖品ID在排除列表中则跳过
        // 否则，将awardRateInfo加入到differenceAwardRateList列表中，并将其抽中概率加入到differenceDenominator中
        List<AwardRateVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateVO awardRateVO : awardRateIntervalValList) {
            if (excludeAwardIds.contains(awardRateVO.getAwardId())) {
                continue;
            }
            differenceAwardRateList.add(awardRateVO);
            differenceDenominator = differenceDenominator.add(awardRateVO.getAwardRate());
        }

        // 前置判断：奖池已空 或 只剩一个奖品的情况
        if (differenceAwardRateList.size() == 0) return null;
        if (differenceAwardRateList.size() == 1) return differenceAwardRateList.get(0).getAwardId();

        // 获取随机数
        int randomVal = this.generateSecureRandomIntCode(100);

        // 执行类似于初始化元祖的操作，区别在于：
        // 1. 每个概率处以剩余总概率，实现剩余奖品总获奖率为1
        // 2. 无需保存元祖，与随机数匹配时直接返回
        String awardId = null;
        int cursorVal = 0;
        for (AwardRateVO awardRateVO : differenceAwardRateList) {
            int rateVal = awardRateVO.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP)
                    .multiply(new BigDecimal(100)).intValue();
            if (randomVal <= cursorVal + rateVal) {
                awardId = awardRateVO.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        return awardId;

    }


}
