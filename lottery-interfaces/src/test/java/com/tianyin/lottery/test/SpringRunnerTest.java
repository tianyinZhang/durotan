package com.tianyin.lottery.test;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.domain.strategy.model.req.DrawReq;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.tianyin.lottery.domain.strategy.service.draw.AbstractDrawBase;
import com.tianyin.lottery.domain.strategy.service.draw.IDrawExec;
import com.tianyin.lottery.infrastructure.dao.IActivityDao;
import com.tianyin.lottery.infrastructure.po.Activity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringRunnerTest {

    @Resource
    IDrawExec drawExec;

    @Autowired
    IActivityDao activityDao;

    @Test
    public void test_drawExec() {
        for (int i = 0; i < 100; ++i) {
            String uId = "Ty" + String.valueOf(i);
            drawExec.doDrawExecute(new DrawReq(uId, 10001L));
        }
    }

    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("Ty0");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        log.info("测试结果：{}", JSON.toJSONString(activity));
    }

}
