package com.eyatoo.dao;

import com.eyatoo.pojo.InvitationDoctor;

import java.util.List;
import java.util.Map;

public interface InvitationDoctorDao {
    //门诊端 门诊邀请医生
    Integer addInvitationDoctor(InvitationDoctor invitationDoctor);

    //根据门诊端id 或总门诊id，获取该门诊下医生管理状态
    List<InvitationDoctor>  invitationDoctorList(Integer branchId,Integer outPrsId);

    //医生端 医生查看所有受到的门诊邀请
    List<Map<String,Object>> invitationBranchList(Integer doctorId);

    //查询是否该医生申请成为就诊医生，或者是否门诊已经邀请该医生
    Integer yzIsReady(Integer branchId,Integer doctorId);

    //医生或门诊 同意或拒绝成为就诊医生
    Integer updateStatus(Integer branchId,Integer doctorId,Integer status);
}
