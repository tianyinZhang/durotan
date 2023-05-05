package com.tianyin.lottery.infrastructure.po;

import lombok.Data;

/**
 * @description: 规则树节点连线实体类，对应数据库表：rule_tree_node_line，字段：id、tree_id、node_id_from、node_id_to、rule_limit_type、rule_limit_value
 *               记录规则树节点连线的基本信息，包括规则树ID、节点From、节点To、限定类型、限定值等
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class RuleTreeNodeLine {

    /** 主键ID */
    private Long id;

    /** 规则树ID */
    private Long treeId;

    /** 节点From */
    private Long nodeIdFrom;

    /** 节点To */
    private Long nodeIdTo;

    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围] */
    private Integer ruleLimitType;

    /** 限定值 */
    private String ruleLimitValue;

}
