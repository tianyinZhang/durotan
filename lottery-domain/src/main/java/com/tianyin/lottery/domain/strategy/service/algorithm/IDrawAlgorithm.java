package com.tianyin.lottery.domain.strategy.service.algorithm;

import com.tianyin.lottery.domain.strategy.model.vo.AwardRateInfo;

import java.util.List;

public interface IDrawAlgorithm {

    /**
     * 初始化概率元组
     * 将奖品概率转换为分布，如：奖品A概率为0.1，奖品B概率为0.2，奖品C概率为0.3，奖品D概率为0.4
     * 则分布为A：0-10，B：11-30，C：31-60，D：61-100
     *
     * 再利用斐波那契散射算法，将奖品分布散射到数组中，实现元祖中有对应比例的元素
     * 优势在于，通过随机数获取到1-100的值后，可以直接定位到对应的奖品信息，通过这样的方式把轮训算奖的时间复杂度从O(n) 降低到 0(1)
     * @param strategyId
     * @param awardRateInfoList
     */
    void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList);

    /**
     * 判断是否完成元祖初始化
     */
    boolean isExistRateTuple(Long strategyId);

    /**
     * 利用SecureRandom生成的随机数，索引到对应的元素实现抽奖
     */
    String randomDraw(Long strategyId, List<String> excludeAwardIds);


}
