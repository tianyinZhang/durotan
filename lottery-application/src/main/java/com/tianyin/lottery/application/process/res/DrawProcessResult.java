package com.tianyin.lottery.application.process.res;

import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @description: 抽奖结果
 * @author：Tianyin Zhang
 * @date: 2023/5/2
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }

}
