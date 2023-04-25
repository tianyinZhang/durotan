package com.tianyin.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import com.tianyin.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: hutool 工具包下的雪花算法
 * @author：Tianyin Zhang
 * @date: 2023/4/25
 */
@Component
public class SnowFlake implements IIdGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 使用机器的IP地址作为workerId
        long workId;
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workId = NetUtil.getLocalhostStr().hashCode();
        }

        // 0 ~ 31 位，可以采用配置的方式使用
        workId = workId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = cn.hutool.core.util.IdUtil.createSnowflake(workId, dataCenterId);
    }

    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }

}
