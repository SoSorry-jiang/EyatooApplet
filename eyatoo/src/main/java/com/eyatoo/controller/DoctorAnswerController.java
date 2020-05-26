package com.eyatoo.controller;

import com.eyatoo.config.FTPConfig;
import com.eyatoo.pojo.*;
import com.eyatoo.service.*;
import com.eyatoo.utils.Random.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DoctorAnswerController {
    @Autowired
    private DoctorAnswerService doctorAnswerService;

    @Autowired
    private QuestionCommunityCommentService  questionCommunityCommentService;

    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private UserPraiseService userPraiseService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserConcernService userConcernService;

    //医生添加回答
    @RequestMapping("/addDoctorAnswer")
    private Integer addDoctorAnswer(DoctorAnswer doctorAnswer){
        Doctor doctor = doctorService.getOneOfDoctorById(doctorAnswer.getDoctorId());
        doctorAnswer.setDoctorAvator(doctor.getDoctorAvator());
        doctorAnswer.setDoctorName(doctor.getDoctorName());
        if(doctorAnswer.getPictureA() == null  || doctorAnswer.getPictureA().equals("undefined"))
            doctorAnswer.setPictureA(null);
        if(doctorAnswer.getPictureB() == null  || doctorAnswer.getPictureB().equals("undefined"))
            doctorAnswer.setPictureB(null);
        if(doctorAnswer.getPictureC() == null  || doctorAnswer.getPictureC().equals("undefined") )
            doctorAnswer.setPictureC(null);
        if(doctorAnswer.getPictureD() == null || doctorAnswer.getPictureD().equals("undefined")  )
            doctorAnswer.setPictureD(null);
        if(doctorAnswer.getPictureE() == null ||  doctorAnswer.getPictureE().equals("undefined") )
            doctorAnswer.setPictureE(null);
        Integer isOK = doctorAnswerService.addAnswer(doctorAnswer);
        return  isOK;
    }

    //删除一个回答
    @RequestMapping("/deleteDoctorAnswer")
    private Integer deleteDoctorAnswer(Integer id){
        Integer isOk = doctorAnswerService.deleteAnswer(id);
        return isOk;
    }

    //加载一个医生的所有回答
    @RequestMapping("/getOneDoctorAnswer")
    private List<DoctorAnswer> getOneDoctorAnswer(Integer doctorId){
        List<DoctorAnswer> doctorAnswers = doctorAnswerService.getOneDoctorAnswer(doctorId);
        return doctorAnswers;
    }

    //加载单个回答
    @RequestMapping("/getOneAnswer")
    private Map<String,Object> getOneAnswer(Integer id,Integer userId){
        Map<String,Object> paraterMap = new HashMap<>();
        DoctorAnswer doctorAnswer = doctorAnswerService.getOneAnswer(id);

        //获取该帖子下所有评论和回复列表
        List<QuestionCommunityComment> questionCommunityComments = questionCommunityCommentService.getAllCommunityCommentById(doctorAnswer.getId());
        List<QuestionCommunityComment> questionCommunityCommentList = new ArrayList<>();
        Integer countComment = questionCommunityComments.size();
        if (countComment == null){
            countComment = 0;
        }
        doctorAnswer.setCountCommunityComment(countComment);
        //加载该用户是否已经关注过
        Integer isConcerned = userConcernService.isConcernedOrNot(userId, null,null, doctorAnswer.getDoctorId());
        doctorAnswer.setIsConcerned(isConcerned);
        //判断用户是否已关注过发帖医生
        UserPraise userPraise1 = new UserPraise();
        userPraise1.setUserId(userId);
        userPraise1.setDoctorAnswerId(doctorAnswer.getId());
        Integer isPraiseAnswer = userPraiseService.loginUserPraise(userPraise1);
        doctorAnswer.setIsPraise(isPraiseAnswer);
        //判断用户是否点过赞
        for (QuestionCommunityComment questionCommunityComment:questionCommunityComments) {
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(userId);
            userPraise.setQuestionCommunityCommentId(questionCommunityComment.getId());
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
            questionCommunityComment.setIsPraise(isPraise);
            questionCommunityCommentList.add(questionCommunityComment);
        }
        List<QuestionCommunityCommentReply> questionCommunityCommentServiceAllReplyLists = questionCommunityCommentService.getAllReplyListByCommunityId(doctorAnswer.getId());
//        List<QuestionCommunityCommentReply> questionCommunityCommentServiceAllReplyList = new ArrayList<>();
//        //判断用户是否点过赞
//        for (QuestionCommunityCommentReply questionCommunityCommentReply:questionCommunityCommentServiceAllReplyLists) {
//            UserPraise userPraise = new UserPraise();
//            userPraise.setUserId(userId);
//            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
//            questionCommunityCommentReply.setIsPraise(isPraise);
//            questionCommunityCommentServiceAllReplyList.add(questionCommunityCommentReply);
//        }
        paraterMap.put("doctorAnswer",doctorAnswer);
        paraterMap.put("questionCommunityCommentList",questionCommunityCommentList);
        paraterMap.put("questionCommunityCommentServiceAllReplyList",questionCommunityCommentServiceAllReplyLists);
        return paraterMap;
    }
}
