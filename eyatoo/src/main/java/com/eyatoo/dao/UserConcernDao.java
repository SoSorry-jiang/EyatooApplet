package com.eyatoo.dao;

import com.eyatoo.pojo.UserConcern;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserConcernDao {


    //当用户关注某一用户
    Integer userConcernedUser(@Param("userId")Integer user_id,@Param("concernedUser") Integer concerned_user);
    //当用户取消某一用户
    Integer userDisConcernedUser(@Param("userId")Integer user_id,@Param("concernedUser")Integer concerned_user);
    //当用户关注某一医生
    Integer userConcernedDoctor(@Param("userId")Integer user_id,@Param("concernedDoctor")Integer concerned_doctor);
    //当用户取消关注某一医生
    Integer userDisConcernedDoctor(@Param("userId")Integer user_id,@Param("concernedDoctor")Integer concerned_doctor);

    //查询单个用户所有关注的人
    List<UserConcern> getUserConcerntionList(Integer id);
    //查询单个用户所有粉丝
    List<UserConcern> getUserConcernedList(Integer id);

    //根据当前用户id或者医生id 和 目标用户或者医生id 判断是否已经关注过此人
    Integer isConcernedOrNot(Integer user_id,Integer doctor_id,Integer concerned_user,Integer concerned_doctor);
}
