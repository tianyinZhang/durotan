package com.tianyin.lottery.application.mq.consumer;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.activity.model.vo.InvoiceVO;
import com.tianyin.lottery.domain.award.model.req.GoodsReq;
import com.tianyin.lottery.domain.award.model.res.DistributionRes;
import com.tianyin.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.tianyin.lottery.domain.award.service.goods.IDistributionGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @description: 消息消费者
 * @author：Tianyin Zhang
 * @date: 2023/5/6
 */
@Component
@Slf4j
public class LotteryInvoiceListener {

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = "lottery_invoice", groupId = "lottery")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        // 1. 判断消息是否存在，避免空指针异常
        Optional<?> message = Optional.ofNullable(record.value());
        if (!message.isPresent()) {
            return;
        }

        // 2. 处理 MQ 消息
        try {
            // 3. 转化对象
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            // 4. 获取发送奖品工厂，执行发奖
            Integer awardType = invoiceVO.getAwardType();
            IDistributionGoods distributionGoods = distributionGoodsFactory.getDistributionGoodsService(awardType);
            GoodsReq goodsReq = new GoodsReq(invoiceVO.getUId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(), invoiceVO.getAwardName(), invoiceVO.getAwardContent());
            DistributionRes distributionRes = distributionGoods.doDistribution(goodsReq);

            // Assert.isTrue 的第一个参数为布尔表达式，第二个参数为异常时抛出的错误信息
            Assert.isTrue(Constants.AwardState.SUCCESS.getCode().equals(distributionRes.getCode()), distributionRes.getInfo());

            // 5. 打印日志
            log.info("消费MQ消息，完成 topic: {} bizId: {} 发奖结果: {}", topic, invoiceVO.getUId(), JSON.toJSONString(distributionRes));

            // 6. 消息消费完成
            ack.acknowledge();

        } catch (Exception e) {
            // 发奖环节失败，消息重试。发货、更新库等所有环节均需保证幂等性
            log.error("消费MQ消息，失败 topic: {} message: {}", topic, message.get());
            throw e;
        }

    }

}
