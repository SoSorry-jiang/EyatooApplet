package com.eyatoo.dao;

import com.eyatoo.pojo.UserAddress;

import java.util.List;

public interface UserAddressDao {
    //用户或医生添加地址
    Integer userAddAddress(UserAddress userAddress);
    //用户或医生编辑地址
    Integer userUpdateAddress(UserAddress userAddress);
    //用户或医生删除地址
    Integer userDelAddress(Integer id);
     //根据id获取地址信息
    UserAddress getOneAddress(Integer id);


    //用户查询自己的地址
    List<UserAddress> getOneUserAddresses(Integer userId);
    //医生查询自己的地址
    List<UserAddress> getOneDoctorAddresses(Integer doctorId);

}
