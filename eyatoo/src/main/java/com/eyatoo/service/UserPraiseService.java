package com.eyatoo.service;

import com.eyatoo.pojo.UserPraise;

public interface UserPraiseService {
    //根据用户id查询点赞表中是否有用户数据
    Integer checkUser(Integer userId);

    //添加一条用户点赞数据
    Integer addPraise(UserPraise userPraise);

    //用户取消点赞
    Integer delPraise(UserPraise userPraise);

    //根据用户id加载用户点赞信息
    Integer loginUserPraise(UserPraise userPraise);


}
