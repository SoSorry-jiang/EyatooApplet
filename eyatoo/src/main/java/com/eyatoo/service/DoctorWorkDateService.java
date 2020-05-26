package com.eyatoo.service;

import com.eyatoo.pojo.DoctorWorkDate;

public interface DoctorWorkDateService {
    //添加医生医生坐诊日期
    Integer addDoctorWorkDate(DoctorWorkDate doctorWorkDate);
    //修改医生坐诊日期
    Integer updateDoctorWorkDate(DoctorWorkDate doctorWorkDate);
    //删除医生以前的坐诊日期
    Integer delDoctorWorkDate(Integer id);
    //查询医生已经设置好的工作日期还有地点名
    DoctorWorkDate getDefaultDoctorWorkDate(Integer doctorId);
    //验证医生是否已经创建了工作日期
    Integer getCountByDoctorWorkDate(Integer doctorId);
    //判断当前日期是否已经超过工作日期
    Integer yzIsWorkDate(Integer doctorId,String date);
}
