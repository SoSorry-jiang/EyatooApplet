package com.eyatoo.controller;

import com.eyatoo.pojo.Coupon;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserCoupon;
import com.eyatoo.service.CouponService;
import com.eyatoo.service.ProjectMedicalAdvertisementService;
import com.eyatoo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CouponController {
    @Resource
    private CouponService couponService;

    @Resource
    private ProjectMedicalAdvertisementService projectMedicalAdvertisementService;

    @Resource
    private UserService userService;

    //用户使用优惠券
    @RequestMapping("userUseCoupon")
    private Integer userUseCoupon(Integer id){
        return couponService.userUseCoupon(id);
    }

     //门诊端发布优惠券
     @RequestMapping("OutPreAddCoupon")
     private Integer OutPreAddCoupon(Coupon coupon){
        String outPreName =  couponService.getOutPreName(coupon.getBranchId());
        String explain = "";
        String codExplain = "";
        String couponName = "";
        if(coupon.getCouponType() == 1) {
            explain = "在"+outPreName+"下的所有项目都可以使用";
            codExplain  = outPreName+"专用";
            couponName = "门诊劵";
        }else if(coupon.getCouponType() == 2){
            String proName = projectMedicalAdvertisementService.findJiBen(coupon.getProjectId()).getTitle();
            explain = "限于'"+proName+"'使用";
            codExplain  = outPreName+"专用";
            couponName = "单项目劵";
        }else if(coupon.getCouponType() == 3){
            explain = "在平台上所有项目都可以使用";
            codExplain = "全平台通用";
            couponName = "平台劵";
        }
        coupon.setCouponName(couponName);
        coupon.setCouponExplain(explain);
        coupon.setCouponConditionExplain(codExplain);
        return couponService.OutPreAddCoupon(coupon);
    }

    //门诊端根据状态查询自己所有发布过的优惠券
    @RequestMapping("OutPreFindAllCoupon")
    private List<Coupon> OutPreFindAllCoupon(Integer outPreId,Integer couponType,Integer isDrafts){
       return couponService.getOneOutPreAllCoupon(outPreId, couponType,isDrafts);
    }


    //用户端 或者项目 或者门诊 查找所有优惠券
    @RequestMapping("getAllCouponByCondition")
    private List<Coupon> getAllCouponByCondition(Integer projectId,Integer branchId){
        return  couponService.getAllCoupon(projectId, branchId);
    }

    //用户领取优惠券
    @RequestMapping("userGetCoupon")
    private Integer userGetCoupon(Integer userId,Integer[] couponIds){
        Integer isOK =0;
        for (Integer couponId:couponIds) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUserId(userId);
            userCoupon.setCouponId(couponId);
            isOK = couponService.userReceiveCoupon(userCoupon);
        }
        return  isOK;
    }

    //用户转赠优惠券
    @RequestMapping("userGiveOtherUserCoupon")                  //↓转赠的用户的id
    private Integer userGiveOtherUserCoupon(Integer userCouponId,Integer userId){
         return couponService.userGiveOtherUserCoupon(userCouponId, userId);
    }

    //加载查询是否有好友赠送优惠券
    @RequestMapping("isHaveFrdGiveCoupon")
    private List<UserCoupon> isHaveFrdGiveCoupon(Integer userId){
         return couponService.isHaveFrdGiveCoupon(userId);
    }

    //接受好友赠送优惠券时，更新优惠券状态
    @RequestMapping("updateFrdGiveCouponStatus")
    private Integer updateFrdGiveCouponStatus(Integer userCouponId){
        return couponService.updateFrdGiveCouponStatus(userCouponId);
    }

    //获取一个用户所有的优惠券
    @RequestMapping("loginOneUserAllCoupon")
    private  List<Map<String ,Object>> loginOneUserAllCoupon(Integer userId) throws Exception{
        List<Map<String ,Object>> paramList = couponService.getOneUserAllCoupon(userId);
        long nd = 1000 * 24 * 60 * 60;//每天毫秒数
        long nh = 1000 * 60 * 60;//每小时毫秒数
        long nm = 1000 * 60;//每分钟毫秒数
        for (Map<String,Object> map: paramList) {
            Integer couponId = Integer.parseInt(map.get("ucoupon_id").toString());
            String endTime = map.get("end_time").toString();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endDate = simpleDateFormat.parse(endTime);
            long diff = endDate.getTime() - date.getTime();
            long day = diff / nd;   // 计算差多少天
            long hour = diff % nd / nh; // 计算差多少小时
            long min = diff % nd % nh / nm;  // 计算差多少分钟
            if(day <= 0){
                if(hour<=0){
                    if (min <= 0){
                        System.out.println("相差"+day+"天"+hour+"小时"+min+"分钟");
                        //当优惠卷过期两天自动删除
                        if(day <= -2){
                            if(hour<=0){
                                if (min <= 0) {
                                    Integer isDelete = couponService.userUseCoupon(couponId);
                                    if (isDelete != 0) {
                                        System.out.println("已从数据库删除该张用户优惠券");
                                    } else {
                                        System.out.println("删除失败");
                                    }
                                }
                            }
                        }else {
                            Integer isOk = couponService.updateUserCouponStatus(couponId);
                            if (isOk != 0) {
                                System.out.println("更新状态成功");
                                map.put("coupon_status", 0);
                            } else {
                                System.out.println("更新状态失败");
                            }
                        }
                    }
                }
            }
        }
        return paramList;
    }

    //当用户确定订单时，查询该订单所有可用的优惠券
    @RequestMapping("loginCanUseCouponByUserAndPro")
    private List<Map<String ,Object>> loginCanUseCouponByUserAndPro(Integer userId, Integer branchId, Integer proId, BigDecimal MaxMoney){
        return couponService.getCanUseCouponBySome(userId, branchId, proId, MaxMoney);
    }


    //首页推荐3个优惠券
    @RequestMapping("getSomeCouponByIds")
    private Map<String,Object> getSomeCouponByIds(Integer userId){
        Map<String,Object> map = new HashMap<>();
        User user = userService.findById(userId);
           if(user.getIsReceiveNewPackage() == 0){
               userService.updateUserIsReceiveNewPackage(userId);
               List<Integer> ids = new ArrayList<>();
               ids.add(1);
               ids.add(2);
               ids.add(3);
               map.put("list",couponService.getSomeCouponByIds(ids));
               map.put("msg","1");
           }else {
               map.put("msg","0");
           }

        return map;
    }
}
