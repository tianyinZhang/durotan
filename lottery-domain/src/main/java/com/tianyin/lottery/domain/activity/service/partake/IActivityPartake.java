package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.res.PartakeResult;
import com.tianyin.lottery.domain.activity.model.vo.DrawOrderVO;

/**
 * @description:    参与抽奖活动接口
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
public interface IActivityPartake {

    /**
     * 参与活动
     *
     * @param req       参与请求
     * @return          领取结果
     */
    PartakeResult doPartake(PartakeReq req);

    /**
     * 保存奖品单
     *
     * @param drawOrder 奖品单
     * @return          保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);

    /**
     * 更改发货单MQ状态
     *
     * @param uId       用户ID
     * @param orderId   订单ID
     * @param mqState   MQ 发送状态
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer mqState);

}
