package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorWorkDateDao;
import com.eyatoo.pojo.DoctorWorkDate;
import com.eyatoo.service.DoctorWorkDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DoctorWorkDateServiceImpl implements DoctorWorkDateService {
    @Resource
    private DoctorWorkDateDao doctorWorkDateDao;

    @Override
    public Integer addDoctorWorkDate(DoctorWorkDate doctorWorkDate) {
        Integer isOk = 0;
        try {
              isOk = doctorWorkDateDao.addDoctorWorkDate(doctorWorkDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateDoctorWorkDate(DoctorWorkDate doctorWorkDate) {
        Integer isOk = 0;
        try {
            isOk = doctorWorkDateDao.updateDoctorWorkDate(doctorWorkDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer delDoctorWorkDate(Integer id) {
        Integer isOk = 0;
        try {
            isOk = doctorWorkDateDao.delDoctorWorkDate(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public DoctorWorkDate getDefaultDoctorWorkDate(Integer doctorId) {
        DoctorWorkDate doctorWorkDate = null;
        try {
            doctorWorkDate =  doctorWorkDateDao.getDefaultDoctorWorkDate(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorWorkDate;
    }

    @Override
    public Integer getCountByDoctorWorkDate(Integer doctorId) {
        Integer isOk = 0;
        try {
            isOk = doctorWorkDateDao.getCountByDoctorWorkDate(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer yzIsWorkDate(Integer doctorId, String date) {
        Integer isOK = 0;
        try {
          isOK = doctorWorkDateDao.yzIsWorkDate(doctorId, date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
