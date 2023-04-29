package com.tianyin.lottery.domain.activity.model.res;

import com.tianyin.lottery.common.Result;

/**
 * @description: 活动参与结果
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
public class PartakeResult extends Result {

    /** 策略ID */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

}
