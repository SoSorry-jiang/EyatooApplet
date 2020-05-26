package com.eyatoo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//经纪人类
public class Agent {
    private  Integer id;
    private  Integer userId;
    private  Integer agentId;
    private  Integer agentDoctorId;
    private  Date createTime;
    private  Date lastUpdateTime;

    public Integer getAgentDoctorId() {
        return agentDoctorId;
    }

    public void setAgentDoctorId(Integer agentDoctorId) {
        this.agentDoctorId = agentDoctorId;
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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
