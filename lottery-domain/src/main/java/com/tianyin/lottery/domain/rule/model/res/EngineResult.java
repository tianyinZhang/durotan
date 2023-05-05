package com.tianyin.lottery.domain.rule.model.res;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 规则引擎执行结果信息
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Data
@NoArgsConstructor
public class EngineResult {

    /** 执行结果 */
    private boolean isSuccess;

    /** 用户ID */
    private String userId;

    /** 规则树ID */
    private Long treeId;

    /** 果实节点ID */
    private Long nodeId;

    /** 果实节点值 */
    private String nodeValue;

    public EngineResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public EngineResult(String userId, Long treeId, Long nodeId, String nodeValue) {
        this.isSuccess = true;
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

}
