package com.tianyin.lottery.common;

/**
 * @description: 枚举状态码定义信息
 * @author：Tianyin Zhang
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

    /**
     * 发奖状态
     * 0: 等待发放， 1： 发奖成功， 2: 发奖失败
     */
    public enum AwardState {

        /**
         * 等待发奖
         */
        WAIT(0, "等待发奖"),

        /**
         * 发奖成功
         */
        SUCCESS(1, "发奖成功"),

        /**
         * 发奖失败
         */
        FAILURE(2, "发奖失败");

        private Integer code;

        private String info;

        AwardState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

    }

    /**
     * 奖品类型
     * 1: 文字描述， 2: 兑换码， 3: 优惠券， 4: 实物奖品
     */
    public enum AwardType {

        /**
         * 文字描述
         */
        DESC(1, "文字描述"),

        /**
         * 兑换码
         */
        RedeemCodeGoods(2, "兑换码"),

        /**
         * 优惠券
         */
        CouponGoods(3, "优惠券"),

        /**
         * 实物奖品
         */
        PhysicalGoods(4, "实物奖品");

        private Integer code;

        private String info;

        AwardType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

}
