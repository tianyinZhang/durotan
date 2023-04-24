package com.tianyin.lottery.infrastructure.repository;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.activity.model.vo.*;
import com.tianyin.lottery.domain.activity.repository.IActivityRepository;
import com.tianyin.lottery.infrastructure.dao.IActivityDao;
import com.tianyin.lottery.infrastructure.dao.IAwardDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDetailDao;
import com.tianyin.lottery.infrastructure.po.Activity;
import com.tianyin.lottery.infrastructure.po.Award;
import com.tianyin.lottery.infrastructure.po.Strategy;
import com.tianyin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 活动仓储实现类
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    IActivityDao activityDao;

    @Resource
    private IAwardDao awardDao;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Override
    public void addActivity(ActivityVO activity) {
        Activity req = new Activity();
        BeanUtils.copyProperties(activity, req);
        activityDao.insert(req);
    }

    @Override
    public void addAward(List<AwardVO> awardList) {
        List<Award> req = new ArrayList<>();
        for (AwardVO award : awardList) {
            Award award1 = new Award();
            BeanUtils.copyProperties(award, award1);
            req.add(award1);
        }
        awardDao.insertAwardList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategy) {
        Strategy req = new Strategy();
        BeanUtils.copyProperties(strategy, req);
        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
        List<StrategyDetail> req = new ArrayList<>();
        for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
            StrategyDetail strategyDetail = new StrategyDetail();
            BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertStrategyDetailList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterStatus(alterStateVO);
        return 1 == count;
    }


}
