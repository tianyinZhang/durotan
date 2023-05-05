package com.tianyin.lottery.test.domain;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.domain.rule.model.req.DecisionMatterReq;
import com.tianyin.lottery.domain.rule.model.res.EngineResult;
import com.tianyin.lottery.domain.rule.service.engine.IEngineFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description: 规则引擎测试
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RuleTest {

    @Resource
    private IEngineFilter engineFilter;

    @Test
    public void test_process() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("Ty");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = engineFilter.process(req);

        log.info("请求参数：{}", JSON.toJSONString(req));
        log.info("测试结果：{}", JSON.toJSONString(res));
    }
}
