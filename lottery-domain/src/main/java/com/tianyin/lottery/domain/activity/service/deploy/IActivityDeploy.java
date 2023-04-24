package com.tianyin.lottery.domain.activity.service.deploy;

import com.tianyin.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @description:
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
public interface IActivityDeploy {

    /**
     * 创建活动信息
     *
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 更新活动信息
     *
     * @param req 活动配置信息·
     */
    void updateActivity(ActivityConfigReq req);

}
