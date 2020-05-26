package com.eyatoo.controller;

import com.eyatoo.pojo.Doctor;
import com.eyatoo.pojo.Invited;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserConcern;
import com.eyatoo.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorConcernController {

    @Resource
    private UserService userService;

    @Resource
    private DoctorConcernService doctorConcernService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private UserConcernService userConcernService;

    @Resource
    private InvitationService invitationService;

    //当医关注用户
    @RequestMapping("/doctorConcernedUser")
    public Integer doctorConcernedUser(Integer doctor_id,Integer concerned_user){
        Integer isOK = 0;
        if(userConcernService.isConcernedOrNot(null,doctor_id,concerned_user,null) == 0) {
            isOK = doctorConcernService.doctorConcernedUser(doctor_id, concerned_user);
        }
        return  isOK;
    }

    //当医生关注医生
    @RequestMapping("/doctorConcernedDoctor")
    public  Integer doctorConcernedDoctor(Integer doctor_id,Integer concerned_doctor){
        Integer isOK = 0;
        if(userConcernService.isConcernedOrNot(null,doctor_id,null,concerned_doctor) != 0) {
             isOK = doctorConcernService.doctorConcernedDoctor(doctor_id, concerned_doctor);
        }
        return  isOK;
    }


    //当医生取消关注用户
    @RequestMapping("doctorDisConcernedUser")
    private Integer doctorConcerned(Integer doctor_id,Integer concerned_user){
        Integer isOK = 0;
        if(userConcernService.isConcernedOrNot(null,doctor_id,concerned_user,null) == 0) {
            isOK = doctorConcernService.doctorDisConcernedUser(doctor_id, concerned_user);
        }
        return isOK;
    }

    //当医生取消关注医生
    @RequestMapping("doctorDisConcernedDoctor")
    private Integer doctorDisConcerned(Integer doctor_id,Integer concerned_doctor){
        Integer isOK = 0;
        if(userConcernService.isConcernedOrNot(null,doctor_id,null,concerned_doctor) != 0) {
            isOK = doctorConcernService.doctorDisConcernedDoctor(doctor_id, concerned_doctor);
        }
        return isOK;
    }

    //查询单个医生所有粉丝
    @RequestMapping("getDoctorAllConcerntion")
    public List<Map<String,Object>> getDoctorAllConcerntion(Integer doctorid,Integer nowUserId,Integer nowDoctorId){
        List<Map<String,Object>> paramList = new ArrayList<>();
        List<UserConcern> userConcerntionIdList = doctorConcernService.getDoctorConcerntionList(doctorid);
        for(UserConcern userConcern:userConcerntionIdList) {
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
                Integer isConcerned = 0;
                if(nowUserId != null && nowUserId != 0) {
                    isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, user.getId(), null);
                }else if(nowDoctorId != null && nowDoctorId != 0){
                    isConcerned = userConcernService.isConcernedOrNot(null, nowDoctorId, user.getId(), null);
                }
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
                Integer isConcerned = 0;
                if(nowUserId != null && nowUserId != 0) {
                    isConcerned = userConcernService.isConcernedOrNot(nowUserId, null,null,  doctor.getId());
                }else if(nowDoctorId != null && nowDoctorId != 0){
                    isConcerned = userConcernService.isConcernedOrNot(null, nowDoctorId,null , doctor.getId());
                }
                parameterMap.put("isConcerned", isConcerned);
                parameterMap.put("isConcerned", isConcerned);
                parameterMap.put("isDoctor",1);
                paramList.add(parameterMap);
            }
        }
        return paramList;
    }

    //查询单个医生所有关注
    @RequestMapping("/getDoctorAllConcerned")
    public List<Map<String,Object>> getDoctorAllConcerned(Integer doctorid,Integer nowUserId,Integer nowDoctorId){
        List<Map<String,Object>> paramList = new ArrayList<>();
        List<UserConcern> doctorConcerntionIdList = doctorConcernService.getDoctorConcernedList(doctorid);
        for(UserConcern userConcern:doctorConcerntionIdList) {
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
                Integer isConcerned = 0;
                if(nowUserId != null && nowUserId != 0) {
                    isConcerned = userConcernService.isConcernedOrNot(nowUserId, null, user.getId(), null);
                }else if(nowDoctorId != null && nowDoctorId != 0){
                    isConcerned = userConcernService.isConcernedOrNot(null, nowDoctorId, user.getId(), null);
                }
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
                Integer isConcerned = 0;
                if(nowUserId != null && nowUserId != 0) {
                    isConcerned = userConcernService.isConcernedOrNot(nowUserId, null,null,  doctor.getId());

                }else if(nowDoctorId != null && nowDoctorId != 0){
                    isConcerned = userConcernService.isConcernedOrNot(null, nowDoctorId,null , doctor.getId());
                }
                parameterMap.put("isConcerned", isConcerned);
                parameterMap.put("isDoctor",1);
                paramList.add(parameterMap);
            }
        }
        return paramList;
    }
}
