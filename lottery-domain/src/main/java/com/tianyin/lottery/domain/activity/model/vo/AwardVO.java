package com.tianyin.lottery.domain.activity.model.vo;

import lombok.Data;

/**
 * @description: 奖品信息
 * @author：Tianyin Zhang
 * @date: 2023/4/23
 */
@Data
public class AwardVO {

    /** 奖品ID */
    private String awardId;

    /** 奖品类型 */
    private Integer awardType;

    /** 奖品名称 */
    private String awardName;

    /** 奖品内容 */
    private String awardContent;



}
