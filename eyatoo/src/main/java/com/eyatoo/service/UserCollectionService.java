package com.eyatoo.service;

import com.eyatoo.pojo.SyMedicalAdvertisement;
import com.eyatoo.pojo.UserCollection;

import java.util.List;

//‘我的’‘商品’‘商品详情’
public interface UserCollectionService {
  //根据用户id查询是否该用户已经收藏过该商品
  Integer findUserIsCollectedPro(Integer collected_user_id,
                                 Integer collected_articles_id);
  //根据用户id 查询该用户收藏的诊疗项目id，用于前端默认显示
  List<Integer> findUserCollectedIdById(Integer id);
  //根据商品id 查询该诊疗项目总共被收藏了几次，用于前端默认显示
  Integer getCountCollectedById(Integer id);

  //当用户点击收藏按钮时，在收藏表添加一条记录
  Integer addCollection(UserCollection user_collection);
  //当用户取消收藏时，根据id删除一条收藏记录
  Integer delCollection(UserCollection user_collection);
}
