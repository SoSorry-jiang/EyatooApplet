package com.eyatoo.controller;

import com.eyatoo.pojo.DoctorCommunity;
import com.eyatoo.pojo.UserPraise;
import com.eyatoo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPraiseController {
    @Autowired
    private UserPraiseService userPraiseService;

    @Autowired
    private DoctorAnswerService doctorAnswerService;

    @Autowired
    private DoctorCaseService doctorCaseService;

    @Autowired
    private DoctorCommunityService doctorCommunityService;

    @Autowired
    private UserCommunityService userCommunityService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserCommunityCommentService userCommunityCommentService;

    @Autowired
    private DoctorCommunityCommentService doctorCommunityCommentService;

    @Autowired
    private QuestionCommunityCommentService questionCommunityCommentService;

    //用户或医生点赞时
    @RequestMapping("/userOrDoctorPraise")
    public Integer userPraise(UserPraise userPraise){
        Integer isSuccess = 0;
        Integer praise = 1;
        if(userPraiseService.loginUserPraise(userPraise) == 0) {
            try {
                if (userPraise.getUserId() != null || userPraise.getDoctorId() != null) {
                    if (userPraise.getDoctorAnswerId() != null) {
                        doctorAnswerService.updatePraise(userPraise.getDoctorAnswerId(), praise);
                    } else if (userPraise.getDoctorCaseId() != null) {
                        doctorCaseService.updatePraise(userPraise.getDoctorCaseId(), praise);
                    } else if (userPraise.getDoctorCommunityId() != null) {
                        doctorCommunityService.updatePraise(userPraise.getDoctorCommunityId(), praise);
                    } else if (userPraise.getUserCommunityId() != null) {
                        userCommunityService.updatePraise(userPraise.getUserCommunityId(), praise);
                    } else if (userPraise.getQuestionId() != null) {
                        questionService.updatePraise(userPraise.getQuestionId(), praise);
                    } else if (userPraise.getUserCommunityCommentId() != null) {
                        userCommunityCommentService.updateCommentPrase(userPraise.getUserCommunityCommentId(), praise);
                    } else if (userPraise.getDoctorCommunityCommentId() != null) {
                        doctorCommunityCommentService.updateCommentPrase(userPraise.getDoctorCommunityCommentId(), praise);
                    } else if (userPraise.getQuestionCommunityCommentId() != null) {
                        questionCommunityCommentService.updateCommentPrase(userPraise.getQuestionCommunityCommentId(), praise);
                    }
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isSuccess = userPraiseService.addPraise(userPraise);
            }
        }
        return  isSuccess;
    }


    //用户或医生取消点赞
    @RequestMapping("/userOrDoctorDisPraise")
    public Integer userDisPraise(UserPraise userPraise){
        Integer isSuccess = 0;
        Integer praise = -1;
        if(userPraiseService.loginUserPraise(userPraise) == 0) {
            try {
                if (userPraise.getUserId() != null || userPraise.getDoctorId() != null) {
                    if (userPraise.getDoctorAnswerId() != null) {
                        doctorAnswerService.updatePraise(userPraise.getDoctorAnswerId(), praise);
                    } else if (userPraise.getDoctorCaseId() != null) {
                        doctorCaseService.updatePraise(userPraise.getDoctorCaseId(), praise);
                    } else if (userPraise.getDoctorCommunityId() != null) {
                        doctorCommunityService.updatePraise(userPraise.getDoctorCommunityId(), praise);
                    } else if (userPraise.getUserCommunityId() != null) {
                        userCommunityService.updatePraise(userPraise.getUserCommunityId(), praise);
                    } else if (userPraise.getQuestionId() != null) {
                        questionService.updatePraise(userPraise.getQuestionId(), praise);
                    } else if (userPraise.getUserCommunityCommentId() != null) {
                        userCommunityCommentService.updateCommentPrase(userPraise.getUserCommunityCommentId(), praise);
                    } else if (userPraise.getDoctorCommunityCommentId() != null) {
                        doctorCommunityCommentService.updateCommentPrase(userPraise.getDoctorCommunityCommentId(), praise);
                    } else if (userPraise.getQuestionCommunityCommentId() != null) {
                        questionCommunityCommentService.updateCommentPrase(userPraise.getQuestionCommunityCommentId(), praise);
                    }
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isSuccess = userPraiseService.delPraise(userPraise);
            }
        }
        return  isSuccess;
    }
}
