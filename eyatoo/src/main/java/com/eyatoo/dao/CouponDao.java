package com.eyatoo.dao;

import com.eyatoo.pojo.Coupon;
import com.eyatoo.pojo.UserCoupon;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CouponDao {
     //根据门诊id查询门诊名字
     String getOutPreName(Integer outPreId);
     //根据条件查找优惠券，没有传条件就查询全部
     List<Coupon> getAllCoupon(Integer projectId, Integer branchId, String unixDate);
     //门诊端根据状态查询自己所有发布过的优惠券
     List<Coupon> getOneOutPreAllCoupon(Integer outPreId,Integer couponType,Integer isDrafts);
     //门诊端发布优惠券
     Integer OutPreAddCoupon(Coupon coupon);
     //用户领取优惠券
     Integer userReceiveCoupon(UserCoupon userCoupon);
     //用户转赠优惠券时，更新优惠券状态                            ↓转赠的用户的id
     Integer userGiveOtherUserCoupon(Integer userCouponId,Integer userId);
     //查询是否有好友赠送优惠券
     List<UserCoupon> isHaveFrdGiveCoupon(Integer userId);
     //接受好友赠送优惠券时，更新优惠券状态
     Integer updateFrdGiveCouponStatus(Integer userCouponId);
     //当优惠券过期时，更新优惠券状态
     Integer updateUserCouponStatus(Integer userCouponId);

     //查询一个用户的所有优惠券
     List<Map<String,Object>> getOneUserAllCoupon(Integer userId);

     //根据项目id，门诊id，满减最大金额，用户id，查询当用户下单时可用的优惠券
     List<Map<String,Object>> getCanUseCouponBySome(Integer userId, Integer branchId, Integer proId, BigDecimal MaxMoney, String unixDate);

     //用户使用优惠券
     Integer userUseCoupon(Integer userCouponId);

     //当首页推送优惠券时 根据指定id数组查询优惠券
     List<Coupon> getSomeCouponByIds(@Param("idList")List<Integer> idList, String unixDate);
}
