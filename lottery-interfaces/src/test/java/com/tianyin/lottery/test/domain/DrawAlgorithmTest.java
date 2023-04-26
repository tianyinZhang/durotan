package com.tianyin.lottery.test.domain;

import com.tianyin.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

    @Resource(name = "singleRateRandomDrawAlgorithm")
    IDrawAlgorithm drawAlgorithm;

    @Before
    public void init() {
        List<AwardRateInfo> strategyList = new ArrayList<>();
        strategyList.add(new AwardRateInfo("一等奖：IMac", new BigDecimal("0.05")));
        strategyList.add(new AwardRateInfo("二等奖：iphone", new BigDecimal("0.15")));
        strategyList.add(new AwardRateInfo("三等奖：ipad", new BigDecimal("0.20")));
        strategyList.add(new AwardRateInfo("四等奖：AirPods", new BigDecimal("0.25")));
        strategyList.add(new AwardRateInfo("五等奖：充电宝", new BigDecimal("0.35")));

        drawAlgorithm.initRateTuple(1000001L, strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm() {
        List<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("二等奖：iphone");
        excludeAwardIds.add("四等奖：AirPods");

        for (int i = 0; i < 20; i++)
            System.out.println(drawAlgorithm.randomDraw(1000001L, excludeAwardIds));
    }


}