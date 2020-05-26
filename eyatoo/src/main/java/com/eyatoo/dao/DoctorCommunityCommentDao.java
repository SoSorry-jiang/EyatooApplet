package com.eyatoo.dao;


import com.eyatoo.pojo.DoctorCommunityComment;
import com.eyatoo.pojo.DoctorCommunityCommentReply;
import com.eyatoo.pojo.UserCommunityCommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorCommunityCommentDao {
    //加载一个帖子所有评论
    List<DoctorCommunityComment> getAllCommunityCommentById(@Param("id") Integer community_id);
    //加载所有回复
    List<DoctorCommunityCommentReply> getAllReplyById(@Param("id")Integer reply_id);
    //获取所有用户回复
    List<DoctorCommunityCommentReply> getAllReplyListByCommunityId(@Param("id") Integer community_id);
    //发表一条评论
    Integer addCommunityComment(DoctorCommunityComment userCommunityComment);
    //回复一条评论
    Integer addCommentReply(DoctorCommunityCommentReply userCommunityCommentReply);
    //点赞一条评论 或 取消一条评论的点赞
    Integer updateCommentPrase(Integer id,Integer number);
    //删除一个帖子下所有的评论和回复
    Integer deleteOneCommunityAllComment(Integer communityId);
    //删除一个评论和它下面的所有回复
    Integer deleteOneCommunityCommentAndAllReply(Integer commentId);
}
