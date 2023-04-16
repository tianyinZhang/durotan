package com.tianyin.lottery.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象中的Code码、Info描述
 */
@AllArgsConstructor
@Data
public class Result implements Serializable {

    private static final long serivalVersionUID = -3826891916021780628L;
    private String code;
    private String info;

    public static Result buildResult(String code, String info) {
        return new Result(code, info);
    }

    public static Result buildSuccessResult() {
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

}
