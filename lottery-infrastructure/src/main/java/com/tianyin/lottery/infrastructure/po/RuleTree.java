package com.tianyin.lottery.infrastructure.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 规则树实体类，对应数据库表：rule_tree，字段：id、tree_name、tree_root_node_id、create_time、update_time
 *               记录规则树的基本信息，包括规则树名称、规则树根节点ID、创建时间、更新时间等
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class RuleTree {

    /** 主键ID */
    private Long id;

    /** 规则树名称 */
    private String treeName;

    /** 规则树描述 */
    private String treeDesc;

    /** 规则树根ID */
    private Long treeRootNodeId;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
