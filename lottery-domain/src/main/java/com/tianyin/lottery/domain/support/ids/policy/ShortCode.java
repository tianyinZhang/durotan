package com.tianyin.lottery.domain.support.ids.policy;

import com.tianyin.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Random;

/**
 * @description: 利用时间戳加随机数生成ID，仅支持很小的调用量，用于生成活动配置类编号，保证全局唯一
 * @author：Tianyin Zhang
 * @date: 2023/4/25
 */
@Component
public class ShortCode implements IIdGenerator {

    @Override
    public synchronized long nextId() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        StringBuilder idStr = new StringBuilder();
        idStr.append(year - 2020);
        idStr.append(hour);
        idStr.append(String.format("%02d", week));
        idStr.append(day);
        // 平均每小时调用38次时，重复概率为50%
        idStr.append(String.format("%03d", new Random().nextInt(1000)));

        return Long.parseLong(idStr.toString());
    }

}
