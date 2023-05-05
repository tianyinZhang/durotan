package com.tianyin.lottery.domain.rule.service.engine;

import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.model.res.EngineResult;

/**
 * @description: 规则过滤引擎
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
public interface IEngineFilter {

    /**
     * 规则过滤器接口
     *
     * @param matter      规则决策物料
     * @return            规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);

}
