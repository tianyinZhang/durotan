package com.tianyin.lottery.domain.rule.service.engine;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.model.res.EngineResult;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeVO;
import com.tianyin.lottery.domain.rule.model.vo.TreeRootVO;
import com.tianyin.lottery.domain.rule.service.logic.ILogicFilter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @description: 规则引擎基础类
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Slf4j
public abstract class EngineBase extends EngineConfig implements IEngineFilter {

    @Override
    public EngineResult process(final DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎");
    }

    /**
     * 规则引擎决策器
     * 从根节点开始，迭代决策直到叶节点
     *
     * @param treeRuleRich  规则树聚合
     * @param matter        决策事项请求参数
     * @return              决策结果
     */
    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq matter) {
        // 1. 获取决策树（根节点 & 节点集合）
        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 2. 获取规则树 根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVO treeNodeInfo = treeNodeMap.get(rootNodeId);

        // 3. 判断节点类型，如果是果实节点，跳出循环
        while (Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())) {
            // 4. 获取规则Key
            String ruleKey = treeNodeInfo.getRuleKey();

            // 5. 获取决策值
            ILogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(matter);

            // 6. 获取下一个节点ID
            Long nextNodeId = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());
            treeNodeInfo = treeNodeMap.get(nextNodeId);
            log.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}",
                    treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }

        return treeNodeInfo;
    }

}
