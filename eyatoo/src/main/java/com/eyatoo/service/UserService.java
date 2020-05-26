package com.eyatoo.service;

import com.eyatoo.pojo.User;

import java.math.BigInteger;
import java.util.List;

//‘我的’
public interface UserService {
    //用户注册
    Integer addUser(User user);

    //根据id取得单用户的信息
    User findById(Integer id);
    //修改用户头像和名称
    Integer updateUserAvatorOrName(String filePath, String name, BigInteger phone, Integer id);

    //用户修改默认地址
    Integer updateUserDefaultAddress(Integer addressId,Integer userId);


    //当成为经纪人的时候，更新经纪人二维码
    Integer updateQRcode(Integer userId,String path);

    //添加用户的身份证，真实姓名信息
    Integer updateTrueMsg(Integer userId,String trueName,String idCard);

    //查找经纪人
    List<User> fingAllAgentByName(String name);

    //根据openId判断用户是否已存在
    User isHaveUser(String openId);

    //当用户更新头像或名字时，更新其他表绑定的头像
    void updateOtherTablesMsg(String userAvator,String userName,Integer id);


    //更新经纪人状态
    Integer updateAgentStatus(Integer id,Integer status);

    //经纪人等级加一
    Integer updateAgentLevel(Integer userId,Integer level);

    //领取新人礼包后，更新状态
    Integer updateUserIsReceiveNewPackage(Integer userId);
}
