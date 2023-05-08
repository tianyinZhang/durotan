package com.tianyin.lottery.domain.award.repository;

/**
 * @description: 订单仓储接口类
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
public interface IOrderRepository {

    /**
     * 更新奖品发放状态
     *
     * @param uId               用户ID
     * @param orderId           订单ID
     * @param awardId           奖品ID
     * @param grantState        奖品状态
     */
    void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState);

}
