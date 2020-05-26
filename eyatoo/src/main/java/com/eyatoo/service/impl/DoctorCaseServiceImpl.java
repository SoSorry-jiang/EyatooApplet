package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorCaseDao;
import com.eyatoo.pojo.DoctorCase;
import com.eyatoo.service.DoctorCaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorCaseServiceImpl implements DoctorCaseService {
    @Resource
    private DoctorCaseDao doctorCaseDao;

    @Override
    public Integer addDoctorCase(DoctorCase doctorCase) {
        Integer isOK = null;
        try {
         isOK = doctorCaseDao.addDoctorCase(doctorCase);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer deleteDoctorCase(Integer id) {
        Integer isOK = null;
        try {
            isOK = doctorCaseDao.deleteDoctorCase(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public List<DoctorCase> getOneDoctorAllCase(Integer doctorId) {
        List<DoctorCase> doctorCases = new ArrayList<>();
        try {
            doctorCases = doctorCaseDao.getOneDoctorAllCase(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCases;
    }

    @Override
    public DoctorCase getOneCase(Integer id) {
        DoctorCase doctorCase = new DoctorCase();
        try {
             doctorCase = doctorCaseDao.getOneCase(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCase;
    }

    @Override
    public Integer updatePraise(Integer id, Integer praise) {
        Integer isOk = 0;
        try {
            isOk = doctorCaseDao.updatePraise(id, praise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updatePhoto(Integer id, String path) {
        Integer isOK =0;
        try {
            isOK = doctorCaseDao.updatePhoto(id, path);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }
}
