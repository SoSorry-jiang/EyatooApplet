package com.eyatoo.dao;

import com.eyatoo.pojo.DoctorCommunity;

import java.util.List;

public interface DoctorCommunityDao {
    //医生发布帖子
    Integer addDoctorCommunity(DoctorCommunity doctorCommunity);

    //根据标签查询所有帖子
    List<DoctorCommunity>  getAllDoctorCommunityByTag(String tag);

    //进入某一个医生帖子详情页
    DoctorCommunity getOneDoctorCommunityById(Integer id);

    //获取一个医生所有的帖子列表
    List<DoctorCommunity> getOneOfDoctorCommunitById(Integer id);

    //增加浏览量
    Integer updateBrowseVolume(Integer id);

    //更新点赞
    Integer updatePraise(Integer id,Integer praise);

}
