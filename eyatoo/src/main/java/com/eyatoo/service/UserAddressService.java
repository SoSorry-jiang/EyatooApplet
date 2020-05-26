package com.eyatoo.service;

import com.eyatoo.pojo.UserAddress;

import java.util.List;

public interface UserAddressService {
    //用户添加地址
    Integer userAddAddress(UserAddress userAddress);
    //用户编辑地址
    Integer userUpdateAddress(UserAddress userAddress);
    //用户查询自己的地址
    List<UserAddress> getOneUserAddresses(Integer userId);
    //用户删除地址
    Integer userDelAddress(Integer id);
    //医生查询自己的地址
    List<UserAddress> getOneDoctorAddresses(Integer doctorId);
    //根据id获取地址信息
    UserAddress getOneAddress(Integer id);
}
