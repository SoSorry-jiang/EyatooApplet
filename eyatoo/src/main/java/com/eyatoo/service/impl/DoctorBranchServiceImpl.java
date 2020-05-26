package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorBranchDao;
import com.eyatoo.pojo.DoctorBranch;
import com.eyatoo.service.DoctorBranchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorBranchServiceImpl implements DoctorBranchService {
    @Resource
    private DoctorBranchDao doctorBranchDao;

    @Override
    public List<Integer> getBranchByDoctor(Integer id) {
        List<Integer> idList = new ArrayList<>();
        try {
            idList = doctorBranchDao.getBranchByDoctor(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public List<Integer> getDoctorByBranch(Integer id) {
        List<Integer> idList = new ArrayList<>();
        try {
            idList = doctorBranchDao.getDoctorByBranch(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public Integer getOneDoctorWorkPlace(Integer doctoorId) {
        Integer branchId = 0;
        try {
             branchId = doctorBranchDao.getOneDoctorWorkPlace(doctoorId);
        }catch (Exception e){
             e.printStackTrace();
        }
        return branchId;
    }

    @Override
    public Integer updateDoctorWorkPlace(Integer branchId, Integer doctoorId, Integer workStatus) {
        Integer isOk = 0;
        try {
            isOk =  doctorBranchDao.updateDoctorWorkPlace(branchId,doctoorId,workStatus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer delDoctorInBranch(Integer doctorId, Integer branchId, Integer outPrsId) {
        Integer isOK = 0;
        try {
            isOK = doctorBranchDao.delDoctorInBranch(doctorId, branchId, outPrsId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer addDoctorBranch(DoctorBranch doctorBranch) {
        Integer isOK = 0;
        try {
             isOK = doctorBranchDao.addDoctorBranch(doctorBranch);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
