package com.eyatoo.pojo;

import java.math.BigInteger;
import java.util.Date;

public class DoctorCase {
    private Integer id;
    private Integer doctorId;
    private String doctorAvator;
    private String doctorName;
    private String content;
    private String beforePhoto;
    private String afterPhoto;
    private BigInteger praiseCount;
    private Date createTime;
    private Date lastUpdateTime;

    public String getDoctorAvator() {
        return doctorAvator;
    }

    public void setDoctorAvator(String doctorAvator) {
        this.doctorAvator = doctorAvator;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBeforePhoto() {
        return beforePhoto;
    }

    public void setBeforePhoto(String beforePhoto) {
        this.beforePhoto = beforePhoto;
    }

    public String getAfterPhoto() {
        return afterPhoto;
    }

    public void setAfterPhoto(String afterPhoto) {
        this.afterPhoto = afterPhoto;
    }

    public BigInteger getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(BigInteger praiseCount) {
        this.praiseCount = praiseCount;
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
