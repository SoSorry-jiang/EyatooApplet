package com.eyatoo.service;

import com.eyatoo.pojo.UserConcern;

import java.util.List;

public interface UserConcernService {
    //当用户关注某一用户
    Integer userConcernedUser(Integer user_id, Integer concerned_user);
    //当用户取消某一用户
    Integer userDisConcernedUser(Integer user_id, Integer concerned_user);
    //当用户关注某一医生
    Integer userConcernedDoctor(Integer user_id,Integer concerned_doctor);
    //当用户取消关注某一医生
    Integer userDisConcernedDoctor(Integer user_id,Integer concerned_doctor);
    //查询单个用户所有关注的人的用户id
    List<UserConcern> getUserConcerntionList(Integer id);
    //查询单个用户所有粉丝的用户id
    List<UserConcern> getUserConcernedList(Integer id);
    //根据当前用户id或者医生id 和 目标用户或者医生id 判断是否已经关注过此人
    Integer isConcernedOrNot(Integer user_id,Integer doctor_id,Integer concerned_user,Integer concerned_doctor);
}
