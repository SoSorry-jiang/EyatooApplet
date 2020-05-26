package com.eyatoo.pojo;

import java.util.Date;

//门诊表
public class Hospital {
    private  Integer id;
    private  String hospitalName;
    private  Integer hospitalPhone;
    private  Integer hospitalLandline;
    private  String  hospitalWorkingDay;
    private  String  hospitalBusinessHours;
    private  String  hospitalPlace;
    private  Date    createTime;
    private  Date    lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(Integer hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public Integer getHospitalLandline() {
        return hospitalLandline;
    }

    public void setHospitalLandline(Integer hospitalLandline) {
        this.hospitalLandline = hospitalLandline;
    }

    public String getHospitalWorkingDay() {
        return hospitalWorkingDay;
    }

    public void setHospitalWorkingDay(String hospitalWorkingDay) {
        this.hospitalWorkingDay = hospitalWorkingDay;
    }

    public String getHospitalBusinessHours() {
        return hospitalBusinessHours;
    }

    public void setHospitalBusinessHours(String hospitalBusinessHours) {
        this.hospitalBusinessHours = hospitalBusinessHours;
    }

    public String getHospitalPlace() {
        return hospitalPlace;
    }

    public void setHospitalPlace(String hospitalPlace) {
        this.hospitalPlace = hospitalPlace;
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
}
