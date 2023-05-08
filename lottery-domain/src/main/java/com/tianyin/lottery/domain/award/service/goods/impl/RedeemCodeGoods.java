package com.tianyin.lottery.domain.award.service.goods.impl;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.award.model.req.GoodsReq;
import com.tianyin.lottery.domain.award.model.res.DistributionRes;
import com.tianyin.lottery.domain.award.service.goods.DistributionBase;
import com.tianyin.lottery.domain.award.service.goods.IDistributionGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 兑换码奖品发放接口实现类
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Slf4j
@Component
public class RedeemCodeGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        log.info("模拟调用兑换码 uID: {} awardContent: {}", req.getUId(), req.getAwardContent());
        super.updateUserAwardState(req.getUId(), req.getOrderId(), req.getAwardId(),
                Constants.GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getUId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.RedeemCodeGoods.getCode();
    }

}
