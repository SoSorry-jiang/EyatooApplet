package com.eyatoo.service.impl;

import com.eyatoo.dao.UserCommunityDao;
import com.eyatoo.pojo.UserCommunity;
import com.eyatoo.service.UserCommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommunityServiceImpl  implements UserCommunityService {
    @Resource
    private UserCommunityDao userCommunityDao;

    @Override
    public Integer addCommunity(UserCommunity userCommunity) {
        Integer num = 0;
        try {
           num =  userCommunityDao.addCommunity(userCommunity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<UserCommunity> getAllCommunity(String projectName) {
        List<UserCommunity> userCommunityList = null;
        try {
             userCommunityList = userCommunityDao.getCommunityList(projectName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityList;
    }

    @Override
    public UserCommunity getOneCommunity(Integer id) {
        UserCommunity userCommunity = null;
        try {
            userCommunity = userCommunityDao.getOneCommunity(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunity;
    }

    @Override
    public List<UserCommunity> getOneUserLog(Integer id) {
        List<UserCommunity> userCommunityList = null;
        try {
            userCommunityList = userCommunityDao.getOneUserLog(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityList;
    }

    @Override
    public List<UserCommunity> getAboutDoctorCommunity(Integer doctorId) {
        List<UserCommunity> communityList = new ArrayList<>();
        try {
               communityList = userCommunityDao.getAboutDoctorCommunity(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return communityList;
    }

    @Override
    public Integer updatePhoto(String afterPhoto, Integer id) {
        Integer isOK = 0;
        try {
           isOK = userCommunityDao.updatePhoto(afterPhoto,id);
        }catch (Exception e){
            e.getStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updatePraise(Integer id, Integer praise) {
        Integer isOk = 0;
        try {
            isOk = userCommunityDao.updatePraise(id, praise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
