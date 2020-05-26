package com.eyatoo.dao;

import com.eyatoo.pojo.UserCommunity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommunityDao {
    //用户发布日志
    Integer addCommunity(UserCommunity userCommunity);
    //默认显示所有用户日志，也可以加搜索条件，按时间倒序
    List<UserCommunity>  getCommunityList(@Param("projectName") String projectName);
    //进入某一个日志详情页
    UserCommunity  getOneCommunity(Integer id);
    //根据用户id查询当前用户所有日志
    List<UserCommunity> getOneUserLog(Integer id);
    //医生查看所有与他相关的日志
    List<UserCommunity> getAboutDoctorCommunity(Integer doctorId);
    //更新图片
    Integer updatePhoto(String afterPhoto, Integer id);
    //更新点赞
    Integer updatePraise(Integer id,Integer praise);
}
