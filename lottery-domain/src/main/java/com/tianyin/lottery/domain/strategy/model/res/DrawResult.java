package com.tianyin.lottery.domain.strategy.model.res;

import com.tianyin.lottery.common.Constants;
import com.tianyin.lottery.domain.strategy.model.vo.DrawAwardVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 绘制结果对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResult {

    /**
     *  用户ID
     */
    private String uId;

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 中奖状态： 0 未中奖，1 已中奖，2 阳光普照奖
     */
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    /**
     * 奖品信息
     */
    private DrawAwardVO drawAwardInfo;

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }
}
