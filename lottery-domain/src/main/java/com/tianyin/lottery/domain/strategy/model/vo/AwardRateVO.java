package com.tianyin.lottery.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardRateVO {

    // 奖品ID
    private String awardId;

    // 中奖概率
    private BigDecimal awardRate;

}
