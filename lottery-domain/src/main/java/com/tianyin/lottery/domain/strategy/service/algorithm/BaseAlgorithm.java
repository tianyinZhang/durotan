package com.tianyin.lottery.domain.strategy.service.algorithm;

import com.tianyin.lottery.domain.strategy.model.vo.AwardRateVO;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseAlgorithm implements IDrawAlgorithm {

    // 斐波那契散列增量
    private final int HASH_INCREMENT = 0x61c88647;

    // 元祖长度
    private final int RATE_TUPLE_LENGTH = 128;

    // 存放概率与奖品对应的散列结果， strategyId => rateTuple
    // protected便于子类访问
    // 使用JUC的ConcurrentHashMap满足并发需求
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    // 奖品区间概率，strategyId => List<AwardRateInfo>
    protected Map<Long, List<AwardRateVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, List<AwardRateVO> awardRateVOList) {

        awardRateInfoMap.put(strategyId, awardRateVOList);
        // 利用浅拷贝获取rateTupleMap中对应的引用
        // computeIfAbsent：若不存在对应的key，则返回新的String数组
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        // 当前游标
        int cursorVal = 0;
        for (AwardRateVO awardRateVO : awardRateVOList) {
            // 将概率乘100，便于迭代操作
            int rateVal = awardRateVO.getAwardRate().multiply(new BigDecimal(100)).intValue();
            // 循环填充概率范围值
            for (int i = cursorVal + 1; i <= (cursorVal + rateVal); i++) {
                rateTuple[hashIdx(i)] = awardRateVO.getAwardId();
            }

            cursorVal += rateVal;

        }

    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }

    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }

    /**
     * 获取随机数
     * @param bound 上限，开区间
     * @return 介于[0, bound]之间的随机数
     */
    protected int generateSecureRandomIntCode(int bound) {
        return new SecureRandom().nextInt(bound) + 1;
    }
}
