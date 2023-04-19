package com.tianyin.lottery.domain.strategy.service.draw;

import com.tianyin.lottery.domain.strategy.model.req.DrawReq;
import com.tianyin.lottery.domain.strategy.model.res.DrawResult;

public interface IDrawExec {

    DrawResult doDrawExecute(DrawReq req);

}
