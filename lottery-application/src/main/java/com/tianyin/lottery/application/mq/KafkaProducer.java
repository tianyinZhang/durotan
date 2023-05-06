package com.tianyin.lottery.application.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @description: 消息生产者
 * @author：Tianyin Zhang
 * @date: 2023/5/6
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_TEST = "Hello-Kafka";

    public static final String TOPIC_GROUP = "test-consumer-group";

    public void send(Object obj) {
        String obj2String = JSON.toJSONString(obj);
        log.info("准备发送的消息为: {}", obj2String);

        // 发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                // 发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                // 成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功: " + result.toString());
            }
        });
    }

}
