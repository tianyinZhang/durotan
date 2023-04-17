package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAwardDao {

    Award queryAwardInfo(String awardId);

}
