package com.eyatoo.pojo;

import java.math.BigDecimal;

public class PayBusinessRecord {
    private Integer id;
    //打款的门诊id
    private Integer payOutpreId;
    //打款的银行卡号
    private String payCardNumber;
    //打款的账户类型 0:错误 1:个人账户 2:公司账户
    private Integer payType;
    //打款金额
    private BigDecimal payMoney;
    //打款方式 0:错误  1:主动打款
    private Integer payMode;
    //打款状态说明
    private String payStatusRemarks;
    //打款时间
    private String payTime;
    //账单时间周期
    private String accountBeforeDate;
    private String accountAfterDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayCardNumber() {
        return payCardNumber;
    }

    public void setPayCardNumber(String payCardNumber) {
        this.payCardNumber = payCardNumber;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public String getPayStatusRemarks() {
        return payStatusRemarks;
    }

    public void setPayStatusRemarks(String payStatusRemarks) {
        this.payStatusRemarks = payStatusRemarks;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getPayOutpreId() {
        return payOutpreId;
    }

    public void setPayOutpreId(Integer payOutpreId) {
        this.payOutpreId = payOutpreId;
    }

    public String getAccountBeforeDate() {
        return accountBeforeDate;
    }

    public void setAccountBeforeDate(String accountBeforeDate) {
        this.accountBeforeDate = accountBeforeDate;
    }

    public String getAccountAfterDate() {
        return accountAfterDate;
    }

    public void setAccountAfterDate(String accountAfterDate) {
        this.accountAfterDate = accountAfterDate;
    }
}
