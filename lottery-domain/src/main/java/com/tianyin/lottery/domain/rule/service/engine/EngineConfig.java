package com.tianyin.lottery.domain.rule.service.engine;

import com.tianyin.lottery.domain.rule.service.logic.ILogicFilter;
import com.tianyin.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import com.tianyin.lottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 规则配置
 * @author：Tianyin Zhang
 * @date: 2023/5/4
 */
public class EngineConfig {

    protected static Map<String, ILogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }

}
