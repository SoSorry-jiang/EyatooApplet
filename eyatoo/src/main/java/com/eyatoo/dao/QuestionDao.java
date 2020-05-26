package com.eyatoo.dao;

import com.eyatoo.pojo.Question;

import java.util.List;

public interface QuestionDao {
    //根据标签查询问题
    List<Question> getQuestionByTag(String tag);
    //添加问题
    Integer addQuestion(Question question);
    //获得单个用户的问题
    List<Question> getOneUserQuestions(Integer id);
    //增加浏览量
    Integer updateBrowse(Integer id);
    //根据id拿到单个用户的问题
    Question getOneQuestionById(Integer id);
    //更新点赞
    Integer updatePraise(Integer id,Integer praise);
}
