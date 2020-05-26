package com.eyatoo.service;

import com.eyatoo.pojo.UserCommunityComment;
import com.eyatoo.pojo.UserCommunityCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//‘社区’
public interface UserCommunityCommentService {
    //加载一个日志所有评论
    List<UserCommunityComment> getAllCommunityCommentById(@Param("id") Integer community_id);
    //加载一个评论所有回复
    List<UserCommunityCommentReply> getAllReplyById(@Param("id")Integer reply_id);
    //发表一条评论
    Integer addCommunityComment(UserCommunityComment userCommunityComment);
    //回复一条评论
    Integer addCommentReply(UserCommunityCommentReply userCommunityCommentReply);
    //点赞一条评论
    Integer updateCommentPrase(Integer id,Integer number);
    //获取所有用户回复
    List<UserCommunityCommentReply> getAllReplyListByCommunityId(@Param("id") Integer community_id);
    //删除一个帖子下所有的评论和回复
    Integer deleteOneCommunityAllComment(Integer communityId);
    //删除一个评论和它下面的所有回复
    Integer deleteOneCommunityCommentAndAllReply(Integer commentId);
}
