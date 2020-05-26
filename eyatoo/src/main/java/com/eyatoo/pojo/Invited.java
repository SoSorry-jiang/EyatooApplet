package com.eyatoo.pojo;

import java.util.Date;

//邀请表
public class Invited {
    private Integer id;
    private Integer userId;
    private Integer doctorId;
    private Integer invitedPrsId;
    private Integer invitedStatus;
    private Date createTime;
    private Date lastUpdateTime;


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvitedPrsId() {
        return invitedPrsId;
    }

    public void setInvitedPrsId(Integer invitedPrsId) {
        this.invitedPrsId = invitedPrsId;
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

    public Integer getInvitedStatus() {
        return invitedStatus;
    }

    public void setInvitedStatus(Integer invitedStatus) {
        this.invitedStatus = invitedStatus;
    }
}
