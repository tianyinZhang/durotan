package com.tianyin.lottery.rpc.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 活动DTO类，用于RPC调用传输数据，不同于PO类，PO类用于持久化数据，DTO类用于传输数据，两者不可混用，否则会出现问题
 * @author：Tianyin Zhang
 * @date: 2023/5/5
 */
@Data
public class AwardDTO implements Serializable {

    /** 用户ID */
    private String userId;

    /** 活动ID */
    private Long activityId;

    /** 活动名称 */
    private String activityName;

    /** 活动描述 */
    private String activityDesc;

    /** 开始时间 */
    private Date beginDateTime;

    /** 结束时间 */
    private Date endDateTime;

    /** 库存 */
    private Integer stockCount;

    /** 每人可参与次数 */
    private Integer takeCount;

    /** 活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启 */
    private Integer state;
}
