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
        /** 成功 */
        SUCCESS("0000", "成功"),
        /** 未知失败 */
        UN_ERROR("0001", "未知失败"),
        /** 非法参数 */
        ILLEGAL_PARAMETER("0002", "非法参数"),
        /** 主键冲突 */
        INDEX_DUP("0003", "主键冲突"),
        /** SQL操作无更新 */
        NO_UPDATE("0004", "SQL操作无更新"),
        /** 未中奖 */
        LOSING_DRAW("D001", "未中奖"),
        /** 量化人群规则执行失败 */
        RULE_ERR("D002", "量化人群规则执行失败");

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
     * 全局属性
     */
    public static final class Global {

        /** 空节点值 */
        public static final Long TREE_NULL_NODE = 0L;

    }

    /**
     * 决策树节点
     */
    public static final class NodeType{

        /** 树茎 */
        public static final Integer STEM = 1;

        /** 果实 */
        public static final Integer FRUIT = 2;

    }

    /**
     * 规则限定类型
     */
    public static final class RuleLimitType {

        /** 等于 */
        public static final int EQUAL = 1;

        /** 大于 */
        public static final int GT = 2;

        /** 小于 */
        public static final int LT = 3;

        /** 大于&等于 */
        public static final int GE = 4;

        /** 小于&等于 */
        public static final int LE = 5;

        /** 枚举 */
        public static final int ENUM = 6;

    }

    /**
     * 活动状态类型
     */
    public enum ActivityState {

        /** 1. 编辑 */
        EDIT(1, "编辑"),

        /** 2. 提审 */
        ARRAIGNMENT(2, "提审"),

        /** 3. 撤审 */
        REVOKE(3, "撤审"),

        /** 4. 通过 */
        PASS(4, "通过"),

        /** 5. 运行（活动中） */
        DOING(5, "运行（活动中）"),

        /** 6. 拒绝 */
        REFUSE(6, "拒绝"),

        /** 7. 关闭 */
        CLOSE(7, "关闭"),

        /** 8. 开启 */
        OPEN(8, "开启");

        private Integer code;
        private String Info;

        ActivityState(Integer code, String info) {
            this.code = code;
            Info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return Info;
        }

        public void setInfo(String info) {
            Info = info;
        }
    }

    /** 策略模式，单项概率 或 总体概率 */
    public enum StrategyMode {

        /** 单项概率 */
        SINGLE(1, "单项概率"),

        /** 总体概率 */
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

    /** 中奖状态： 0未中奖，1中奖，2阳光普照奖 */
    public enum DrawState {
        /** 未中奖 */
        FAIL(0, "未中奖"),

        /** 中奖 */
        SUCCESS(1, "中奖"),

        /** 阳光普照奖 */
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

        /** 等待发奖 */
        WAIT(0, "等待发奖"),

        /** 发奖成功 */
        SUCCESS(1, "发奖成功"),

        /** 发奖失败 */
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

        /** 文字描述 */
        DESC(1, "文字描述"),

        /** 兑换码 */
        RedeemCodeGoods(2, "兑换码"),

        /** 优惠券 */
        CouponGoods(3, "优惠券"),

        /** 实物奖品 */
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

    /**
     * Ids 生成策略枚举
     */
    public enum Ids {
        /** 雪花算法 */
        SnowFlake,
        /** 日期算法 */
        ShortCode,
        /** 随机算法 */
        RandomNumeric;
    }

    /**
     * 活动单使用状态枚举类
     */
    public enum TaskState {
        /** 未使用 */
        NO_USED(0, "未使用"),

        /** 已使用 */
        USED(1, "已使用");

        private Integer code;
        private String info;

        TaskState(Integer code, String info) {
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
     * 发奖状态 0初始、1完成、2失败
     */
    public enum GrantState{

        INIT(0, "初始"),
        COMPLETE(1, "完成"),
        FAIL(2, "失败");

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
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
