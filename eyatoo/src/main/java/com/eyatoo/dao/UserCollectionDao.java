package com.eyatoo.dao;

import com.eyatoo.pojo.SyMedicalAdvertisement;
import com.eyatoo.pojo.UserCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectionDao {
    //根据用户id查询是否该用户已经收藏过该商品
    Integer findUserIsCollectedPro(Integer collectedUserId,
                                   Integer collectedArticlesId);
    //根据用户id 查询该用户收藏的诊疗项目id，用于前端默认显示
    List<Integer> findUserCollectedIdById(@Param("id")Integer id);
    //根据商品id 查询该诊疗项目总共被收藏了几次
    Integer getCountCollectedById(@Param("id")Integer id);
    //获得所有商品的id    该方法为商品表中方法
    List<Integer> getAllCommoditId();

    //当用户点击收藏按钮时，在收藏表添加一条记录
    Integer addCollection(UserCollection user_collection);
    //当用户取消收藏时，根据id删除一条收藏记录
    Integer delCollection(UserCollection user_collection);

}
