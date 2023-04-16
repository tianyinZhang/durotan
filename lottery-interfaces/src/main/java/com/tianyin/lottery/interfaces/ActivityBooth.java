package com.tianyin.lottery.interfaces;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.infrastructure.dao.IActivityDao;
import com.tianyin.lottery.infrastructure.po.Activity;
import com.tianyin.lottery.rpc.IActivityBooth;
import com.tianyin.lottery.rpc.dto.ActivityDto;
import com.tianyin.lottery.rpc.req.ActivityReq;
import com.tianyin.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
public class ActivityBooth implements IActivityBooth {

    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

        Activity activity = activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto = new ActivityDto();

        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()), activityDto);
    }

}
