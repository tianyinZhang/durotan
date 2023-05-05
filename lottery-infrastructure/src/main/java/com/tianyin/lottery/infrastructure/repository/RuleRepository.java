package com.tianyin.lottery.infrastructure.repository;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeVO;
import com.tianyin.lottery.domain.rule.model.vo.TreeRootVO;
import com.tianyin.lottery.domain.rule.repository.IRuleRepository;
import com.tianyin.lottery.infrastructure.dao.IRuleTreeDao;
import com.tianyin.lottery.infrastructure.dao.IRuleTreeNodeDao;
import com.tianyin.lottery.infrastructure.dao.IRuleTreeNodeLineDao;
import com.tianyin.lottery.infrastructure.po.RuleTree;
import com.tianyin.lottery.infrastructure.po.RuleTreeNode;
import com.tianyin.lottery.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则仓储实现类
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private IRuleTreeDao IRuleTreeDao;

    @Resource
    private IRuleTreeNodeDao IRuleTreeNodeDao;

    @Resource
    private IRuleTreeNodeLineDao IRuleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {

        // 规则树
        RuleTree ruleTree = IRuleTreeDao.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRoot = new TreeRootVO();
        treeRoot.setTreeId(ruleTree.getId());
        treeRoot.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRoot.setTreeName(ruleTree.getTreeName());

        // 树节点->树连接线
        Map<Long, TreeNodeVO> treeNodeMap = new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList = IRuleTreeNodeDao.queryRuleTreeNodeList(treeId);
        for (RuleTreeNode treeNode : ruleTreeNodeList) {
            List<TreeNodeLineVO> treeNodeLineInfoList = new ArrayList<>();
            if (Constants.NodeType.STEM.equals(treeNode.getNodeType())) {

                RuleTreeNodeLine ruleTreeNodeLineReq = new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(treeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = IRuleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                for (RuleTreeNodeLine nodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLineInfo = new TreeNodeLineVO();
                    treeNodeLineInfo.setNodeIdFrom(nodeLine.getNodeIdFrom());
                    treeNodeLineInfo.setNodeIdTo(nodeLine.getNodeIdTo());
                    treeNodeLineInfo.setRuleLimitType(nodeLine.getRuleLimitType());
                    treeNodeLineInfo.setRuleLimitValue(nodeLine.getRuleLimitValue());
                    treeNodeLineInfoList.add(treeNodeLineInfo);
                }
            }
            TreeNodeVO treeNodeInfo = new TreeNodeVO();
            treeNodeInfo.setTreeId(treeNode.getTreeId());
            treeNodeInfo.setTreeNodeId(treeNode.getId());
            treeNodeInfo.setNodeType(treeNode.getNodeType());
            treeNodeInfo.setNodeValue(treeNode.getNodeValue());
            treeNodeInfo.setRuleKey(treeNode.getRuleKey());
            treeNodeInfo.setRuleDesc(treeNode.getRuleDesc());
            treeNodeInfo.setTreeNodeLineInfoList(treeNodeLineInfoList);

            treeNodeMap.put(treeNode.getId(), treeNodeInfo);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRoot);
        treeRuleRich.setTreeNodeMap(treeNodeMap);

        return treeRuleRich;
    }

}
