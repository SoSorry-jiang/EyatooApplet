package com.eyatoo.pojo;

import java.util.Date;

public class UserConcern {
    private Integer id;
    private Integer concernedUser;
    private Integer userId;
    private Integer concernedDoctor;
    private Integer doctorId;
    private Date createTime;

    public Integer getConcernedDoctor() {
        return concernedDoctor;
    }

    public void setConcernedDoctor(Integer concernedDoctor) {
        this.concernedDoctor = concernedDoctor;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConcernedUser() {
        return concernedUser;
    }

    public void setConcernedUser(Integer concernedUser) {
        this.concernedUser = concernedUser;
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
}
