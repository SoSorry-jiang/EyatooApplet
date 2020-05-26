package com.eyatoo.pojo;

import java.math.BigDecimal;
import java.util.Date;

//医生可做项目表   因为一个医生可以做多个项目，所以单独为一表
public class DoctorProject {

    private int id;
    private int doctorId;
    private int doctorProjectId;
    private int branchId;
    private Date createTime;
    private Date lastUpdateTime;
    private BigDecimal projectMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDoctorProjectId() {
        return doctorProjectId;
    }

    public void setDoctorProjectId(int doctorProjectId) {
        this.doctorProjectId = doctorProjectId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public BigDecimal getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(BigDecimal projectMoney) {
        this.projectMoney = projectMoney;
    }
}
