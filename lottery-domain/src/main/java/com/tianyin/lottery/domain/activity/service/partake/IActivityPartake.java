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
     */
    PartakeResult doPartake(PartakeReq req);


    Result recordDrawOrder(DrawOrderVO drawOrder);

}
