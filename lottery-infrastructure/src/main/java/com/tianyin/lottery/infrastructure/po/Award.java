package com.tianyin.lottery.infrastructure.po;

import lombok.Data;

import java.util.Date;

/**
 * 奖品的持久化对象，对应于数据库中的一条记录
 */
@Data
public class Award {

    // 自增ID
    private Long id;

    // 奖品ID
    private String awardId;

    // 奖品类型
    private Integer awardType;

    // 奖品数量
    private Integer awardCount;

    // 奖品名称
    private String awardName;

    // 奖品内容
    private String awardContent;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

}
