package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {

    /**
     * 查询策略信息
     *
     * @param strategyId    策略ID
     * @return              策略信息
     */
    Strategy queryStrategy(Long strategyId);

    /**
     * 插入策略配置
     *
     * @param req           策略配置
     */
    void insert(Strategy req);

}
