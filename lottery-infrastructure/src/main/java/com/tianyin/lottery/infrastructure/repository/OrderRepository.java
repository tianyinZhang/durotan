package com.tianyin.lottery.infrastructure.repository;

import com.tianyin.lottery.domain.award.repository.IOrderRepository;
import com.tianyin.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.tianyin.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: 奖品仓储实现类
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);
    }

}
