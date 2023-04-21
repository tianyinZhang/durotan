package com.tianyin.lottery.test;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.award.model.req.GoodsReq;
import com.tianyin.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.tianyin.lottery.domain.award.service.goods.IDistributionGoods;
import com.tianyin.lottery.domain.strategy.model.req.DrawReq;
import com.tianyin.lottery.domain.strategy.model.res.DrawResult;
import com.tianyin.lottery.domain.strategy.model.vo.DrawAwardInfo;
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
            String uId = "Ty" + i;
            drawExec.doDrawExecute(new DrawReq(uId, 10001L));
        }
    }

    @Test
    public void test_award() {

        // 1. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExecute(new DrawReq("Ty", 10001L));

        // 2. 判断抽奖结果
        Integer drawState = drawResult.getDrawState();
        if (Constants.DrawState.FAIL.equals(drawState)) {
            log.info("未中奖");
            return;
        }

        // 3. 封装发奖参数
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();
        GoodsReq goodsReq = new GoodsReq(drawResult.getUId(), "1821693217682196"
                , drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent());

        // 4. 根据 awardType 从抽奖工厂中获取对应的发奖服务并执行
        DistributionGoodsFactory distributionGoodsFactory = new DistributionGoodsFactory();
        IDistributionGoods distributionGoods = distributionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
        distributionGoods.doDistribution(goodsReq);

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
