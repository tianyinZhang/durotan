package com.tianyin.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 中奖奖品信息
 * @author：Tianyin Zhang
 * @date: 2023/4/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawAwardInfo {

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品类型
     */
    private Integer awardType;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品内容
     */
    private String awardContent;
}
