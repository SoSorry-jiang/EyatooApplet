package com.eyatoo.service.impl;

import com.eyatoo.dao.InvitationDoctorDao;
import com.eyatoo.pojo.InvitationDoctor;
import com.eyatoo.service.InvitationDoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InvitationDoctorServiceImpl implements InvitationDoctorService {

    @Resource
    private InvitationDoctorDao invitationDoctorDao;

    @Override
    public Integer addInvitationDoctor(InvitationDoctor invitationDoctor) {
        Integer isOK = 0;
        try {
            isOK = invitationDoctorDao.addInvitationDoctor(invitationDoctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<InvitationDoctor> invitationDoctorList(Integer branchId, Integer outPrsId) {
        List<InvitationDoctor> invitationDoctors = new ArrayList<>();
        try {
               invitationDoctors = invitationDoctorDao.invitationDoctorList(branchId, outPrsId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return invitationDoctors;
    }

    @Override
    public List<Map<String, Object>> invitationBranchList(Integer doctorId) {
       List<Map<String,Object>> invitationBranchs = new ArrayList<>();
       try {
           invitationBranchs = invitationDoctorDao.invitationBranchList(doctorId);
       }catch (Exception e){
           e.printStackTrace();
       }
        return invitationBranchs;
    }

    @Override
    public Integer yzIsReady(Integer branchId, Integer doctorId) {
        Integer isOK = 0;
        try {
           isOK = invitationDoctorDao.yzIsReady(branchId, doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateStatus(Integer branchId, Integer doctorId, Integer status) {
        Integer isOK = 0;
        try {
           isOK = invitationDoctorDao.updateStatus(branchId, doctorId, status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
