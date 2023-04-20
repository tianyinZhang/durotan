package com.tianyin.lottery.common;

import lombok.Data;

/**
 * 枚举状态码定义信息
 */
public class Constants {

    /**
     * 响应状态码
     */
    public enum ResponseCode {
        /**
         * 成功
         */
        SUCCESS("0000", "成功"),
        /**
         * 未知失败
         */
        UN_ERROR("0001", "未知失败"),
        /**
         * 非法参数
         */
        ILLEGAL_PARAMETER("0002", "非法参数"),
        /**
         * 主键冲突
         */
        INDEX_DUP("0003", "主键冲突");

        private String code;
        private String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    /**
     * 策略模式，单项概率 或 总体概率
     */
    public enum StrategyMode {

        /**
         * 单项概率
         */
        SINGLE(1, "单项概率"),

        /**
         * 总体概率
         */
        ENTIRETY(2, "总体概率");

        private Integer code;
        private String info;

        StrategyMode(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    /**
     * 中奖状态： 0未中奖，1中奖，2阳光普照奖
     */
    public enum DrawState {
        /**
         * 未中奖
         */
        FAIL(0, "未中奖"),

        /**
         * 中奖
         */
        SUCCESS(1, "中奖"),

        /**
         * 阳光普照奖
         */
        Cover(2, "阳光普照奖");

        private Integer code;
        private String info;

        DrawState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }



}
