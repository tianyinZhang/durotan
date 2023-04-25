package com.tianyin.lottery.domain.support.ids.policy;

import com.tianyin.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 使用 org.apache.commons.lang3.RandomStringUtils
 * @author：Tianyin Zhang
 * @date: 2023/4/25
 */
@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }

}
