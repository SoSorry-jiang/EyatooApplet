package com.eyatoo.service;

import com.eyatoo.pojo.UserCommunity;

import java.util.List;

//‘社区’ ‘我的’
public interface UserCommunityService {
     //用户发表日志
     Integer addCommunity(UserCommunity userCommunity);
     //获得所有日志 （可带条件）
     List<UserCommunity> getAllCommunity(String projectName);
     //进入一个日志的详情页
     UserCommunity getOneCommunity(Integer id);
     //根据用户id查询当前用户所有日志
     List<UserCommunity> getOneUserLog(Integer id);
     //医生查看所有与他相关的日志
     List<UserCommunity> getAboutDoctorCommunity(Integer doctorId);
     //更新图片
     Integer updatePhoto(String afterPhoto, Integer id);
     //更新点赞
     Integer updatePraise(Integer id,Integer praise);
}
