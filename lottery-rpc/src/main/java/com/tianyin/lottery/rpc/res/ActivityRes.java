package com.tianyin.lottery.rpc.res;

import com.tianyin.lottery.common.Result;
import com.tianyin.lottery.rpc.dto.ActivityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ActivityRes implements Serializable {

    private Result result;

    private ActivityDto activity;

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

}
