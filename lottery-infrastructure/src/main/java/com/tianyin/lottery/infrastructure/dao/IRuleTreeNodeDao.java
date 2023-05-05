package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 规则树节点DAO
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Mapper
public interface IRuleTreeNodeDao {

    /**
     * 查询规则树节点
     * @param treeId    规则树ID
     * @return          规则树节点集合
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    /**
     * 查询规则树节点数量
     * @param treeId    规则树ID
     * @return          节点数量
     */
    int queryTreeNodeCount(Long treeId);

    /**
     * 查询规则树节点
     *
     * @param treeId    规则树ID
     * @return          节点集合
     */
    List<RuleTreeNode> queryTreeRulePoint(Long treeId);

}
