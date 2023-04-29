package com.tianyin.lottery.domain.activity.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 参与活动请求
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartakeReq {

    /** 用户ID */
    private String uId;

    /** 活动ID */
    private Long activityId;

    /** 活动领取时间 */
    private Date partakeDate;

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = new Date();
    }

}
