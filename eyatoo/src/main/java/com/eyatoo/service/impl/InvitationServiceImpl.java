package com.eyatoo.service.impl;

import com.eyatoo.dao.InvitationDao;
import com.eyatoo.pojo.Agent;
import com.eyatoo.pojo.Invited;
import com.eyatoo.service.InvitationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Resource
    private InvitationDao ivtDao;

    @Override
    public List<Agent> getAllAgent(Integer id) {
        List<Agent> agentList = null;
        try {
             agentList = ivtDao.getOneUserAgent(id);
        }catch (Exception e){
            System.out.println("错误信息："+e.getMessage());
            e.printStackTrace();
        }

        return agentList;
    }

    @Override
    public List<Invited> getAllInvitedPs(Integer id,Integer status) {
        List<Invited> invitedList = null;
        try {
            invitedList = ivtDao.getOneUserInvitedPs(id,status);
        }catch (Exception e){
            System.out.println("错误信息："+e.getMessage());
            e.printStackTrace();
        }

        return invitedList;
    }

    @Override
    public Integer getCountInvitedUserStatus(Integer id) {
        Integer num = 0;
        try {
            num = ivtDao.getCountInvitedUserStatus(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer addInvitedUser(Invited invitedUser) {
        Integer isOk = 0;
        try {
           isOk = ivtDao.addInvitedUser(invitedUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<Invited> getOneDoctorInvitedPs(Integer id,Integer status) {
        List<Invited> inviteds = new ArrayList<>();
        try {
            inviteds = ivtDao.getOneDoctorInvitedPs(id,status);
        }catch (Exception e){
            e.printStackTrace();
        }
        return inviteds;
    }

    @Override
    public Integer getDoctorCountInvitedUserStatus(Integer id) {
        Integer isOk = 0;
        try {
          isOk = ivtDao.getDoctorCountInvitedUserStatus(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer judgeUser(Integer userId, Integer invitedId) {
        Integer isHave = 0;
        try{
            isHave = ivtDao.judgeUser(userId, invitedId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isHave;
    }
}
