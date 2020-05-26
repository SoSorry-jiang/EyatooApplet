package com.eyatoo.pojo;

import java.util.Date;

//商品表
public class SyMedicalAdvertisement {
    private Integer id;
    private String title;
    private String path;
    private double price;
    private Integer salesVolume;
    private String storeName;
    private String city;
    private Integer medicalTreatmentProjectId;
    private Integer outpatientTypeId;
    private Integer technologyTypeId;
    private Integer otherTypeId;
    private Integer projectDetailsId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMedicalTreatmentProjectId() {
        return medicalTreatmentProjectId;
    }

    public void setMedicalTreatmentProjectId(Integer medicalTreatmentProjectId) {
        this.medicalTreatmentProjectId = medicalTreatmentProjectId;
    }

    public Integer getOutpatientTypeId() {
        return outpatientTypeId;
    }

    public void setOutpatientTypeId(Integer outpatientTypeId) {
        this.outpatientTypeId = outpatientTypeId;
    }

    public Integer getTechnologyTypeId() {
        return technologyTypeId;
    }

    public void setTechnologyTypeId(Integer technologyTypeId) {
        this.technologyTypeId = technologyTypeId;
    }

    public Integer getOtherTypeId() {
        return otherTypeId;
    }

    public void setOtherTypeId(Integer otherTypeId) {
        this.otherTypeId = otherTypeId;
    }

    public Integer getProjectDetailsId() {
        return projectDetailsId;
    }

    public void setProjectDetailsId(Integer projectDetailsId) {
        this.projectDetailsId = projectDetailsId;
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
