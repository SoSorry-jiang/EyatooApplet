package com.eyatoo.pojo;

import java.util.Date;

/**
 * 门诊表类
 */
public class OutpatientPresentations {

    private int id;
    private String outpatientName;
    private String icon;
    private String province;
    private String city;
    private String area;
    private String address;
    private String phone;
    private String legalRepresentative;
    private String legalPersonIdcard;
    private String businessLicense;
    private String medicalAdvertisingReview;
    private String radiologyLicense;
    private String medicalInstitution;
    private int chainId;
    private int hospitalTypeId;
    private int outpatientProfileId;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutpatientName() {
        return outpatientName;
    }

    public void setOutpatientName(String outpatientName) {
        this.outpatientName = outpatientName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalPersonIdcard() {
        return legalPersonIdcard;
    }

    public void setLegalPersonIdcard(String legalPersonIdcard) {
        this.legalPersonIdcard = legalPersonIdcard;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getMedicalAdvertisingReview() {
        return medicalAdvertisingReview;
    }

    public void setMedicalAdvertisingReview(String medicalAdvertisingReview) {
        this.medicalAdvertisingReview = medicalAdvertisingReview;
    }

    public String getRadiologyLicense() {
        return radiologyLicense;
    }

    public void setRadiologyLicense(String radiologyLicense) {
        this.radiologyLicense = radiologyLicense;
    }

    public String getMedicalInstitution() {
        return medicalInstitution;
    }

    public void setMedicalInstitution(String medicalInstitution) {
        this.medicalInstitution = medicalInstitution;
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public int getHospitalTypeId() {
        return hospitalTypeId;
    }

    public void setHospitalTypeId(int hospitalTypeId) {
        this.hospitalTypeId = hospitalTypeId;
    }

    public int getOutpatientProfileId() {
        return outpatientProfileId;
    }

    public void setOutpatientProfileId(int outpatientProfileId) {
        this.outpatientProfileId = outpatientProfileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}