package com.eyatoo.service;

import com.eyatoo.pojo.QuestionCommunityComment;
import com.eyatoo.pojo.QuestionCommunityCommentReply;
import com.eyatoo.pojo.UserCommunityCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionCommunityCommentService {
    //加载一个帖子所有评论
    List<QuestionCommunityComment> getAllCommunityCommentById(Integer community_id);
    //加载所有回复
    List<QuestionCommunityCommentReply> getAllReplyById(Integer reply_id);
    //获取所有用户回复
    List<QuestionCommunityCommentReply> getAllReplyListByCommunityId(Integer community_id);
    //发表一条评论
    Integer addCommunityComment(QuestionCommunityComment userCommunityComment);
    //回复一条评论
    Integer addCommentReply(QuestionCommunityCommentReply userCommunityCommentReply);
    //点赞一条评论 或 取消一条评论的点赞
    Integer updateCommentPrase(Integer id,Integer number);
    //删除一个帖子下所有的评论和回复
    Integer deleteOneCommunityAllComment(Integer communityId);
    //删除一个评论和它下面的所有回复
    Integer deleteOneCommunityCommentAndAllReply(Integer commentId);
}
