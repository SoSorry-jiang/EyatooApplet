package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorProjectDao;
import com.eyatoo.pojo.DoctorProject;
import com.eyatoo.service.DoctorProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorProjectServiceImpl implements DoctorProjectService {
    @Resource
    private DoctorProjectDao doctorProjectDao;

    @Override
    public List<DoctorProject> selectProFormDoctorById(Integer projectId) {
        List<DoctorProject> idList = new ArrayList<>();
        try {
            idList = doctorProjectDao.selectProFormDoctorById(projectId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public List<DoctorProject> selectDoctorFormProById(Integer doctorId) {
        List<DoctorProject> idList = new ArrayList<>();
        try {
            idList = doctorProjectDao.selectDoctorFormProById(doctorId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public List<Integer> selectDoctorDoPro(String proDo) {
        List<Integer> idList = new ArrayList<>();
        try {
            idList = doctorProjectDao.selectDoctorDoPro(proDo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return idList;
    }

    @Override
    public double getMoneyByDoctorAndBranchId(Integer doctorId, Integer projectId) {
        double money = 0;
        try {
            money = doctorProjectDao.getMoneyByDoctorAndBranchId(doctorId,projectId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return money;
    }

    @Override
    public int addProjectDoctor(DoctorProject doctorProject) {
        int num = 0;
        try {
            num = doctorProjectDao.addProjectDoctor(doctorProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<DoctorProject> selectProjectDoctor(Integer branchId) {
        List<DoctorProject> doctorProjects = new ArrayList<>();
        try {
            doctorProjects = doctorProjectDao.selectProjectDoctor(branchId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorProjects;
    }
}
