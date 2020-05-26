package com.eyatoo.service.impl;

import com.eyatoo.dao.UserConcernDao;
import com.eyatoo.pojo.UserConcern;
import com.eyatoo.service.UserConcernService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserConcernServiceImpl implements UserConcernService {
    @Resource
    private UserConcernDao userConcernDao;

    @Override
    public Integer userConcernedUser(Integer user_id, Integer concerned_user) {
        Integer num = 0;
        try {
            num = userConcernDao.userConcernedUser(user_id,concerned_user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer userDisConcernedUser(Integer user_id, Integer concerned_user) {
        Integer num = 0;
        try {
            num = userConcernDao.userDisConcernedUser(user_id,concerned_user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<UserConcern> getUserConcerntionList(Integer id) {
        List<UserConcern> userConcernList = new ArrayList<>();
        try {
            userConcernList = userConcernDao.getUserConcerntionList(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userConcernList;
    }

    @Override
    public List<UserConcern> getUserConcernedList(Integer id) {
        List<UserConcern> userConcernList = new ArrayList<>();
        try {
            userConcernList = userConcernDao.getUserConcernedList(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userConcernList;
    }

    @Override
    public Integer userConcernedDoctor(Integer user_id, Integer concerned_doctor) {
        Integer num = 0;
        try {
            num = userConcernDao.userConcernedDoctor(user_id,concerned_doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer userDisConcernedDoctor(Integer user_id, Integer concerned_doctor) {
        Integer num = 0;
        try {
            num = userConcernDao.userDisConcernedDoctor(user_id,concerned_doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer isConcernedOrNot(Integer user_id, Integer doctor_id, Integer concerned_user, Integer concerned_doctor) {
        Integer isOK = 0;
        try {
             isOK = userConcernDao.isConcernedOrNot(user_id, doctor_id, concerned_user, concerned_doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
