package com.tianyin.lottery.domain.activity.service.partake;

import com.tianyin.lottery.domain.activity.model.req.PartakeReq;
import com.tianyin.lottery.domain.activity.model.vo.ActivityBillVO;
import com.tianyin.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 活动领取操作，通用的数据服务
 * @author：Tianyin Zhang
 * @date: 2023/4/28
 */
public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }

}
