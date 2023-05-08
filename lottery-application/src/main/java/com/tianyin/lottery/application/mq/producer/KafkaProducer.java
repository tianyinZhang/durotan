package com.tianyin.lottery.application.mq.producer;

import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.domain.activity.model.vo.InvoiceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

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

    /**
     * MQ主题：中奖发货单
     */
    public static final String TOPIC_INVOICE = "lottery_invoice";

    /**
     * 发送中奖物品发货单消息
     *
     * @param invoice   发货单
     * @return          发送结果
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryInvoice(InvoiceVO invoice) {
        String objJson = JSON.toJSONString(invoice);
        log.info("准备发送MQ消息: topic: {} bizId: {} message: {}", TOPIC_INVOICE, invoice.getUId(), objJson);
        return kafkaTemplate.send(TOPIC_INVOICE, objJson);
    }

}
