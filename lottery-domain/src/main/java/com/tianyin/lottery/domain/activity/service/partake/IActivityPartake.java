package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.res.PartakeResult;

/**
 * @description:    参与抽奖活动接口
 * @author：Tianyin Zhang
 * @date: 2023/4/24
 */
public interface IActivityPartake {

    /**
     * 参与活动
     */
    PartakeResult doPartake(PartakeReq req);

}
