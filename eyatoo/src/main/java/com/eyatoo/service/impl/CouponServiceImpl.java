package com.eyatoo.service.impl;

import com.eyatoo.dao.CouponDao;
import com.eyatoo.pojo.Coupon;
import com.eyatoo.pojo.UserCoupon;
import com.eyatoo.service.CouponService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponDao couponDao;

    @Override
    public String getOutPreName(Integer outPreId) {
        String name = "";
        try {
             name = couponDao.getOutPreName(outPreId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public List<Coupon> getAllCoupon(Integer projectId, Integer branchId) {
        List<Coupon> coupons = new ArrayList<>();
        try {
            String dateStr = Long.toString(System.currentTimeMillis()/1000L);
            coupons = couponDao.getAllCoupon(projectId, branchId,dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return coupons;
    }

    @Override
    public List<Coupon> getOneOutPreAllCoupon(Integer outPreId, Integer couponType,Integer isDrafts) {
        List<Coupon> coupons = new ArrayList<>();
        try {
           coupons = couponDao.getOneOutPreAllCoupon(outPreId, couponType,isDrafts);
        }catch (Exception e){
            e.printStackTrace();
        }
        return coupons;
    }

    @Override
    public Integer OutPreAddCoupon(Coupon coupon) {
        Integer isOK = 0;
        try {
          isOK = couponDao.OutPreAddCoupon(coupon);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer userReceiveCoupon(UserCoupon userCoupon) {
        Integer isOK = 0;
        try {
              isOK = couponDao.userReceiveCoupon(userCoupon);
        }catch (Exception  e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer userGiveOtherUserCoupon(Integer userCouponId, Integer userId) {
        Integer isOK = 0;
        try {
           isOK = couponDao.userGiveOtherUserCoupon(userCouponId, userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<UserCoupon> isHaveFrdGiveCoupon(Integer userId) {
        List<UserCoupon> userCoupons = new ArrayList<>();
        try {
           userCoupons  = couponDao.isHaveFrdGiveCoupon(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userCoupons;
    }

    @Override
    public Integer updateFrdGiveCouponStatus(Integer userCouponId) {
        Integer isOK = 0;
        try {
          isOK = couponDao.updateFrdGiveCouponStatus(userCouponId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updateUserCouponStatus(Integer userCouponId) {
        Integer isOK = 0;
        try {
          isOK = couponDao.updateUserCouponStatus(userCouponId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Map<String, Object>> getOneUserAllCoupon(Integer userId) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
           mapList = couponDao.getOneUserAllCoupon(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public Integer userUseCoupon(Integer userCouponId) {
        Integer isOK = 0;
        try {
         isOK = couponDao.userUseCoupon(userCouponId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<Map<String, Object>> getCanUseCouponBySome(Integer userId, Integer branchId, Integer proId, BigDecimal MaxMoney) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            String dateStr = Long.toString(System.currentTimeMillis()/1000L);
            mapList = couponDao.getCanUseCouponBySome(userId, branchId, proId, MaxMoney,dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapList;
    }

    @Override
    public List<Coupon> getSomeCouponByIds(List<Integer> ids) {
        List<Coupon> couponList = new ArrayList<>();
        try {
            String dateStr = Long.toString(System.currentTimeMillis()/1000L);
            couponList = couponDao.getSomeCouponByIds(ids,dateStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return couponList;
    }
}
