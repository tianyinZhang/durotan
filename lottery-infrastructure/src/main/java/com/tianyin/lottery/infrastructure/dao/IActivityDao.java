package com.tianyin.lottery.infrastructure.dao;

import com.tianyin.lottery.domain.activity.model.vo.AlterStateVO;
import com.tianyin.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IActivityDao {

    /**
     * 插入数据
     *
     * @param req   入参
     */
    void insert(Activity req);

    /**
     * 根据活动号查询活动信息
     *
     * @param activityId    活动号
     * @return              活动信息
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return              更新数量
     */
    int alterStatus(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     *
     * @param activityId    活动ID
     * @return              更新数量
     */
    int subtractionActivityStock(long activityId);

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     *
     * @param id            ID
     * @return              待处理的活动集合
     */
    List<Activity> scanToDoActivityList(Long id);

}
