package com.tianyin.lottery.domain.rule.model.aggregates;

import com.tianyin.lottery.domain.rule.model.vo.TreeNodeVO;
import com.tianyin.lottery.domain.rule.model.vo.TreeRootVO;
import lombok.Data;

import java.util.Map;

/**
 * @description: 规则树聚合
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class TreeRuleRich {

    /** 树根信息 */
    private TreeRootVO treeRoot;

    /** 树节点ID -> 子节点ID */
    private Map<Long, TreeNodeVO> treeNodeMap;

}
