package com.tianyin.lottery.domain.rule.service.logic;

import com.tianyin.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 规则过滤器接口
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
public interface ILogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点
     * @return                      下一个节点ID
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter        决策事项请求参数
     * @return                      决策值
     */
    String matterValue(DecisionMatterReq decisionMatter);

}
