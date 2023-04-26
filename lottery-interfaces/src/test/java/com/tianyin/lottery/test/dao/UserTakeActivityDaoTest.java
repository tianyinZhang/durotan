package com.tianyin.lottery.test.dao;

import com.tianyin.lottery.infrastructure.dao.IUserTakeActivityDao;
import com.tianyin.lottery.infrastructure.po.UserTakeActivity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * @description: 测试用户领取活动表DAO层数据源切换功能
 * @author：Tianyin Zhang
 * @date: 2023/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTakeActivityDaoTest {

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;

    @Test
    public void test_insert() {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId("Uhdgkw766120d"); // 1库：Ukdli109op89oi 2库：Ukdli109op811d
        userTakeActivity.setTakeId(121019889410L);
        userTakeActivity.setActivityId(100001L);
        userTakeActivity.setActivityName("测试活动");
        userTakeActivity.setTakeDate(new Date());
        userTakeActivity.setTakeCount(10);
        userTakeActivity.setUuid("Uhdgkw766120d");

        userTakeActivityDao.insert(userTakeActivity);
    }

    /**
     * lombok注解与idea自动生成的getter/setter方法不同
     * 如属性名为uId，lombok注解生成的getter/setter方法为getuId/setuId
     * idea自动生成的getter/setter方法为getUId/setUId
     * BeanUtils.getProperty(userTakeActivity, "uId")会报错
     *
     * 解决方法有两种：
     * 1. 检测类上的注解，判断是否为lombok注解，如果是，则使用lombok注解生成的getter/setter方法
     * 2. 使用反射获取类的属性，避免使用BeanUtils.getProperty(userTakeActivity, "uId")
     */
    @Test
    public void test_lombok() {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        Class<?> clazz = UserTakeActivity.class;
        boolean hasDataAnnotation = clazz.isAnnotationPresent(Data.class);
        log.info("Class " + clazz.getName() + " has @Data annotation: " + hasDataAnnotation);

        try {
            log.info(BeanUtils.getProperty(userTakeActivity, "uId"));
        } catch (Exception e) {
            log.error("BeanUtils.getProperty(userTakeActivity, \"uId\")异常", e);
        }

        Field[] fields = userTakeActivity.getClass().getDeclaredFields();
        for (Field field : fields) {
            log.info(field.getName());
        }
    }

}
