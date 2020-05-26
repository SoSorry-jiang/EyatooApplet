package com.eyatoo.pojo;
/**
 * id 用户优惠券id
 * userId 优惠券所属用户id
 * couponId 优惠券id
 * couponStatus 优惠券状态
 * startTime 开始时间
 * endTime 最高有效时间
 * */
public class UserCoupon {
    private Integer id;
    private Integer userId;
    private Integer couponId;
    /** couponStatus 的几种状态
     *  0： 该用优惠券已过期
     *  1： 默认状态（可使用，未过期状态
     *  2： 转赠状态中（既拥有该优惠券的用户转赠给其他用户时，其他用户未接受时
     * */
    private Integer couponStatus;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }
}
