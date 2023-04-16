package com.tianyin.lottery.rpc;

import com.tianyin.lottery.rpc.req.ActivityReq;
import com.tianyin.lottery.rpc.res.ActivityRes;

public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);

}
