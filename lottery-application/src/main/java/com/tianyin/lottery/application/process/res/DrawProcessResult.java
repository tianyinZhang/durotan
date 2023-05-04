package com.tianyin.lottery.application.process.res;

import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * @description: 抽奖结果
 * @author：Tianyin Zhang
 * @date: 2023/5/2
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }

}
