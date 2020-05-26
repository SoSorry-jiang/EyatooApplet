package com.eyatoo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

//用户表
public class User {
    //用户编号
    private Integer id;
    //用户openId
    private String openId;
    //用户名
    private String name;
    //头像
    private String txPhoto;
    //经纪人积分
    private Integer jjrPoints;
    //会员积分
    private Integer hyPoints;
    //诊疗金
    private Integer money;
    //创建时间
    private Date createTime;
    //最后一次修改的时间
    private Date lastUpdateTime;
    //是否就诊
    private Integer isTreatment;
    //是否为经纪人
    /**
     * status: 0:不是经纪人 1:是经纪人 2:审核中 3:审核失败
     * */
    private Integer isAgent;
    //用户电话号码
    private BigInteger phone;
    //二维码
    private String qrCode;
    //身份证
    private String idCard;
    //真实姓名
    private String trueName;
    //用户默认地址
    private Integer userDefaultAddress;


    //已邀请成功就诊的人数
    private Integer countSuccessMedPeople;
    //成功就诊后，最后一次就诊的商品标题
    private String lastTreatmentProjectTitle;

    private Integer jjrLevel;

    private Integer jjrConuntPoints;

    //是否已经领取过新人礼包
    private Integer isReceiveNewPackage;

    public Integer getIsReceiveNewPackage() {
        return isReceiveNewPackage;
    }

    public void setIsReceiveNewPackage(Integer isReceiveNewPackage) {
        this.isReceiveNewPackage = isReceiveNewPackage;
    }

    public String getLastTreatmentProjectTitle() {
        return lastTreatmentProjectTitle;
    }

    public void setLastTreatmentProjectTitle(String lastTreatmentProjectTitle) {
        this.lastTreatmentProjectTitle = lastTreatmentProjectTitle;
    }

    public Integer getCountSuccessMedPeople() {
        return countSuccessMedPeople;
    }

    public void setCountSuccessMedPeople(Integer countSuccessMedPeople) {
        this.countSuccessMedPeople = countSuccessMedPeople;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }



    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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

    public Integer getIsTreatment() {
        return isTreatment;
    }

    public void setIsTreatment(Integer isTreatment) {
        this.isTreatment = isTreatment;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxPhoto() {
        return txPhoto;
    }

    public void setTxPhoto(String txPhoto) {
        this.txPhoto = txPhoto;
    }

    public Integer getJjrPoints() {
        return jjrPoints;
    }

    public void setJjrPoints(Integer jjrPoints) {
        this.jjrPoints = jjrPoints;
    }

    public Integer getHyPoints() {
        return hyPoints;
    }

    public void setHyPoints(Integer hyPoints) {
        this.hyPoints = hyPoints;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public Integer getJjrLevel() {
        return jjrLevel;
    }

    public void setJjrLevel(Integer jjrLevel) {
        this.jjrLevel = jjrLevel;
    }

    public Integer getJjrConuntPoints() {
        return jjrConuntPoints;
    }

    public void setJjrConuntPoints(Integer jjrConuntPoints) {
        this.jjrConuntPoints = jjrConuntPoints;
    }

    public Integer getUserDefaultAddress() {
        return userDefaultAddress;
    }

    public void setUserDefaultAddress(Integer userDefaultAddress) {
        this.userDefaultAddress = userDefaultAddress;
    }
}
