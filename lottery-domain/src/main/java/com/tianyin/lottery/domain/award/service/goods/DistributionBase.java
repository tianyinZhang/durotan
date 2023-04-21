package com.tianyin.lottery.domain.award.service.goods;

import com.tianyin.lottery.domain.award.repository.IAwardRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @description: 配送货物基础共用类
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Slf4j
public class DistributionBase {

    @Resource
    private IAwardRepository awardRepository;

    /**
     * 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态
     * @param uId               用户ID
     * @param orderId           订单ID
     * @param awardId           奖品ID
     * @param awardState        奖品状态
     * @param awardStateInfo    奖品状态信息
     */
    protected void updateUserAwardState(String uId, String orderId, String awardId, Integer awardState, String awardStateInfo) {

    }

}
