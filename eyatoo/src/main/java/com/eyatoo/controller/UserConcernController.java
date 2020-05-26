package com.eyatoo.controller;

import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.Invited;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserConcern;
import com.eyatoo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserConcernController {
      @Resource
      private UserConcernService userConcernService;

      @Resource
      private UserService userService;

      @Resource
      private InvitationService invitationService;

      @Resource
      private DoctorService doctorService;

             //用户单击关注用户
             @RequestMapping("/userConcernedUser")
             public Integer userConcerned(Integer user_id,Integer concerned_user){
                 Integer isOK = 0;
                 if(userConcernService.isConcernedOrNot(user_id,null,concerned_user,null) == 0){
                 isOK = userConcernService.userConcernedUser(user_id,concerned_user);
                 }
                 return  isOK;
             }

             //用户取消关注用户
             @RequestMapping("/userDisConcernedUser")
             public  Integer userDisConcerned(Integer user_id,Integer concerned_user){
                 Integer isOK = 0;
                 if(userConcernService.isConcernedOrNot(user_id,null,concerned_user,null) != 0) {
                     isOK = userConcernService.userDisConcernedUser(user_id, concerned_user);
                 }
                 return  isOK;
             }


            //当用户关注医生时
            @RequestMapping("userConcernedDoctor")
            private Integer doctorConcerned(Integer user_id,Integer concerned_doctor){
                     Integer isOK = 0;
                     if(userConcernService.isConcernedOrNot(user_id,null,null,concerned_doctor) == 0){
                       isOK = userConcernService.userConcernedDoctor(user_id, concerned_doctor);
                     }
                return isOK;
            }

            //当用户取消关注医生时
            @RequestMapping("userDisConcernedDoctor")
            private Integer doctorDisConcerned(Integer user_id,Integer concerned_doctor){
                     Integer isOK = 0;
                     if(userConcernService.isConcernedOrNot(user_id,null,null,concerned_doctor) != 0){
                         isOK = userConcernService.userDisConcernedDoctor(user_id, concerned_doctor);
                     }
                return isOK;
            }



             //查询单个用户所有粉丝
             @RequestMapping("/getUserAllConcerntion")
             public  List<Map<String,Object>> getUserAllConcerntion(Integer id,Integer nowUserId){
                 List<Map<String,Object>> paramList = new ArrayList<>();
                 //获取用户列表
                 List<UserConcern> userConcernedIdList = userConcernService.getUserConcerntionList(id);
                 for(UserConcern userConcern:userConcernedIdList) {
                     if(userConcern.getUserId() != 0) {
                         Integer userId = userConcern.getUserId();
                         Map<String, Object> parameterMap = new HashMap<>();
                         User user = userService.findById(userId);
                         List<Invited> invitedList = invitationService.getAllInvitedPs(userId, 1);
                         //获取所有被邀请人名字和总数量
                         List<User> invitedUser = new ArrayList<>();
                         for (Invited invited : invitedList) {
                             User intUser = userService.findById(invited.getInvitedPrsId());
                             invitedUser.add(intUser);
                         }
                         Integer countInvited = invitedUser.size();
                         user.setCountSuccessMedPeople(countInvited);
                         parameterMap.put("id", user.getId());
                         parameterMap.put("name", user.getName());
                         parameterMap.put("txPhoto", user.getTxPhoto());
                         parameterMap.put("jjrLevel", user.getJjrLevel());
                         parameterMap.put("countSuccessMedPeople", countInvited);
                         //加载该用户是否已经关注过
                         Integer isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, user.getId(), null);
                         parameterMap.put("isConcerned", isConcerned);
                         parameterMap.put("isDoctor",0);
                         paramList.add(parameterMap);
                     }
                     else if(userConcern.getDoctorId() != 0){
                         Integer doctorId = userConcern.getDoctorId();
                         Map<String, Object> parameterMap = new HashMap<>();
                         Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
                         List<Invited> invitedList = invitationService.getOneDoctorInvitedPs(doctorId, 1);
                         //获取所有被邀请人名字和总数量
                         List<User> invitedUser = new ArrayList<>();
                         for (Invited invited : invitedList) {
                             User intUser = userService.findById(invited.getInvitedPrsId());
                             invitedUser.add(intUser);
                         }
                         Integer countInvited = invitedUser.size();
                         doctor.setCountSuccessMedPeople(countInvited);
                         parameterMap.put("id", doctor.getId());
                         parameterMap.put("name", doctor.getDoctorName());
                         parameterMap.put("txPhoto", doctor.getDoctorAvator());
                         parameterMap.put("jjrLevel", doctor.getJjrLevel());
                         parameterMap.put("countSuccessMedPeople", countInvited);
                         //加载该用户是否已经关注过
                         Integer isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, null, doctor.getId());
                         parameterMap.put("isConcerned", isConcerned);
                         parameterMap.put("isDoctor",1);
                         paramList.add(parameterMap);
                     }
                 }
                 return paramList;
             }

             //查询单个用户所有关注
            @RequestMapping("/getUserAllConcerned")
            public  List<Map<String,Object>> getUserAllConcerned(Integer id,Integer nowUserId){
                List<Map<String,Object>> paramList = new ArrayList<>();
                List<UserConcern> userConcernedIdList = userConcernService.getUserConcernedList(id);
                for(UserConcern userConcern:userConcernedIdList) {
                    if(userConcern.getConcernedUser() != 0){
                    Integer userId = userConcern.getConcernedUser();
                    Map<String,Object> parameterMap = new HashMap<>();
                    User user = userService.findById(userId);
                    List<Invited> invitedList = invitationService.getAllInvitedPs(userId,1);
                    //获取所有被邀请人名字和总数量
                    List<User> invitedUser = new ArrayList<>();
                    for (Invited invited:invitedList) {
                        User intUser = userService.findById(invited.getInvitedPrsId());
                        invitedUser.add(intUser);
                    }
                    Integer countInvited = invitedUser.size();
                    user.setCountSuccessMedPeople(countInvited);
                    parameterMap.put("id",user.getId());
                    parameterMap.put("name",user.getName());
                    parameterMap.put("txPhoto",user.getTxPhoto());
                    parameterMap.put("jjrLevel",user.getJjrLevel());
                    parameterMap.put("countSuccessMedPeople",countInvited);
                    //加载该用户是否已经关注过
                    Integer isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, user.getId(), null);
                    parameterMap.put("isConcerned",isConcerned);
                    parameterMap.put("isDoctor",0);
                    paramList.add(parameterMap);
                    }
                    else if(userConcern.getConcernedDoctor() != 0){
                        Integer doctorId = userConcern.getConcernedDoctor();
                        Map<String, Object> parameterMap = new HashMap<>();
                        Doctor doctor = doctorService.getOneOfDoctorById(doctorId);
                        List<Invited> invitedList = invitationService.getOneDoctorInvitedPs(doctorId, 1);
                        //获取所有被邀请人名字和总数量
                        List<User> invitedUser = new ArrayList<>();
                        for (Invited invited : invitedList) {
                            User intUser = userService.findById(invited.getInvitedPrsId());
                            invitedUser.add(intUser);
                        }
                        Integer countInvited = invitedUser.size();
                        doctor.setCountSuccessMedPeople(countInvited);
                        parameterMap.put("id", doctor.getId());
                        parameterMap.put("name", doctor.getDoctorName());
                        parameterMap.put("txPhoto", doctor.getDoctorAvator());
                        parameterMap.put("jjrLevel", doctor.getJjrLevel());
                        parameterMap.put("countSuccessMedPeople", countInvited);
                        //加载该用户是否已经关注过
                        Integer isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, null, doctor.getId());
                        parameterMap.put("isConcerned", isConcerned);
                        parameterMap.put("isDoctor",1);
                        paramList.add(parameterMap);
                    }
                }
                return paramList;
            }
}
