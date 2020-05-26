package com.eyatoo.service.impl;

import com.eyatoo.dao.UserPraiseDao;
import com.eyatoo.pojo.UserPraise;
import com.eyatoo.service.UserPraiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserPraiseServiceImpl implements UserPraiseService {
    @Resource
    private UserPraiseDao userPraiseDao;

    @Override
    public Integer checkUser(Integer userId) {
        Integer num = 0;
        try {
            num = userPraiseDao.checkUser(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer addPraise(UserPraise userPraise) {
        Integer num = 0;
        try {
            num = userPraiseDao.addPraise(userPraise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer delPraise(UserPraise userPraise) {
        Integer isOK =0;
        try {
            isOK = userPraiseDao.delPraise(userPraise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer loginUserPraise(UserPraise userPraise) {
        Integer userPraise1 = 0;
        try {
            userPraise1 = userPraiseDao.loginUserPraise(userPraise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userPraise1;
    }
}
