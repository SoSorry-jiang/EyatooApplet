package com.eyatoo.service.impl;

import com.eyatoo.dao.UserCommunityCommentDao;
import com.eyatoo.pojo.UserCommunityComment;
import com.eyatoo.pojo.UserCommunityCommentReply;
import com.eyatoo.service.UserCommunityCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCommunityCommentServiceImpl implements UserCommunityCommentService {

    @Resource
    private UserCommunityCommentDao userCommunityCommentDao;

    @Override
    public List<UserCommunityComment> getAllCommunityCommentById(Integer community_id) {
        List<UserCommunityComment> userCommunityCommentList = null;
        try {
            userCommunityCommentList = userCommunityCommentDao.getAllCommunityCommentById(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityCommentList;
    }


    @Override
    public List<UserCommunityCommentReply> getAllReplyListByCommunityId(Integer community_id) {
        List<UserCommunityCommentReply> userCommunityCommentReplies = new ArrayList<>();
        try {
             userCommunityCommentReplies = userCommunityCommentDao.getAllReplyListByCommunityId(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityCommentReplies;
    }

    @Override
    public List<UserCommunityCommentReply> getAllReplyById(Integer reply_id) {
        List<UserCommunityCommentReply> userCommunityCommentReplyList = null;
        try {
          userCommunityCommentReplyList = userCommunityCommentDao.getAllReplyById(reply_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityCommentReplyList;
    }

    @Override
    public Integer addCommunityComment(UserCommunityComment userCommunityComment) {
        Integer num = 0;
        try {
            num = userCommunityCommentDao.addCommunityComment(userCommunityComment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer addCommentReply(UserCommunityCommentReply userCommunityCommentReply) {
        Integer num = 0;
        try {
            num = userCommunityCommentDao.addCommentReply(userCommunityCommentReply);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer updateCommentPrase(Integer id,Integer number) {
        Integer num = 0;
        try {
           num = userCommunityCommentDao.updateCommentPrase(id,number);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer deleteOneCommunityAllComment(Integer communityId) {
        Integer isOk = 0;
        try {
            isOk = userCommunityCommentDao.deleteOneCommunityAllComment(communityId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteOneCommunityCommentAndAllReply(Integer commentId) {
        Integer isOk = 0;
        try {
            isOk = userCommunityCommentDao.deleteOneCommunityCommentAndAllReply(commentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
