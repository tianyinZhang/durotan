package com.tianyin.lottery.domain.activity.repository;

import com.tianyin.lottery.domain.activity.model.vo.ActivityVO;
import com.tianyin.lottery.domain.activity.model.vo.AwardVO;
import com.tianyin.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.tianyin.lottery.domain.activity.model.vo.StrategyVO;
import com.tianyin.lottery.common.Constants;

import java.util.List;

/**
 * @description: 活动仓库服务（活动表、奖品表、策略表、策略明细表）
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
public interface IActivityRepository {

    /**
     * 添加活动配置
     * @param activity 活动配置
     */
    void addActivity(ActivityVO activity);

    /**
     * 添加奖品配置集合
     * @param awardList 奖品配置集合
     */
    void addAward(List<AwardVO> awardList);

    /**
     * 添加策略配置
     * @param strategy 策略配置
     */
    void addStrategy(StrategyVO strategy);

    /**
     * 添加策略明细配置
     * @param strategyDetailList 策略明细配置
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList);

    /**
     * 变更活动状态
     *
     * @param activityId    活动ID
     * @param beforeState   修改前状态
     * @param afterState    修改后状态
     * @return              受影响行数
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState);

}
