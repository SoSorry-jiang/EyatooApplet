package com.eyatoo.service;

import com.eyatoo.pojo.UserComment;

import java.util.List;

//‘我的’‘商品详情’
public interface UserCommentService {
    //根据商品id查询该商品所有评论 （当用户点击一个商品进去后加载）
    List<UserComment> getAllCommentsByAaticlesId(Integer articles_id);
    //根据用户id查询该用户所有评论  （当用户查看自己的所有评论时调用，‘我的’）
    List<UserComment> getAllCommentsByUserId(Integer user_id);
    //根据商品id添加用户评论 （用户添加商品评论）
    Integer addUserComments(UserComment user_comment);
    //根据评论id删除评论
    Integer deleteComments(Integer id);
    //根据医生和用户和商品id 查询 用户最后一次评论改项目的评论内容
    UserComment getAllDoctorCommentisById(Integer doctorId,Integer proId,Integer userId);
}
