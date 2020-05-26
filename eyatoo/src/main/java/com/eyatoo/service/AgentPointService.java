package com.eyatoo.service;

public interface AgentPointService {
    //用户获得经纪人积分时，增加总积分
    Integer  addCountPoints(Integer num,Integer id);
    //用户使用经纪人积分或获取积分时，添加或扣除经纪人积分
    Integer updateUsedPoints(Integer num,Integer id);
    //单击升级按钮时，判断用户经纪人积分是否达到某个阶段，并升级经纪人等级

    //用户获得或兑换用户积分时，添加或扣除用户总积分
    Integer updateUserUsedUserPoints(Integer num,Integer id);
    //医生获得经纪人积分时，增加总积分
    Integer  addDoctorCountPoints(Integer num,Integer id);
    //医生使用经纪人积分或获取积分时，添加或扣除经纪人积分
    Integer updateDoctorUsedPoints(Integer num,Integer id);
    //用户获得或兑换用户积分时，添加或扣除用户总积分
    Integer updateDoctorUsedUserPoints(Integer num,Integer id);
}
