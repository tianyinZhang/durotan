package com.tianyin.lottery.test.application;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.application.process.IActivityProcess;
import com.tianyin.lottery.application.process.req.DrawProcessReq;
import com.tianyin.lottery.application.process.res.DrawProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: 测试类，测试抽奖流程编排接口的实现类 ActivityProcessImpl
 * @author：Tianyin Zhang
 * @date: 2023/5/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActivityProcessTest {

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess() {
        DrawProcessReq req = new DrawProcessReq();
        req.setUId("Ty");
        req.setActivityId(100001L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(req);

        log.info("请求入参： {}", JSON.toJSONString(req));
        log.info("测试结果： {}", JSON.toJSONString(drawProcessResult));
    }

}
