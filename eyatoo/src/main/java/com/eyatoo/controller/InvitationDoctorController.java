package com.eyatoo.controller;

import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InvitationDoctorController {
    @Autowired
    private InvitationDoctorService invitationDoctorService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorBranchService doctorBranchService;

    @Autowired
    private ProjectBranchService projectBranchService;

    @Autowired
    private OutpatientPresentationsService outpatientPresentationsService;

    private WxChat wxChat = new WxChat();

    //门诊邀请医生，或者医生申请门诊
    @RequestMapping("invitationDoctor")
    private Integer invitationDoctor(InvitationDoctor invitationDoctor){
        /**
         *        #{doctorId},  被邀请医生id
         *        #{invitationBranchId}, 邀请该医生的门诊id
         *        #{invitationStatus} 邀请状态:
                                     0-报错
                                     1-医生申请成为门诊医生
                                     2-门诊邀请医生成为门诊医生
                                     3-医生申请,门诊拒绝医生
                                     4-医生申请,门诊同意该医生
                                     5-门诊邀请医生，医生拒绝
                                     6-门诊邀请医生，医生同意
         *        */
        ProjectBranch projectBranch = projectBranchService.getOneBranchById(invitationDoctor.getInvitationBranchId());
        OutpatientPresentations outpatientPresentations = outpatientPresentationsService.findmzbyoptId(projectBranch.getOutpatientPresentationsId());
        Doctor doctor = doctorService.getOneOfDoctorById(invitationDoctor.getDoctorId());
        String outPreId = outpatientPresentations.getId()+"";
         if(invitationDoctor.getInvitationStatus() == 1){
             String content = doctor.getDoctorName()+"申请成为门诊医生";

             wxChat.WxMiniSendMsg(invitationDoctor.getDoctorId().toString(),outPreId,"TIMTextElem",content);
         }else if(invitationDoctor.getInvitationStatus() == 2){
             String content = outpatientPresentations.getOutpatientName()+"邀请您成为门诊医生";
             wxChat.WxMiniSendMsg(outPreId,invitationDoctor.getDoctorId().toString(),"TIMTextElem",content);
         }
        return invitationDoctorService.addInvitationDoctor(invitationDoctor);
    }

    //门诊端 医生管理-邀请中医生
    @RequestMapping("getAllInvitationDoctor")
    private List<Map<String,Object>> getAllInvitationDoctor(@RequestParam(defaultValue = "0") Integer branchId, @RequestParam(defaultValue = "0")Integer outPrsId){
        List<Map<String,Object>> paramterList = new ArrayList<>();
        List<InvitationDoctor> invitationDoctors = invitationDoctorService.invitationDoctorList(branchId, outPrsId);
        for (InvitationDoctor invitation: invitationDoctors) {
               Map<String,Object> objectMap = new HashMap<>();
               Doctor doctor = doctorService.getOneOfDoctorById(invitation.getDoctorId());
               objectMap.put("doctor",doctor);
               objectMap.put("doctorStatus",invitation.getInvitationStatus());
               paramterList.add(objectMap);
        }
        return paramterList;
    }

    //医生端 医生查看自己的邀请
    @RequestMapping("getAllInvitationBranch")
    private List<Map<String,Object>> getAllInvitationBranch(Integer doctorId){
        return invitationDoctorService.invitationBranchList(doctorId);
    }

    //门诊端或医生端 门诊或医生同意或拒绝申请成为就诊医生
    @RequestMapping("updateBranchDoctorStatus")
    private Integer updateBranchDoctorStatus(Integer branchId,Integer doctorId,Integer status){
        if(status == 4 || status == 6){
            DoctorBranch doctorBranch = new DoctorBranch();
            doctorBranch.setDoctorId(doctorId);
            doctorBranch.setBranch_id(branchId);
            doctorBranchService.addDoctorBranch(doctorBranch);
        }
        return invitationDoctorService.updateStatus(branchId, doctorId, status);
    }

    //验证是否门诊端已经 邀请过或者已经申请过
    @RequestMapping("yzIsReady")
    private Integer yzIsReady(Integer branchId,Integer doctorId){
     return invitationDoctorService.yzIsReady(branchId,doctorId);
    }
}
