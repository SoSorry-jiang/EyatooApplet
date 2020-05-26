package com.eyatoo.pojo;

import java.util.Date;

/**
 * 诊疗项目分店表
 */
public class ProjectBranch {

    private int id;
    private String storeName;
    private String address;
    private String longitude;
    private String latitude;
    private String workingday;
    private String businesshours;
    private String phone;
    private int outpatientPresentationsId;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWorkingday() {
        return workingday;
    }

    public void setWorkingday(String workingday) {
        this.workingday = workingday;
    }

    public String getBusinesshours() {
        return businesshours;
    }

    public void setBusinesshours(String businesshours) {
        this.businesshours = businesshours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOutpatientPresentationsId() {
        return outpatientPresentationsId;
    }

    public void setOutpatientPresentationsId(int outpatientPresentationsId) {
        this.outpatientPresentationsId = outpatientPresentationsId;
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