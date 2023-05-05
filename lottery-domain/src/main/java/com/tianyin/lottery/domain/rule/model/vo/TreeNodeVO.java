package com.tianyin.lottery.domain.rule.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: 规则树节点信息
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class TreeNodeVO {

    /** 规则树ID */
    private Long treeId;

    /** 规则树节点ID */
    private Long treeNodeId;

    /** 节点类型；1子叶、2果实 */
    private Integer nodeType;

    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;

    /** 规则Key */
    private String ruleKey;

    /** 规则描述 */
    private String ruleDesc;

    /** 节点链路 */
    private List<TreeNodeLineVO> treeNodeLineInfoList;

}
