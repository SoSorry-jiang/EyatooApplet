package com.eyatoo.service.impl;


import com.eyatoo.dao.UserCommentDao;
import com.eyatoo.pojo.UserComment;
import com.eyatoo.service.UserCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    @Resource
    private UserCommentDao userCommentDao;



    @Override
    public List<UserComment> getAllCommentsByAaticlesId(Integer articles_id) {
        List<UserComment> allcommentList = null;
        try {
            allcommentList = userCommentDao.getAllCommentsByAaticlesId(articles_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return allcommentList;
    }

    @Override
    public List<UserComment> getAllCommentsByUserId(Integer user_id) {
        List<UserComment> ucommentsList = null;
        try {
           ucommentsList = userCommentDao.getAllCommentsByUserId(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ucommentsList;
    }

    @Override
    public Integer addUserComments(UserComment user_comment) {
        Integer num = 0;
        try {
            num = userCommentDao.addUserComments(user_comment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer deleteComments(Integer id) {
        Integer num = 0;
        try {
            num = userCommentDao.deleteComments(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public UserComment getAllDoctorCommentisById(Integer doctorId,Integer proId,Integer userId) {
        UserComment comments = null;
        try {
              comments = userCommentDao.getAllDoctorCommentisById(doctorId,proId,userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }
}
