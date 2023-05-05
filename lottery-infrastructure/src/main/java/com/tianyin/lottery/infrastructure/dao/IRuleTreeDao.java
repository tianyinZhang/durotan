package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 规则树DAO接口类
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Mapper
public interface IRuleTreeDao {

    /**
     * 规则树查询
     *
     * @param id    ID
     * @return      规则树
     */
    RuleTree queryRuleTreeByTreeId(Long id);

    /**
     * 规则树简要信息查询
     *
     * @param treeId    规则树ID
     * @return          规则树
     */
    RuleTree queryTreeSummaryInfo(Long treeId);

}
