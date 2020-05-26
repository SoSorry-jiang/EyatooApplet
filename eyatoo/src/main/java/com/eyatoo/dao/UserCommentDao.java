package com.eyatoo.dao;

import com.eyatoo.pojo.UserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommentDao {
    //
    //根据商品id查询该商品所有评论
    List<UserComment> getAllCommentsByAaticlesId(@Param("id")Integer articles_id);
    //根据用户id查询该用户所有评论
    List<UserComment> getAllCommentsByUserId(@Param("id")Integer user_id);
    //根据商品id添加用户评论
    Integer addUserComments(UserComment user_comment);
    //根据商品id和用户id修改评论
    Integer updateComments(Integer user_id,Integer articles_id);
    //根据评论id删除评论
    Integer deleteComments(Integer id);
    //根据医生和用户和商品id 查询 用户最后一次评论改项目的评论内容
    UserComment getAllDoctorCommentisById(Integer doctorId,Integer proId,Integer userId);
}
