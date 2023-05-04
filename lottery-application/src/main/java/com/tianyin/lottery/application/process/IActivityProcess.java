package com.tianyin.lottery.application.process;

import com.tianyin.lottery.application.process.req.DrawProcessReq;
import com.tianyin.lottery.application.process.res.DrawProcessResult;

/**
 * @description: 活动抽奖流程编排接口定义
 * @author：Tianyin Zhang
 * @date: 2023/5/2
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     *
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);
}
