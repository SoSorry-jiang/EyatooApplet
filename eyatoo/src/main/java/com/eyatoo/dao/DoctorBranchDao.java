package com.eyatoo.dao;

import com.eyatoo.pojo.DoctorBranch;

import java.util.List;

public interface DoctorBranchDao
{
    //添加医生就诊分店
    Integer addDoctorBranch(DoctorBranch doctorBranch);
    //根据医生id查找坐诊医院id列表
    List<Integer> getBranchByDoctor(Integer id);
    //根据坐诊医院查找医生id列表
    List<Integer> getDoctorByBranch(Integer id);
    //根据医生id查找当前医生坐诊医院id
    Integer getOneDoctorWorkPlace(Integer doctoorId);
    //根据医生id修改医生坐诊医院
    Integer updateDoctorWorkPlace(Integer branchId,Integer doctoorId,Integer workStatus);
    //门诊端 删除就诊医生
    Integer delDoctorInBranch(Integer doctorId,Integer branchId,Integer outPrsId);
}
