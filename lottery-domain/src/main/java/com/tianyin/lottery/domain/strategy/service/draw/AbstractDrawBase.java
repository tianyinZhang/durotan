package com.tianyin.lottery.domain.strategy.service.draw;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.activity.model.vo.StrategyDetailVO;
import com.tianyin.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.tianyin.lottery.domain.strategy.model.res.DrawResult;
import com.tianyin.lottery.domain.strategy.model.req.DrawReq;
import com.tianyin.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.tianyin.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.tianyin.lottery.domain.strategy.model.vo.DrawAwardInfo;
import com.tianyin.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.tianyin.lottery.domain.strategy.repository.IStrategyRepository;
import com.tianyin.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽象类定义模版方法流程，在抽象类的 doDrawExec() 中处理整个抽奖流程，并提供在流程中
 *               需要使用到的抽象方法，由DrawExecImpl服务逻辑中做具体实现
 * @author：Tianyin Zhang
 * @date: 2023/4/20
 */
@Slf4j
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec {

    @Resource
    IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExecute(DrawReq req) {

        // 1. 获取抽奖策略
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 2. 校验抽奖策略散射是否已初始化至内存
        this.checkAndInitRateData(strategy.getStrategyId(), strategy.getStrategyMode(), strategyRich.getStrategyDetailList());

        // 3. 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        // 4. 执行抽奖算法
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        String awardId = drawAlgorithm(strategy.getStrategyId(), drawAlgorithm, excludeAwardIds);

        // 5. 包装抽奖结果
        DrawResult drawResult = buildDrawResult(req.getUId(), strategy.getStrategyId(), awardId);
        return drawResult;
    }

    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
     * @param strategyId 策略ID
     * @return 排除的奖品IDList
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 执行抽奖算法
     * @param strategyId 策略ID
     * @param drawAlgorithm 抽奖算法模型
     * @param excludeAwardIds 排除的奖品ID
     * @return 奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds);

    /**
     * 检查抽奖策略是否已被初始化至内存
     * 仅当采用单项概率，需初始化散射表
     * @param strategyId 数据库中策略对应的ID
     * @param strategyMode 人为规定的映射
     * @param strategyDetailList 策略细节List
     */
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailVO> strategyDetailList) {

        // 获取对应的抽奖算法实现类
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        // 判断是否已经初始化过
        if (drawAlgorithm.isExistRateTuple(strategyId)) return;

        // 将 strategyDetailList 转换为 awardRateInfoList 实现初始化
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailVO strategyDetail : strategyDetailList)
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);

    }


    private DrawResult buildDrawResult(String uId, Long strategyId, String awardId) {

        if (null == awardId) {
            log.info("执行抽奖策略完成【未中奖】，用户：{}， 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }

        AwardBriefVO award = super.queryAwardInfoByAwardId(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardType(), award.getAwardName(), award.getAwardContent());
        log.info("执行抽奖策略完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称： {}", uId, strategyId, award.getAwardId(), award.getAwardName());

        return new DrawResult(uId, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);
    }

}
