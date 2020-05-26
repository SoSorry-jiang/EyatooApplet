package com.eyatoo.controller;

import com.eyatoo.pojo.DoctorAnswer;
import com.eyatoo.pojo.Question;
import com.eyatoo.pojo.User;
import com.eyatoo.pojo.UserPraise;
import com.eyatoo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionController {
   @Resource
    private QuestionService questionService;

   @Resource
   private UserService userService;

   @Autowired
   private DoctorAnswerService doctorAnswerService;

   @Autowired
   private QuestionCommunityCommentService questionCommunityCommentService;

   @Autowired
   private UserConcernService userConcernService;

   @Autowired
   private UserPraiseService userPraiseService;

    //加载所有问题列表
    @RequestMapping("/loginQuestion")
    public List<Question> loginQuestion(@RequestParam(defaultValue = "0") String tagId){
        List<Question> questionList = questionService.getQuestionByTag(tagId);
        return questionList;
    }

    //点击单个问题加载该问题下的所有回答目录,并添加浏览量
    @RequestMapping("/checkOneQuestion")
    public Map<String,Object> checkOneQuestion(Integer questionId,Integer userId){
        Map<String,Object> paramterMap = new HashMap<>();
        //增加浏览量
        questionService.updateBrowse(questionId);
        //获得单个问题
        Question question = questionService.getOneQuestionById(questionId);
        //获得问题列表
        List<DoctorAnswer> doctorAnswers  = doctorAnswerService.loginAnswerList(question.getId());
        List<DoctorAnswer> doctorAnswers1 = new ArrayList<>();
        for (DoctorAnswer doctorAnswer:doctorAnswers
             ) {
            Integer countComment = 0;
             if(questionCommunityCommentService.getAllCommunityCommentById(doctorAnswer.getId()) != null){
                 countComment = questionCommunityCommentService.getAllCommunityCommentById(doctorAnswer.getId()).size();
             }
            //加载该用户是否已经关注过
            Integer isConcerned = userConcernService.isConcernedOrNot(userId, null,null, doctorAnswer.getDoctorId());
            //加载该用户是否已经点赞过
            UserPraise userPraise = new UserPraise();
            userPraise.setUserId(userId);
            userPraise.setDoctorAnswerId(doctorAnswer.getId());
            Integer isPraise = userPraiseService.loginUserPraise(userPraise);
             doctorAnswer.setIsPraise(isPraise);
             doctorAnswer.setIsConcerned(isConcerned);
             doctorAnswer.setCountCommunityComment(countComment);
             doctorAnswers1.add(doctorAnswer);
        }

        paramterMap.put("question",question);
        paramterMap.put("doctorAnswers",doctorAnswers1);
        return paramterMap;
    }

    //当用户单击  ‘消息--问答’ 或者‘我的--问答’ 时，显示他自己的问答列表
    @RequestMapping("/getOneUserQuestions")
    public List<Question> getOneUserQuestions(Integer id){
        List<Question> questionList = questionService.getOneUserQuestions(id);
       return questionList;
    }

    //发布问题
    @RequestMapping("/addQueation")
    public Integer addQuestion(Integer id,Integer question_tagid,String question_content){
        Question question = new Question();
        User user = userService.findById(id);
        question.setUserId(user.getId());
        question.setQuestionTagId(question_tagid);
        question.setQuestionContent(question_content);
        question.setUserAvator(user.getTxPhoto());
        Integer num = questionService.addQuestion(question);
        return num;
    }



}
