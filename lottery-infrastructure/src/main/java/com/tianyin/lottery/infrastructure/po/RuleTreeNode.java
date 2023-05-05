package com.tianyin.lottery.infrastructure.po;

import lombok.Data;

/**
 * @description: 规则树节点实体类，对应数据库表：rule_tree_node，字段：id、tree_id、node_type、node_value、rule_key、rule_desc
 *               记录规则树节点的基本信息，包括规则树ID、节点类型、节点值、规则Key、规则描述等
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class RuleTreeNode {

    /** 主键ID */
    private Long id;

    /** 规则树ID */
    private Long treeId;

    /** 节点类型；1子叶、2果实 */
    private Integer nodeType;

    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;

    /** 规则Key */
    private String ruleKey;

    /** 规则描述 */
    private String ruleDesc;

}
