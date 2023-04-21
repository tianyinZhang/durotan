package com.tianyin.lottery.domain.award.service.factory;

import com.tianyin.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description: 配送商品简单工厂，提供获取配送服务
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig {

    public IDistributionGoods getDistributionGoodsService(Integer awardType) {
        return goodsMap.get(awardType);
    }

}
