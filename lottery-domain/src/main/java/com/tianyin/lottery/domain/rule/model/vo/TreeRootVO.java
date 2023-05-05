package com.tianyin.lottery.domain.rule.model.vo;

import lombok.Data;

/**
 * @description: 规则树根配置
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
public class TreeRootVO {

    /** 规则树ID */
    private Long treeId;

    /** 规则树根ID */
    private Long treeRootNodeId;

    /** 规则树名称 */
    private String treeName;

}
