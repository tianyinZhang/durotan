package com.tianyin.lottery.domain.activity.model.aggregates;

import com.tianyin.lottery.domain.activity.model.vo.ActivityVO;
import com.tianyin.lottery.domain.activity.model.vo.AwardVO;
import com.tianyin.lottery.domain.activity.model.vo.StrategyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 活动配置聚合信息
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityConfigRich {

    /**
     * 活动配置
     */
    private ActivityVO activity;

    /**
     * 策略配置（包含明细）
     */
    private StrategyVO strategy;

    /**
     * 奖品配置
     */
    private List<AwardVO> awardList;

}
