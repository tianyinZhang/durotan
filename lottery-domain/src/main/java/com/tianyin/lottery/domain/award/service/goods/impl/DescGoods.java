package com.tianyin.lottery.domain.award.service.goods.impl;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.award.model.req.GoodsReq;
import com.tianyin.lottery.domain.award.model.res.DistributionRes;
import com.tianyin.lottery.domain.award.service.goods.DistributionBase;
import com.tianyin.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description: 文字描述奖品发放接口实现类
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        super.updateUserAwardState(req.getUId(), req.getOrderId(), req.getAwardId(),
                Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getUId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.DESC.getCode();
    }

}
