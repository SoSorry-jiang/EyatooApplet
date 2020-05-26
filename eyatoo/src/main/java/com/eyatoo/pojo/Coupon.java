package com.eyatoo.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * branchId 该优惠券所属门诊id
 * projectId 优惠指定项目的项目id
 * couponName  优惠券名称
 * couponType 优惠券类型
 * couponDemandMoney 需要达到多少钱才可使用
 * couponDiscountMoney 优惠金额
 * couponRebate 折扣
 * couponExplain 优惠券说明
 * couponConditionExplain 优惠券条件说明
 * startTime 开始时间
 * endTime 结束时间
 * */
public class Coupon {
    private Integer id;
    //如果门诊id等于0,就是我们自己发的优惠券
    private Integer branchId;
    private Integer projectId;
    private String couponName;
    /** couponType 优惠券类型
     *  0： 报错（无类型）
     *  1： 门诊通用
     *  2： 单个项目使用
     *  3： 全平台通用
     * */
    private Integer couponType;
    /** couponDiscountType
     *  1:  满减
     *  2:  折扣
     * */
    private Integer couponDiscountType;
    private BigDecimal couponDemandMoney;
    private BigDecimal couponDiscountMoney;
    private double couponRebate;
    private String couponExplain;
    private String couponConditionExplain;
    private String startTime;
    private String endTime;
    /**  isDrafts 是否为草稿箱
     *   0：不是
     *   1：是
     * */
    private Integer isDrafts;

    public Integer getCouponDiscountType() {
        return couponDiscountType;
    }

    public void setCouponDiscountType(Integer couponDiscountType) {
        this.couponDiscountType = couponDiscountType;
    }

    public Integer getIsDrafts() {
        return isDrafts;
    }

    public void setIsDrafts(Integer isDrafts) {
        this.isDrafts = isDrafts;
    }

    public String getCouponExplain() {
        return couponExplain;
    }

    public void setCouponExplain(String couponExplain) {
        this.couponExplain = couponExplain;
    }

    public String getCouponConditionExplain() {
        return couponConditionExplain;
    }

    public void setCouponConditionExplain(String couponConditionExplain) {
        this.couponConditionExplain = couponConditionExplain;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponDemandMoney() {
        return couponDemandMoney;
    }

    public void setCouponDemandMoney(BigDecimal couponDemandMoney) {
        this.couponDemandMoney = couponDemandMoney;
    }

    public BigDecimal getCouponDiscountMoney() {
        return couponDiscountMoney;
    }

    public void setCouponDiscountMoney(BigDecimal couponDiscountMoney) {
        this.couponDiscountMoney = couponDiscountMoney;
    }

    public double getCouponRebate() {
        return couponRebate;
    }

    public void setCouponRebate(double couponRebate) {
        this.couponRebate = couponRebate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
