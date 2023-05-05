package com.tianyin.lottery.domain.rule.service.engine.impl;

import com.tianyin.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.model.res.EngineResult;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeVO;
import com.tianyin.lottery.domain.rule.repository.IRuleRepository;
import com.tianyin.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 规则引擎处理器
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Service("ruleEngineHandle")
public class TreeEngineHandle extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        // 1. 获取决策引擎树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if (null == treeRuleRich) {
            throw new RuntimeException("Tree Rule is null!");
        }

        // 2. 决策
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);

        // 3. 返回决策结果
        return new EngineResult(matter.getUserId(), treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }

}
