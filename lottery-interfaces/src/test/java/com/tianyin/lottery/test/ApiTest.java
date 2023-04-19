package com.tianyin.lottery.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApiTest {

    /**
     * 测试 strategyList 配置
     */
    @Test
    public void test_strategy() {
        SecureRandom random = new SecureRandom();
        int rate = random.nextInt(100);

        System.out.println("概率：" + rate);

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "0.2");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "0.3");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "三等奖：洗衣机");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "0.5");
        }});

    }

    /**
     * 测试结果证明斐波那契散列分布更为均匀
     */
    @Test
    public void test_idx() {
        Map<Integer, Integer> map = new HashMap<>();

        int HASH_INCREMENT = 0x61c88647;
        int hashCode = 0;
        for (int i = 0; i <= 100; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = hashCode & (128 - 1);

            // 若map中不存在idx，则赋1；若存在，则 +1
            map.merge(idx, 1, Integer::sum);

            System.out.println("斐波那契散列：" + idx + "普通散列：" + (String.valueOf(i).hashCode() & (128 - 1)));
        }

        System.out.println(map);

    }

    @Test
    public void test_DrawStrategy() {

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "20");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "30");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "三等奖：洗衣机");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "50");
        }});

        DrawStrategy drawStrategy = new DrawStrategy();
        drawStrategy.initRateTuple(strategyList);
        for (int i = 0; i < 20; i++) {
            System.out.println(drawStrategy.randomDraw());
        }

    }

    @Test
    public void test_random() {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 20; i++)
            System.out.println(random.nextInt(2));
    }

}

class DrawStrategy {

    // 黄金分割点
    private final int HASH_INCREMENT = 0x61c88647;

    // 用于保存0 - 100，斐波那契散列索引的结果
    private String[] rateTuple = new String[128];

    /**
     * 初始化概率索引数组，将0.20 0.30 0.50调整为0-20 20-50 50-100的范围值
     * 位于范围内则表示获得对应奖品
     */
    public void initRateTuple(List<Map<String, String>> drawConfig) {
        int cursorVal = 0;
        for (Map<String, String> drawMap : drawConfig) {
            int rateVal = Integer.parseInt(drawMap.get("awardRate"));

            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                // 使用斐波那契散列获取索引并填充数据
                int hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
                int idx = hashCode & (rateTuple.length - 1);
                rateTuple[idx] = drawMap.get("awardDesc");
            }

            cursorVal += rateVal;
        }
    }

    /**
     * 使用随机数模拟抽奖
     * @return
     */
    public String randomDraw() {
        int rate = new SecureRandom().nextInt(100) + 1;
        int hashCode = rate * HASH_INCREMENT + HASH_INCREMENT;
        int idx = hashCode & (rateTuple.length - 1);
        return rateTuple[idx];
    }

}
