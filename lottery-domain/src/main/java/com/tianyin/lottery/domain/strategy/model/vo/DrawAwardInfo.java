package com.tianyin.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
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
     * 奖品名称
     */
    private String awardName;
}
