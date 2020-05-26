package com.eyatoo.pojo;

import java.util.Date;

public class Remittance {
    private Integer id;
    private Integer branchId;
    private Integer remittanceMoney;
    private Integer remittanceMode;
    private Integer paymentStatus;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getRemittanceMoney() {
        return remittanceMoney;
    }

    public void setRemittanceMoney(Integer remittanceMoney) {
        this.remittanceMoney = remittanceMoney;
    }

    public Integer getRemittanceMode() {
        return remittanceMode;
    }

    public void setRemittanceMode(Integer remittanceMode) {
        this.remittanceMode = remittanceMode;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
