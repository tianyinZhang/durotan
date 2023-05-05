package com.tianyin.lottery.domain.rule.repository;

import com.tianyin.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @description: 规则过滤器接口
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
public interface IRuleRepository {

    /**
     * 查询规则树聚合信息
     *
     * @param treeId   规则树ID
     * @return         规则树聚合信息
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);

}
