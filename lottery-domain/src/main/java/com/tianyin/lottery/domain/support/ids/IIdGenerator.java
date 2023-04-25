package com.tianyin.lottery.domain.support.ids;

/**
 * @description: 生成ID接口类，所有的ID生成策略都要实现这个接口，然后在IdContext中注册，才能使用
 * @author：Tianyin Zhang
 * @date: 2023/4/25
 */
public interface IIdGenerator {

    /**
     * 生成ID
     *
     * @return ID
     */
    long nextId();
}
