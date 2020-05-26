package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorConcernDao;
import com.eyatoo.pojo.UserConcern;
import com.eyatoo.service.DoctorConcernService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorConcernServiceImpl implements DoctorConcernService {
    @Resource
    private DoctorConcernDao doctorConcernDao;
    @Override
    public List<UserConcern> getDoctorConcerntionList(Integer id) {
        List<UserConcern> doctorConcernList = new ArrayList<>();
        try {
            doctorConcernList = doctorConcernDao.getDoctorConcerntionList(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorConcernList;
    }

    @Override
    public List<UserConcern> getDoctorConcernedList(Integer id) {
        List<UserConcern> doctorConcernList = new ArrayList<>();
        try {
            doctorConcernList = doctorConcernDao.getDoctorConcernedList(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorConcernList;
    }

    @Override
    public Integer doctorConcernedUser(Integer doctor_id, Integer concerned_user) {
        Integer isOK = 0;
        try {
            isOK = doctorConcernDao.doctorConcernedUser(doctor_id, concerned_user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer doctorDisConcernedUser(Integer doctor_id, Integer concerned_user) {
        Integer isOK = 0;
        try {
           isOK = doctorConcernDao.doctorDisConcernedUser(doctor_id, concerned_user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer doctorConcernedDoctor(Integer doctor_id, Integer concerned_doctor) {
        Integer isOK = 0;
        try {
            isOK = doctorConcernDao.doctorConcernedDoctor(doctor_id, concerned_doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer doctorDisConcernedDoctor(Integer doctor_id, Integer concerned_doctor) {
        Integer isOK = 0;
        try {
               isOK = doctorConcernDao.doctorDisConcernedUser(doctor_id, concerned_doctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
