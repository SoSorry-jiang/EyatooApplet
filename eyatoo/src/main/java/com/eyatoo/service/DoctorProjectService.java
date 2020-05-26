package com.eyatoo.service;

import com.eyatoo.pojo.DoctorProject;

import java.util.List;

public interface DoctorProjectService {
    //根据医生id 查询 该医生坐诊的项目
    List<DoctorProject> selectProFormDoctorById(Integer doctorId);
    //根据项目id 查询 该项目就诊的医生id
    List<DoctorProject> selectDoctorFormProById(Integer projectId);
    //根据项目类型，查询可做项目的医生的id
    List<Integer> selectDoctorDoPro(String proDo);
    //根据项目id和医生id 获取价格
    double getMoneyByDoctorAndBranchId(Integer doctorId, Integer projectId);
    //门诊端添加 项目就诊医生
    int addProjectDoctor(DoctorProject doctorProject);
    //门诊端查看 项目就诊医生
    List<DoctorProject> selectProjectDoctor(Integer branchId);
}
