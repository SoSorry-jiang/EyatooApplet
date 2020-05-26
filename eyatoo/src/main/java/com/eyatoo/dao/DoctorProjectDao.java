package com.eyatoo.dao;

import com.eyatoo.pojo.DoctorProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorProjectDao {
    //根据医生id 查询 该医生坐诊的项目
    List<DoctorProject> selectProFormDoctorById(@Param("doctorId") Integer doctorId);
    //根据项目id 查询 该项目就诊的医生id
    List<DoctorProject> selectDoctorFormProById(@Param("projectId") Integer projectId);
    //根据项目类型，查询可做项目的医生的id
    List<Integer> selectDoctorDoPro(String proDo);
    //根据项目id和医生id 获取价格
    double getMoneyByDoctorAndBranchId(@Param("doctorId") Integer doctorId, @Param("projectId") Integer projectId);

    //门诊端添加 项目就诊医生
    int addProjectDoctor(DoctorProject doctorProject);
    //门诊端查看 项目就诊医生
    List<DoctorProject> selectProjectDoctor(Integer branchId);
}
