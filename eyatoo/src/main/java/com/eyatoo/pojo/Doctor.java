package com.eyatoo.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;
//医生类
public class Doctor {
    private Integer id;
    private String doctorAvator;
    private String  doctorName;
    private String  doctorJob;
    private Integer doctorWorkYear;
    private BigInteger doctorPhone;
    private String doctorLoginPassword;
    /**
     *  doctorStatus 状态
     *  0：未审核
     *  1：审核成功
     *  2：审核失败
     * */
    private Integer doctorStatus;
    private Integer isAgent;
    private String  introduce;
    private Integer sex;
    private String diploma;
    private Integer bindUserId;
    private Date createTime;
    private Date lastUpdateTime;
    private Integer hospitalId;
    private String doctorEducation;
    private String doctorTag;
    private Integer jjrCoumtPoints;
    private Integer jjrPoints;
    private Integer hyPoints;
    private Integer jjrLevel;
    private Integer doctorDefaultAddress;
    private String personalInformation;
    private String registerRecord;
    private Integer isOurLandLocked;
    private String idCardPositive;
    private String idCardBack;
    private String startWorkTime;
    //二维码
    private String qrCode;
    private String id_card;
    private String true_name;
    //各种总数
    private Integer orderCount;
    private Integer communityCount;
    private Integer answerCount;
    private Integer fansCount;
    private Integer caseCount;

    private Integer countSuccessMedPeople;


    public Integer getCountSuccessMedPeople() {
        return countSuccessMedPeople;
    }

    public void setCountSuccessMedPeople(Integer countSuccessMedPeople) {
        this.countSuccessMedPeople = countSuccessMedPeople;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Integer getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(Integer doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    public String getIdCardPositive() {
        return idCardPositive;
    }

    public void setIdCardPositive(String idCardPositive) {
        this.idCardPositive = idCardPositive;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public Integer getIsOurLandLocked() {
        return isOurLandLocked;
    }

    public void setIsOurLandLocked(Integer isOurLandLocked) {
        this.isOurLandLocked = isOurLandLocked;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public BigInteger getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(BigInteger doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorLoginPassword() {
        return doctorLoginPassword;
    }

    public void setDoctorLoginPassword(String doctorLoginPassword) {
        this.doctorLoginPassword = doctorLoginPassword;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getCommunityCount() {
        return communityCount;
    }

    public void setCommunityCount(Integer communityCount) {
        this.communityCount = communityCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }


    public String getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation) {
        this.personalInformation = personalInformation;
    }

    public String getRegisterRecord() {
        return registerRecord;
    }

    public void setRegisterRecord(String registerRecord) {
        this.registerRecord = registerRecord;
    }

    public Integer getDoctorDefaultAddress() {
        return doctorDefaultAddress;
    }

    public void setDoctorDefaultAddress(Integer doctorDefaultAddress) {
        this.doctorDefaultAddress = doctorDefaultAddress;
    }

    public String getDoctorAvator() {
        return doctorAvator;
    }

    public void setDoctorAvator(String doctorAvator) {
        this.doctorAvator = doctorAvator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorJob() {
        return doctorJob;
    }

    public void setDoctorJob(String doctorJob) {
        this.doctorJob = doctorJob;
    }

    public Integer getDoctorWorkYear() {
        return doctorWorkYear;
    }

    public void setDoctorWorkYear(Integer doctorWorkYear) {
        this.doctorWorkYear = doctorWorkYear;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
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

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDoctorEducation() {
        return doctorEducation;
    }

    public void setDoctorEducation(String doctorEducation) {
        this.doctorEducation = doctorEducation;
    }

    public String getDoctorTag() {
        return doctorTag;
    }

    public void setDoctorTag(String doctorTag) {
        this.doctorTag = doctorTag;
    }

    public Integer getBindUserId() {
        return bindUserId;
    }

    public void setBindUserId(Integer bindUserId) {
        this.bindUserId = bindUserId;
    }

    public Integer getJjrCoumtPoints() {
        return jjrCoumtPoints;
    }

    public void setJjrCoumtPoints(Integer jjrCoumtPoints) {
        this.jjrCoumtPoints = jjrCoumtPoints;
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

    public Integer getJjrLevel() {
        return jjrLevel;
    }

    public void setJjrLevel(Integer jjrLevel) {
        this.jjrLevel = jjrLevel;
    }
}

