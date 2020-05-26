package com.eyatoo.service;

import com.eyatoo.pojo.DoctorAnswer;

import java.util.List;

public interface DoctorAnswerService {
    //医生回答问题
    Integer addAnswer(DoctorAnswer doctorAnswer);

    //医生删除回答
    Integer deleteAnswer(Integer id);

    //根据医生id查找该医生的所有回答
    List<DoctorAnswer> getOneDoctorAnswer(Integer doctorId);

    //加载单个回答
    DoctorAnswer getOneAnswer(Integer id);

    //根据问题id 加载回答列表
    List<DoctorAnswer> loginAnswerList(Integer questionId);

    //更新图片
    Integer updatePhoto(Integer id,String path);

    //更新点赞
    Integer updatePraise(Integer id,Integer praise);
}
