package com.tianyin.lottery.domain.award.model.req;

import com.tianyin.lottery.domain.award.model.vo.ShippingAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 奖品发货信息
 * @author：Tianyin Zhang
 * @date: 2023/4/21
 */
@Data
@NoArgsConstructor
public class GoodsReq {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 抽奖单号ID
     */
    private String orderId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容
     */
    private String awardContent;

    /**
     * 送货地址（仅实物类商品）
     */
    private ShippingAddress shippingAddress;

    /**
     * 扩展信息，用于一些个性商品发放所需要的透传字段内容
     * 透传字段：系统间传输数据时，不经过处理、转换或解析，而是直接从源系统传输到目标系统
     */
    private String extInfo;

    public GoodsReq(String uId, String orderId, String awardId, String awardName, String awardContent) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }

    public GoodsReq(String uId, String orderId, String awardId, String awardName, String awardContent, ShippingAddress shippingAddress) {
        this.uId = uId;
        this.orderId = orderId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardContent = awardContent;
        this.shippingAddress = shippingAddress;
    }

}
