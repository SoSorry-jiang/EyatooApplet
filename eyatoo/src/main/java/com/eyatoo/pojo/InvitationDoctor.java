package com.eyatoo.pojo;

import java.util.Date;

public class InvitationDoctor {
    private Integer id;
    private Integer doctorId;
    private Integer invitationBranchId;
//    0-报错
//    1-医生申请成为门诊医生
//    2-门诊邀请医生成为门诊医生
//    3-医生申请,门诊拒绝医生
//    4-医生申请,门诊同意该医生
//    5-门诊邀请医生，医生拒绝
//    6-门诊邀请医生，医生同意
    private Integer invitationStatus;
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

    public Integer getInvitationBranchId() {
        return invitationBranchId;
    }

    public void setInvitationBranchId(Integer invitationBranchId) {
        this.invitationBranchId = invitationBranchId;
    }

    public Integer getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(Integer invitationStatus) {
        this.invitationStatus = invitationStatus;
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
