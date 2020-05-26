package com.eyatoo.pojo;


import java.util.Date;

public class ProjectMedicalAdvertisement {

    private int id;
    private String title;
    private String path;
    private Double price;
    private int salesVolume;
    private String storeName;
    private int collection;
    private String city;
    private int collections;
    private int medicalTreatmentProjectId;
    private int outpatientTypeId;
    private int technologyTypeId;
    private int otherTypeId;
    private int projectDetailsId;
    private Date createTime;
    private Date updatetime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMedicalTreatmentProjectId() {
        return medicalTreatmentProjectId;
    }

    public void setMedicalTreatmentProjectId(int medicalTreatmentProjectId) {
        this.medicalTreatmentProjectId = medicalTreatmentProjectId;
    }

    public int getOutpatientTypeId() {
        return outpatientTypeId;
    }

    public void setOutpatientTypeId(int outpatientTypeId) {
        this.outpatientTypeId = outpatientTypeId;
    }

    public int getTechnologyTypeId() {
        return technologyTypeId;
    }

    public void setTechnologyTypeId(int technologyTypeId) {
        this.technologyTypeId = technologyTypeId;
    }

    public int getOtherTypeId() {
        return otherTypeId;
    }

    public void setOtherTypeId(int otherTypeId) {
        this.otherTypeId = otherTypeId;
    }

    public int getProjectDetailsId() {
        return projectDetailsId;
    }

    public void setProjectDetailsId(int projectDetailsId) {
        this.projectDetailsId = projectDetailsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }
}
