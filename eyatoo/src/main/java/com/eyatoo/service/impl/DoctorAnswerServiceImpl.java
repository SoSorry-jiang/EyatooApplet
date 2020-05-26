package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorAnswerDao;
import com.eyatoo.pojo.DoctorAnswer;
import com.eyatoo.service.DoctorAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorAnswerServiceImpl implements DoctorAnswerService {
    @Resource
    private DoctorAnswerDao doctorAnswerDao;


    @Override
    public Integer addAnswer(DoctorAnswer doctorAnswer) {
        Integer isOk = 0;
        try {
            isOk = doctorAnswerDao.addAnswer(doctorAnswer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer deleteAnswer(Integer id) {
        Integer isOk = 0;
        try {
           isOk = doctorAnswerDao.deleteAnswer(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<DoctorAnswer> getOneDoctorAnswer(Integer doctorId) {
        List<DoctorAnswer> doctorAnswers = new ArrayList<>();
        try {
             doctorAnswers = doctorAnswerDao.getOneDoctorAnswer(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorAnswers;
    }

    @Override
    public DoctorAnswer getOneAnswer(Integer id) {
        DoctorAnswer doctorAnswer = new DoctorAnswer();
        try {
             doctorAnswer = doctorAnswerDao.getOneAnswer(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorAnswer;
    }

    @Override
    public List<DoctorAnswer> loginAnswerList(Integer questionId) {
        List<DoctorAnswer> doctorAnswers = new ArrayList<>();
        try {
            doctorAnswers = doctorAnswerDao.loginAnswerList(questionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorAnswers;
    }

    @Override
    public Integer updatePhoto(Integer id, String path) {
        Integer isOK = 0 ;
        try {
              isOK = doctorAnswerDao.updatePhoto(id,path);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updatePraise(Integer id, Integer praise) {
        Integer isOk = 0;
        try {
              isOk = doctorAnswerDao.updatePraise(id, praise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
