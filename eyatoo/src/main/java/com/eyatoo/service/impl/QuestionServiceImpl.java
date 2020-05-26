package com.eyatoo.service.impl;

import com.eyatoo.dao.QuestionDao;
import com.eyatoo.pojo.Question;
import com.eyatoo.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Override
    public List<Question> getQuestionByTag(String tag) {
        List<Question> questionList = null;
        try {
           questionList = questionDao.getQuestionByTag(tag);
        }catch (Exception e){
             e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public List<Question> getOneUserQuestions(Integer id) {
        List<Question> questionList = null;
        try {
            questionList = questionDao.getOneUserQuestions(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public Integer addQuestion(Question question) {
        Integer num = 0;
        try {
            num = questionDao.addQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer updateBrowse(Integer id) {
        Integer isOK = 0;
        try {
            isOK = questionDao.updateBrowse(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Question getOneQuestionById(Integer id) {
        Question question = new Question();
        try {
            question = questionDao.getOneQuestionById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public Integer updatePraise(Integer id, Integer praise) {
        Integer isOk = 0;
        try {
            isOk = questionDao.updatePraise(id, praise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
