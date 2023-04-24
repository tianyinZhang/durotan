package com.tianyin.lottery.infrastructure.repository;

import com.tianyin.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.tianyin.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;
import com.tianyin.lottery.infrastructure.dao.IAwardDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDao;
import com.tianyin.lottery.infrastructure.dao.IStrategyDetailDao;
import com.tianyin.lottery.infrastructure.po.Award;
import com.tianyin.lottery.infrastructure.po.Strategy;
import com.tianyin.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 策略表仓储服务
 * @author：Tianyin Zhang
 * @date: 2023/4/18
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;

    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailVO> strategyDetailVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailVO strategyDetailVO = new StrategyDetailVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailVO);
            strategyDetailVOList.add(strategyDetailVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award, awardBriefVO);

        return awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail strategyDetail = new StrategyDetail();
        strategyDetail.setStrategyId(strategyId);
        strategyDetail.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(strategyDetail);
        return count == 1;
    }

}
