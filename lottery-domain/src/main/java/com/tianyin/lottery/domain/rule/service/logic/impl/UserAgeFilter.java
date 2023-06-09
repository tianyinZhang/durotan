package com.tianyin.lottery.domain.rule.service.logic.impl;

import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;

/**
 * @description: 年龄规则
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
@Component
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }

}
