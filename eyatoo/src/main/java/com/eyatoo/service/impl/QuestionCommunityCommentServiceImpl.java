package com.eyatoo.service.impl;

import com.eyatoo.dao.QuestionCommunityCommentDao;
import com.eyatoo.pojo.QuestionCommunityComment;
import com.eyatoo.pojo.QuestionCommunityCommentReply;
import com.eyatoo.pojo.UserCommunityCommentReply;
import com.eyatoo.service.QuestionCommunityCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionCommunityCommentServiceImpl implements QuestionCommunityCommentService {
   @Resource
   private QuestionCommunityCommentDao questionCommunityCommentDao;

    @Override
    public List<QuestionCommunityComment> getAllCommunityCommentById(Integer community_id) {
        List<QuestionCommunityComment> questionCommunityComments = new ArrayList<>();
        try {
             questionCommunityComments = questionCommunityCommentDao.getAllCommunityCommentById(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionCommunityComments;
    }

    @Override
    public List<QuestionCommunityCommentReply> getAllReplyById(Integer reply_id) {
        List<QuestionCommunityCommentReply> questionCommunityCommentReplyList = new ArrayList<>();
        try {
             questionCommunityCommentReplyList = questionCommunityCommentDao.getAllReplyById(reply_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionCommunityCommentReplyList;
    }

    @Override
    public List<QuestionCommunityCommentReply> getAllReplyListByCommunityId(Integer community_id) {
        List<QuestionCommunityCommentReply> userCommunityCommentReplies = new ArrayList<>();
        try {
            userCommunityCommentReplies = questionCommunityCommentDao.getAllReplyListByCommunityId(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityCommentReplies;
    }

    @Override
    public Integer addCommunityComment(QuestionCommunityComment userCommunityComment) {
        Integer istrue = 0;
        try {
            istrue  = questionCommunityCommentDao.addCommunityComment(userCommunityComment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return istrue;
    }

    @Override
    public Integer addCommentReply(QuestionCommunityCommentReply userCommunityCommentReply) {
        Integer isOK = 0;
        try {
            isOK = questionCommunityCommentDao.addCommentReply(userCommunityCommentReply);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateCommentPrase(Integer id, Integer number) {
        Integer isOk = 0;
        try {
           isOk =  questionCommunityCommentDao.updateCommentPrase(id,number);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteOneCommunityAllComment(Integer communityId) {
        Integer isOk = 0;
        try {
            isOk = questionCommunityCommentDao.deleteOneCommunityAllComment(communityId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteOneCommunityCommentAndAllReply(Integer commentId) {
        Integer isOk = 0;
        try {
            isOk = questionCommunityCommentDao.deleteOneCommunityCommentAndAllReply(commentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
