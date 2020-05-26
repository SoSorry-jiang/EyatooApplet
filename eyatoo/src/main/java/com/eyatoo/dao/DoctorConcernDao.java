package com.eyatoo.dao;

import com.eyatoo.pojo.UserConcern;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorConcernDao {
    //当用户关注某一用户
    Integer doctorConcernedUser(@Param("doctorId") Integer doctor_id, @Param("concernedUser")Integer concerned_user);
    //当用户取消关注某一用户
    Integer doctorDisConcernedUser(@Param("doctorId")Integer doctor_id,@Param("concernedUser")Integer concerned_user);
    //当医生关注某一医生
    Integer doctorConcernedDoctor(@Param("doctorId")Integer doctor_id,@Param("concernedDoctor")Integer concerned_doctor);
    //当医生关注某一医生
    Integer doctorDisConcernedDoctor(@Param("doctorId")Integer doctor_id,@Param("concernedDoctor")Integer concerned_doctor);
    //查询单个医生所有关注的人的用户id
    List<UserConcern> getDoctorConcerntionList(Integer id);
    //查询单个医生所有粉丝的用户id
    List<UserConcern> getDoctorConcernedList(Integer id);
}
