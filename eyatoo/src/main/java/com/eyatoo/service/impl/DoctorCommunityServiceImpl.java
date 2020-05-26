package com.eyatoo.service.impl;

import com.eyatoo.dao.DoctorCommunityDao;
import com.eyatoo.pojo.DoctorCommunity;
import com.eyatoo.service.DoctorCommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorCommunityServiceImpl implements DoctorCommunityService{
    @Resource
    private DoctorCommunityDao doctorCommunityDao;

    @Override
    public Integer addDoctorCommunity(DoctorCommunity doctorCommunity) {
        Integer isOk = 0;
        try {
            isOk = doctorCommunityDao.addDoctorCommunity(doctorCommunity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public List<DoctorCommunity> getAllDoctorCommunityByTag(String tag) {
        List<DoctorCommunity> doctorCommunities = new ArrayList<>();
        try {
             doctorCommunities = doctorCommunityDao.getAllDoctorCommunityByTag(tag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCommunities;
    }

    @Override
    public DoctorCommunity getOneDoctorCommunityById(Integer id) {
        DoctorCommunity doctorCommunity = new DoctorCommunity();
        try {
            doctorCommunity = doctorCommunityDao.getOneDoctorCommunityById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCommunity;
    }


    @Override
    public List<DoctorCommunity> getOneOfDoctorCommunitById(Integer id) {
        List<DoctorCommunity> doctorCommunityList = new ArrayList<>();
        try {
             doctorCommunityList = doctorCommunityDao.getOneOfDoctorCommunitById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorCommunityList;
    }

    @Override
    public Integer updateBrowseVolume(Integer id) {
        Integer isOK = 0;
        try {
            isOK = doctorCommunityDao.updateBrowseVolume(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOK;
    }

    @Override
    public Integer updatePraise(Integer id, Integer praise) {
        Integer isOk = 0;
        try {
            isOk = doctorCommunityDao.updatePraise(id, praise);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
