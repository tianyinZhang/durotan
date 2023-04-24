package com.tianyin.lottery.domain.activity.service.deploy.impl;

import com.tianyin.lottery.domain.activity.model.aggregates.ActivityConfigRich;
import com.tianyin.lottery.domain.activity.model.req.ActivityConfigReq;
import com.tianyin.lottery.domain.activity.repository.IActivityRepository;
import com.tianyin.lottery.domain.activity.service.deploy.IActivityDeploy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description:
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Service
@Slf4j
public class ActivityDeployImpl implements IActivityDeploy {

    @Resource
    private IActivityRepository activityRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createActivity(ActivityConfigReq req) {
        log.info("创建活动配置信息， activityId: {}", req.getActivityId());
        ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

        try {
            // 1. 添加活动配置
            activityRepository.addActivity(activityConfigRich.getActivity());

            // 2. 添加奖品配置
            activityRepository.addAward(activityConfigRich.getAwardList());

            // 3. 添加策略配置
            activityRepository.addStrategy(activityConfigRich.getStrategy());

            // 4. 添加策略明细配置
            activityRepository.addStrategyDetailList(activityConfigRich.getStrategy().getStrategyDetailList());

            log.info("创建活动配置完成， activityId: {}", req.getActivityId());
        } catch (DuplicateKeyException e) {
            log.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), req, e);
            throw e;
        }
    }

    /**
     * 更新活动信息
     *
     * @param req 活动配置信息
     * @TODO 后续补充
     */
    @Override
    public void updateActivity(ActivityConfigReq req) {

    }


}
