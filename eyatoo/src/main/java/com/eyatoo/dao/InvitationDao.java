package com.eyatoo.dao;

import com.eyatoo.pojo.Agent;
import com.eyatoo.pojo.Invited;

import java.util.List;

public interface InvitationDao {
    //根据用户id 获取该用户邀请的人
    List<Invited> getOneUserInvitedPs(Integer id,Integer status);
    //根据用户id 获取该用户的经纪人
    List<Agent> getOneUserAgent(Integer id);
    //根据用户id获取被邀请但未就诊用户总数
    Integer getCountInvitedUserStatus(Integer id);

    //添加被邀请用户
    Integer addInvitedUser(Invited invitedUser);
    //根据医生id 获取该用户邀请的人
    List<Invited> getOneDoctorInvitedPs(Integer id,Integer status);
    //根据医生id 获取该用户的经纪人
    List<Agent> getOneDoctorAgent(Integer id);
    //根据医生id获取被邀请但未就诊用户总数
    Integer getDoctorCountInvitedUserStatus(Integer id);
    //判断该用户是否已经接受过邀请
    Integer judgeUser(Integer userId,Integer invitedId);
}
