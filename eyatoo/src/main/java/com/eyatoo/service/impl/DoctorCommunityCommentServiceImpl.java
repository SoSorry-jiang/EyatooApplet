package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorCommunityCommentDao;
import com.eyatoo.pojo.DoctorCommunityComment;
import com.eyatoo.pojo.DoctorCommunityCommentReply;
import com.eyatoo.pojo.UserCommunityCommentReply;
import com.eyatoo.service.DoctorCommunityCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorCommunityCommentServiceImpl implements DoctorCommunityCommentService {

    @Resource
    private DoctorCommunityCommentDao doctorCommunityCommentDao;

    @Override
    public List<DoctorCommunityComment> getAllCommunityCommentById(Integer community_id) {
        List<DoctorCommunityComment> doctorCommunityComments = new ArrayList<>();
        try {
             doctorCommunityComments = doctorCommunityCommentDao.getAllCommunityCommentById(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCommunityComments;
    }

    @Override
    public List<DoctorCommunityCommentReply> getAllReplyById(Integer reply_id) {
        List<DoctorCommunityCommentReply> doctorCommunityCommentReplies = new ArrayList<>();
        try {
           doctorCommunityCommentReplies = doctorCommunityCommentDao.getAllReplyById(reply_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCommunityCommentReplies;
    }

    @Override
    public List<DoctorCommunityCommentReply> getAllReplyListByCommunityId(Integer community_id) {
        List<DoctorCommunityCommentReply> userCommunityCommentReplies = new ArrayList<>();
        try {
            userCommunityCommentReplies = doctorCommunityCommentDao.getAllReplyListByCommunityId(community_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCommunityCommentReplies;
    }

    @Override
    public Integer addCommunityComment(DoctorCommunityComment doctorCommunityComment) {
        Integer isOK = 0;
        try {
             isOK = doctorCommunityCommentDao.addCommunityComment(doctorCommunityComment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer addCommentReply(DoctorCommunityCommentReply doctorCommunityCommentReply) {
        Integer isOk = 0;
        try {
           isOk = doctorCommunityCommentDao.addCommentReply(doctorCommunityCommentReply);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateCommentPrase(Integer id, Integer number) {
        Integer isOk = 0;
        try {
            isOk = doctorCommunityCommentDao.updateCommentPrase(id,number);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteOneCommunityAllComment(Integer communityId) {
        Integer isOk = 0;
        try {
            isOk = doctorCommunityCommentDao.deleteOneCommunityAllComment(communityId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteOneCommunityCommentAndAllReply(Integer commentId) {
        Integer isOk = 0;
        try {
            isOk = doctorCommunityCommentDao.deleteOneCommunityCommentAndAllReply(commentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
