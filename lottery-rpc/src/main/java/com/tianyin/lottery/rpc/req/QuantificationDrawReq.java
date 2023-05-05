package com.tianyin.lottery.rpc.req;

import lombok.Data;

import java.util.Map;

/**
 * @description: 量化人群抽奖请求参数
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@Data
public class QuantificationDrawReq {

    /** 用户ID */
    private String uId;

    /** 规则树ID */
    private Long treeId;

    /** 决策值 */
    private Map<String, Object> valMap;

}
