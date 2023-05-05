package com.tianyin.lottery.test.interfaces;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.rpc.ILotteryActivityBooth;
import com.tianyin.lottery.rpc.req.DrawReq;
import com.tianyin.lottery.rpc.req.QuantificationDrawReq;
import com.tianyin.lottery.rpc.res.DrawRes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description: 测试类，测试抽奖活动接口的实现类
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LotteryActivityBoothTest {

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw() {
        DrawReq drawReq = new DrawReq();
        drawReq.setUId("Ty");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        log.info("请求参数：{}", JSON.toJSONString(drawReq));
        log.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQuantificationDraw() {
        QuantificationDrawReq req = new QuantificationDrawReq();
        req.setUId("Ty");
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});

        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        log.info("请求参数：{}", JSON.toJSONString(req));
        log.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

}
