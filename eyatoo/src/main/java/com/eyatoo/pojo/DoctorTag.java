package com.eyatoo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//医生标签表
public class DoctorTag {
    private Integer id;
    private Integer doctorId;
    private String doctorTagName;
    private Date createTime;
    private Date lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorTagName() {
        return doctorTagName;
    }

    public void setDoctorTagName(String doctorTagName) {
        this.doctorTagName = doctorTagName;
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
