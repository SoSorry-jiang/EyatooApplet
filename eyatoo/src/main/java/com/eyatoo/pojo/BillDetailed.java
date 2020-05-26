package com.eyatoo.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BillDetailed {
    private Integer id;
    private Integer transactionBranchId;

    /**  transactionType 账目类型（支出or转入）
     *   0:报错
     *   1：收入
     *   2：支出
     * */
    private Integer transactionType;
    //备注
    private String  transactionRemarks;
    //资金流入
    private BigDecimal transactionAddMoney;
    //资金流出
    private BigDecimal transactionReduceMoney;
    //交易日期
    private Date transactionTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public BigDecimal getTransactionAddMoney() {
        return transactionAddMoney;
    }

    public void setTransactionAddMoney(BigDecimal transactionAddMoney) {
        this.transactionAddMoney = transactionAddMoney;
    }

    public BigDecimal getTransactionReduceMoney() {
        return transactionReduceMoney;
    }

    public void setTransactionReduceMoney(BigDecimal transactionReduceMoney) {
        this.transactionReduceMoney = transactionReduceMoney;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getTransactionBranchId() {
        return transactionBranchId;
    }

    public void setTransactionBranchId(Integer transactionBranchId) {
        this.transactionBranchId = transactionBranchId;
    }
}
