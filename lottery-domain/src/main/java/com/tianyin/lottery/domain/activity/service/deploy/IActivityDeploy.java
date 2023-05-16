package com.tianyin.lottery.domain.activity.service.deploy;

import com.tianyin.lottery.domain.activity.model.req.ActivityConfigReq;
import com.tianyin.lottery.domain.activity.model.vo.ActivityVO;

import java.util.List;

/**
 * @description: 活动部署服务接口定义
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

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     * 通过 -> 时间符合时 -> 活动中
     * 活动中 -> 时间到期时 -> 关闭
     *
     * @param id ID
     * @return   待处理的活动集合
     */
    List<ActivityVO> scanToDoActivityList(Long id);

}
