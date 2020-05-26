package com.eyatoo.controller;

import com.eyatoo.pojo.Accounts;
import com.eyatoo.pojo.AgentOndition;
import com.eyatoo.pojo.Invited;
import com.eyatoo.pojo.User;
import com.eyatoo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AgentPointController {
    @Autowired
    private AgentPointService agentPointService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private AgentOnditionService agentOnditionService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private DoctorService doctorService;

    private WxChat wxChat = new WxChat();
    //当用户获取经纪人积分时触发
    @PostMapping("updateUserAgentPoint")
    public Integer updateUserAgentPoint(Integer point,Integer id){
        Integer isOK = 0;
        Integer num1 = 0;
        if(point > 0) {
            num1 = agentPointService.addCountPoints(point, id);
        }
         Integer num2 = agentPointService.updateUsedPoints(point,id);
           if (num1 != 0 && num2 != 0){
               isOK = 1;
           }
        return isOK;
    }

    //当用户获取会员积分
    @PostMapping("updateUserHyPoint")
    public Integer updateUserHyPoint(Integer point,Integer id){
        Integer isOK = agentPointService.updateUserUsedUserPoints(point,id);
        return isOK;
    }

    //当医生获取会员积分
    @PostMapping("updateDoctorHyPoint")
    public Integer updateDoctorHyPoint(Integer point,Integer id){
        Integer isoK = agentPointService.updateDoctorUsedUserPoints(point,id);
        return isoK;
    }

    //当医生获取经纪人积分
    @PostMapping("updateDoctorAgentPoint")
    public Integer updateDoctorAgentPoint(Integer point,Integer id){
        Integer isOK = 0;
        Integer num1 = 0;
        if(point > 0) {
            num1 = agentPointService.addDoctorCountPoints(point, id);
        }
        Integer num2 = agentPointService.updateDoctorUsedPoints(point,id);
        if (num1 != 0 && num2 != 0){
            isOK = 1;
        }
        return isOK;
    }

    //用户升级经纪人
    @RequestMapping("updateUserAgentLevel")                                        //↓type传的值为 0：用户 1：医生  判断升级经纪人等级的是用户还是医生
    public Map<String,String> updateUserAgentLevel(Integer userId, Integer level,Integer type){
        Map<String,String> map = new HashMap<>();
        User user = userService.findById(userId);
          //先获取该用户邀请的所有用户信息
        List<Invited> invitedPeoples =invitationService.getAllInvitedPs(userId,1);
        //获取已就诊用户总数
        Integer isTreatmentCount = invitedPeoples.size();

        //获取用户总经纪人积分
        Integer countAgentPoint = user.getJjrConuntPoints();
        //获取邀请就诊金额
        BigDecimal countInvitatedMoney = new BigDecimal("0");
        if(invitedPeoples != null) {
            for (Invited invited : invitedPeoples) {
                //单个已就诊用户就诊金额总数
                BigDecimal OneUserIsTreamentCount =  new BigDecimal("0");
                List<Accounts> accounts = accountsService.getUserAllProjectBy(invited.getInvitedPrsId(), userId);
                for (Accounts account : accounts) {
                    OneUserIsTreamentCount = OneUserIsTreamentCount.add(account.getFinallayMoney());
                }
                countInvitatedMoney = countInvitatedMoney.add(OneUserIsTreamentCount);
            }
        }
        //获取条件
        AgentOndition agentOndition = agentOnditionService.getAgentOnditionByLevel(level);
        if(isTreatmentCount < agentOndition.getAgentIstreatmentCount()){
             map.put("msg","isTreatmentCountNotEnough");
        }else if(countAgentPoint < agentOndition.getAgentPoint()){
            map.put("msg","countAgentPointNotEnough");
        }else if(countInvitatedMoney.compareTo(agentOndition.getAgentIstreatmentMoneyCount()) == -1){
            map.put("msg","countInvitatedMoneyNotEnough");
        }else {
            if(type == 0) {
                userService.updateAgentLevel(userId, level);
                wxChat.updateUserData(userId.toString(), null, null, null, level);
                map.put("msg", "success");
            }
            else if(type == 1){
                doctorService.updateDoctorAgentLevel(userId,level);
                wxChat.updateUserData(userId.toString(), null, null, null, level);
                map.put("msg", "success");
            }
            else{
                map.put("msg","Report errors");
            }

        }
        return map;
    }

//    //升级经纪人
//    @RequestMapping("getUserNowAgentParm")
//    public Map<String,Object> getUserNowAgentParm(Integer userId){
//        Map<String,Object> map = new HashMap<>();
//        User user = userService.findById(userId);
//        //先获取该用户邀请的所有用户信息
//        List<Invited> invitedPeoples =invitationService.getAllInvitedPs(userId,1);
//        //获取已就诊用户总数
//        Integer isTreatmentCount = invitedPeoples.size();
//
//        //获取用户总经纪人积分
//        Integer countAgentPoint = user.getJjrConuntPoints();
//        //获取邀请就诊金额
//        Integer countInvitatedMoney = 0;
//        if(invitedPeoples != null) {
//            for (Invited invited : invitedPeoples) {
//                //单个已就诊用户就诊金额总数
//                Integer OneUserIsTreamentCount = 0;
//                List<Accounts> accounts = accountsService.getUserAllProjectBy(invited.getInvitedPrsId(), userId);
//                for (Accounts account : accounts) {
//                    OneUserIsTreamentCount = OneUserIsTreamentCount + account.getFinallayMoney();
//                }
//                countInvitatedMoney = countInvitatedMoney + OneUserIsTreamentCount;
//            }
//        }
//        map.put("isTreatmentCount",isTreatmentCount);
//        map.put("countAgentPoint",countAgentPoint);
//        map.put("countInvitatedMoney",countInvitatedMoney);
//        return map;
//    }


    //用钱升级经纪人（待完善）
}
