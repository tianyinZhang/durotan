package com.tianyin.lottery.application.process.res;

import com.tianyin.lottery.common.Result;
import lombok.Data;

/**
 * @description: 规则量化人群结果信息
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@Data
public class RuleQuantificationCrowdResult extends Result {

    /** 活动ID */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

}
