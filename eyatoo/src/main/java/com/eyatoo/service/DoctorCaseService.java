package com.eyatoo.service;

import com.eyatoo.pojo.DoctorCase;

import java.util.List;

public interface DoctorCaseService {
    //医生添加案例
    Integer addDoctorCase(DoctorCase doctorCase);
    //医生删除案例
    Integer deleteDoctorCase(Integer id);
    //查看单个医生的所有案例
    List<DoctorCase> getOneDoctorAllCase(Integer doctorId);
    //加载某一个案例
    DoctorCase getOneCase(Integer id);
    //更新点赞
    Integer updatePraise(Integer id,Integer praise);

    //更新图片
    Integer updatePhoto(Integer id,String path);
}
