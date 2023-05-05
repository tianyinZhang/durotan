package com.tianyin.lottery.domain.rule.service.logic;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.rule.model.vo.TreeNodeLineVO;
import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;

import java.util.List;

/**
 * @description: 规则基础抽象类
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
public abstract class BaseLogic implements ILogicFilter {

    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        for (TreeNodeLineVO nodeLine : treeNodeLineInfoList) {
            if (decisionLogic(matterValue, nodeLine)) {
                return nodeLine.getNodeIdTo();
            }
        }
        return Constants.Global.TREE_NULL_NODE;
    }

    /**
     * 获取规则比对值
     * @param decisionMatter 决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatter);

    /**
     * 决策逻辑，根据规则比对值与规则限定值进行比对
     *
     * @param matterValue   规则比对值
     * @param nodeLine      规则限定值
     * @return              比对结果
     */
    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLine) {
        switch (nodeLine.getRuleLimitType()) {
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLine.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }

}
