package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.res.PartakeResult;
import com.tianyin.lottery.domain.activity.model.vo.DrawOrderVO;
import com.tianyin.lottery.domain.activity.model.vo.InvoiceVO;

import java.util.List;

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

    /**
     * 扫描发货单 MQ 状态，打印未发送 MQ 的单子并进行补偿
     * @param dbCount   指定分库
     * @param tbCount   指定分表
     * @return          发货单
     */
    List<InvoiceVO> scanInvoiceMqState(int dbCount, int tbCount);

}
