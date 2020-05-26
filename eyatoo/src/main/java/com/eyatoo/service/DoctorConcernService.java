package com.eyatoo.service;

import com.eyatoo.pojo.UserConcern;

import java.util.List;

public interface DoctorConcernService {
    //当用户关注某一用户
    Integer doctorConcernedUser(Integer doctor_id,Integer concerned_user);
    //当用户取消关注某一用户
    Integer doctorDisConcernedUser(Integer doctor_id,Integer concerned_user);
    //当医生关注某一医生
    Integer doctorConcernedDoctor(Integer doctor_id,Integer concerned_doctor);
    //当医生关注某一医生
    Integer doctorDisConcernedDoctor(Integer doctor_id,Integer concerned_doctor);
    //查询单个用户所有关注的人的用户id
    List<UserConcern> getDoctorConcerntionList(Integer id);
    //查询单个用户所有粉丝的用户id
    List<UserConcern> getDoctorConcernedList(Integer id);
}
