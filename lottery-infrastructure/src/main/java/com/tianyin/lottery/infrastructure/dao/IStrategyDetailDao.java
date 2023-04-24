package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {

    /**
     * 查询策略明细表
     *
     * @param strategyId    策略ID
     * @return              策略明细表
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     *
     * @param strategyId    策略ID
     * @return              无库存策略奖品ID
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     *
     * @param strategyDetail    策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetail);

    /**
     * 插入策略配置组
     *
     * @param list  策略配置组
     */
    void insertStrategyDetailList(List<StrategyDetail> list);

}
