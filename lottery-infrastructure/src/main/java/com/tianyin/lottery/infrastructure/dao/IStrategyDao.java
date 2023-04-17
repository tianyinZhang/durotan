package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStrategyDao {

    Strategy queryStrategy(Long strategyId);

}
