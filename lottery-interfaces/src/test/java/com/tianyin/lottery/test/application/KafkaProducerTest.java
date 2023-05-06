package com.tianyin.lottery.test.application;

import com.tianyin.lottery.application.mq.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: Kafka 消息测试
 * @author：Tianyin Zhang
 * @date: 2023/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KafkaProducerTest {

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    public void test_send() throws InterruptedException {
        int i = 0;
        // 循环发送消息
        while (true) {
            kafkaProducer.send("Hello, I'm Ty" + i++ + "!");
            Thread.sleep(5000);
        }
    }
}
