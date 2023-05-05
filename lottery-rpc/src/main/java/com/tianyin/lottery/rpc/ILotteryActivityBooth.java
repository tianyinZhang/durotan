package com.tianyin.lottery.rpc;

import com.tianyin.lottery.rpc.req.DrawReq;
import com.tianyin.lottery.rpc.req.QuantificationDrawReq;
import com.tianyin.lottery.rpc.res.DrawRes;

/**
 * @description: 抽奖活动展台接口
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     *
     * @param drawReq   请求参数
     * @return          抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     *
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);

}
