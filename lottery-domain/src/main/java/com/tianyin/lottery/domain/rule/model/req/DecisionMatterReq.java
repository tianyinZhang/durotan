package com.tianyin.lottery.domain.rule.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 决策事项请求参数信息
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionMatterReq {

    /** 规则树ID */
    private Long treeId;

    /** 用户ID */
    private String userId;

    /** 决策值 */
    private Map<String, Object> valMap;

}
