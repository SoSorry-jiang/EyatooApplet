package com.eyatoo.controller;


import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class UserCommunityCommentController {
      @Resource
      private DoctorService doctorService;

      @Resource
      private UserService userService;

      @Resource
      private UserCommunityCommentService userCommunityCommentService;

      @Resource
      private DoctorCommunityCommentService doctorCommunityCommentService;

      @Resource
      private QuestionCommunityCommentService questionCommunityCommentService;

      //用户或医生在用户帖子下发表发表评论
      @RequestMapping("/userOrDoctorAddUserCommunityComment")
      public Integer userOrDoctorAddCommunityComment(UserCommunityComment userCommunityComment){
          if(userCommunityComment.getDoctorId() != null){
               Doctor doctor = doctorService.getOneOfDoctorById(userCommunityComment.getDoctorId());
               userCommunityComment.setUserAvator(doctor.getDoctorAvator());
               userCommunityComment.setUserName(doctor.getDoctorName());
               userCommunityComment.setUserLevel(doctor.getJjrLevel());
          }else if(userCommunityComment.getUserId() != null){
               User user = userService.findById(userCommunityComment.getUserId());
               userCommunityComment.setUserAvator(user.getTxPhoto());
               userCommunityComment.setUserName(user.getName());
               userCommunityComment.setUserLevel(user.getJjrLevel());
          }
          return userCommunityCommentService.addCommunityComment(userCommunityComment);
      }

      //用户或医生在用户帖子下发表回复
      @RequestMapping("/userOrDoctorAddUserCommunityCommentReply")
      public Integer userOrDoctorAddCommunityCommentReply(UserCommunityCommentReply userCommunityCommentReply){
          if(userCommunityCommentReply.getDoctorId() != null){
              Doctor doctor = doctorService.getOneOfDoctorById(userCommunityCommentReply.getDoctorId());
              userCommunityCommentReply.setUserName(doctor.getDoctorName());
          }else if(userCommunityCommentReply.getUserId() != null){
              User user = userService.findById(userCommunityCommentReply.getUserId());
              userCommunityCommentReply.setUserName(user.getName());
          }
          return  userCommunityCommentService.addCommentReply(userCommunityCommentReply);
      }

      //用户或医生在医生回答帖子下发表评论
      @RequestMapping("/userOrDoctorAddQuestionCommunityComment")
      public Integer userOrDoctorAddQuestionCommunityComment(QuestionCommunityComment questionCommunityComment){
          if(questionCommunityComment.getDoctorId() != null){
              Doctor doctor = doctorService.getOneOfDoctorById(questionCommunityComment.getDoctorId());
              questionCommunityComment.setUserAvator(doctor.getDoctorAvator());
              questionCommunityComment.setUserName(doctor.getDoctorName());
              questionCommunityComment.setUserLevel(doctor.getJjrLevel());
          }else if(questionCommunityComment.getUserId() != null){
              User user = userService.findById(questionCommunityComment.getUserId());
              questionCommunityComment.setUserAvator(user.getTxPhoto());
              questionCommunityComment.setUserName(user.getName());
              questionCommunityComment.setUserLevel(user.getJjrLevel());
          }
          return questionCommunityCommentService.addCommunityComment(questionCommunityComment);
      }

      //用户或医生在回答帖子下发表评论回复
      @RequestMapping("/userOrDoctorAddQuestionCommunityCommentReply")
      public Integer userOrDoctorAddQuestionCommunityCommentReply(QuestionCommunityCommentReply questionCommunityCommentReply){
          if(questionCommunityCommentReply.getDoctorId() != null){
              Doctor doctor = doctorService.getOneOfDoctorById(questionCommunityCommentReply.getDoctorId());
              questionCommunityCommentReply.setUserName(doctor.getDoctorName());
          }else if(questionCommunityCommentReply.getUserId() != null){
              User user = userService.findById(questionCommunityCommentReply.getUserId());
              questionCommunityCommentReply.setUserName(user.getName());
          }
         return questionCommunityCommentService.addCommentReply(questionCommunityCommentReply);
      }

      //用户或医生在医生帖子下发表评论
      @RequestMapping("/userOrDoctorAddDoctorCommunityComment")
      public Integer userOrDoctorAddDoctorCommunityComment(DoctorCommunityComment doctorCommunityComment){
          if(doctorCommunityComment.getDoctorId() != null){
              Doctor doctor = doctorService.getOneOfDoctorById(doctorCommunityComment.getDoctorId());
              doctorCommunityComment.setUserAvator(doctor.getDoctorAvator());
              doctorCommunityComment.setUserName(doctor.getDoctorName());
              doctorCommunityComment.setUserLevel(doctor.getJjrLevel());
          }else if(doctorCommunityComment.getUserId() != null){
              User user = userService.findById(doctorCommunityComment.getUserId());
              doctorCommunityComment.setUserAvator(user.getTxPhoto());
              doctorCommunityComment.setUserName(user.getName());
              doctorCommunityComment.setUserLevel(user.getJjrLevel());
          }
          return  doctorCommunityCommentService.addCommunityComment(doctorCommunityComment);
      }

      //用户或医生在医生帖子下发表评论回复
      @RequestMapping("/userOrDoctorAddDoctorCommunityCommentReply")
      public  Integer userOrDoctorAddDoctorCommunityCommentReply(DoctorCommunityCommentReply doctorCommunityCommentReply){
          if(doctorCommunityCommentReply.getDoctorId() != null){
              Doctor doctor = doctorService.getOneOfDoctorById(doctorCommunityCommentReply.getDoctorId());
              doctorCommunityCommentReply.setUserName(doctor.getDoctorName());
          }else if(doctorCommunityCommentReply.getUserId() != null){
              User user = userService.findById(doctorCommunityCommentReply.getUserId());
              doctorCommunityCommentReply.setUserName(user.getName());
          }
          return doctorCommunityCommentService.addCommentReply(doctorCommunityCommentReply);
      }


      //用户点赞评论
       @RequestMapping("/updatePrase")
       @ResponseBody
       public Integer  updatePrase(Integer id,Integer number){
          Integer isok = userCommunityCommentService.updateCommentPrase(id,number);
          if (isok!=0){
              System.out.println("成功！");
          }else{
              System.out.println("失败");
          }
          return  isok;
       }
}
