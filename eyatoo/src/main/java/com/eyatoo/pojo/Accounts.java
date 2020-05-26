package com.eyatoo.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

//总账目类
public class Accounts {
    private String id;
    private Integer projectId;
    private Integer userId;
    private Integer buyDoctorId;
    private Integer doctorId;
    private Integer branchId;
    private String  Remarks;
    private Integer number;
    //    0 -- 报错
    //    1 -- 待支付
    //    2 -- 预约中(未就诊)
    //    3 -- 就诊中
    //    4 -- 待评价(已就诊)
    //    5 -- 已评价
    //    6 -- 已追评
    private Integer Status;
    /**
     *  finallayMoney 订单用户最终支付金额
     *  finallyPayOutpreMoney 扣除其他费用后,结算给门诊的金额
     *  shouldPayMoney 订单本应支付金额
     *  technologySupplyMoney 平台技术服务金额
     *  marketingMoney 营销金额
     *  othersMoney 其他金额
     * */
    private BigDecimal finallayMoney;
    private BigDecimal finallyPayOutpreMoney;
    private BigDecimal shouldPayMoney;
    private BigDecimal technologySupplyMoney;
    private BigDecimal marketingMoney;
    private BigDecimal othersMoney;
    //    0--报错
    //    1--未结算
    //    2--已结算
    private Integer forwardStatus;
    /** forwardType
     *  0:暂无打款账户类型
     *  1：个人账户类型
     *  2：公司账户类型
     * */
    private Integer forwardType;
    //就诊码
    private String treamentcode;
    private String medicalTime;
    //所使用的优惠券的经纪人id
    private Integer couponAgentId;
    private Date createTime;
    private Date lastUpdateTime;

    public String getTreamentcode() {
        return treamentcode;
    }

    public BigDecimal getFinallyPayOutpreMoney() {
        return finallyPayOutpreMoney;
    }

    public void setFinallyPayOutpreMoney(BigDecimal finallyPayOutpreMoney) {
        this.finallyPayOutpreMoney = finallyPayOutpreMoney;
    }

    public Integer getForwardType() {
        return forwardType;
    }

    public void setForwardType(Integer forwardType) {
        this.forwardType = forwardType;
    }

    public void setTreamentcode(String treamentcode) {
        this.treamentcode = treamentcode;
    }

    public Integer getCouponAgentId() {
        return couponAgentId;
    }

    public void setCouponAgentId(Integer couponAgentId) {
        this.couponAgentId = couponAgentId;
    }

    public Integer getBuyDoctorId() {
        return buyDoctorId;
    }

    public void setBuyDoctorId(Integer buyDoctorId) {
        this.buyDoctorId = buyDoctorId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(Integer forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

     public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }


    public String getMedicalTime() {
        return medicalTime;
    }

    public void setMedicalTime(String medicalTime) {
        this.medicalTime = medicalTime;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public BigDecimal getFinallayMoney() {
        return finallayMoney;
    }

    public void setFinallayMoney(BigDecimal finallayMoney) {
        this.finallayMoney = finallayMoney;
    }

    public BigDecimal getShouldPayMoney() {
        return shouldPayMoney;
    }

    public void setShouldPayMoney(BigDecimal shouldPayMoney) {
        this.shouldPayMoney = shouldPayMoney;
    }

    public BigDecimal getTechnologySupplyMoney() {
        return technologySupplyMoney;
    }

    public void setTechnologySupplyMoney(BigDecimal technologySupplyMoney) {
        this.technologySupplyMoney = technologySupplyMoney;
    }

    public BigDecimal getMarketingMoney() {
        return marketingMoney;
    }

    public void setMarketingMoney(BigDecimal marketingMoney) {
        this.marketingMoney = marketingMoney;
    }

    public BigDecimal getOthersMoney() {
        return othersMoney;
    }

    public void setOthersMoney(BigDecimal othersMoney) {
        this.othersMoney = othersMoney;
    }
}
