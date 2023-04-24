package com.tianyin.lottery.domain.strategy.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description: 策略简要信息
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
public class StrategyBriefVO {

    /** 策略ID */
    private Long strategyId;

    /** 策略描述 */
    private String strategyDesc;

    /** 策略方式（1. 单项概率 2. 总体概率） */
    private Integer strategyMode;

    /** 发奖方式（1. 即时 2. 定时 3. 人工）*/
    private Integer grantType;

    /** 发奖时间 */
    private Date grantDate;

    /** 扩展信息 */
    private String extInfo;

}
