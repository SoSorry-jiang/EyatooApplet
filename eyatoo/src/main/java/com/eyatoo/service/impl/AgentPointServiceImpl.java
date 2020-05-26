package com.eyatoo.service.impl;

import com.eyatoo.dao.AgentPointDao;
import com.eyatoo.service.AgentPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AgentPointServiceImpl implements AgentPointService {
    @Resource
    private AgentPointDao agentPointDao;

    @Override
    public Integer addCountPoints(Integer num,Integer id) {
        Integer isOk = 0;
        try{
                 isOk = agentPointDao.addCountPoints(num,id);
        }catch (Exception e){
             e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateUsedPoints(Integer num,Integer id) {
        Integer isOk = 0;
        try{
             isOk = agentPointDao.updateUsedPoints(num,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateUserUsedUserPoints(Integer num, Integer id) {
        Integer isOk = 0;
        try{
            isOk = agentPointDao.updateUserUsedUserPoints(num,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer addDoctorCountPoints(Integer num, Integer id) {
        Integer isOk = 0;
        try{
            isOk = agentPointDao.addDoctorCountPoints(num,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateDoctorUsedPoints(Integer num, Integer id) {
        Integer isOk = 0;
        try{
            isOk = agentPointDao.updateDoctorUsedPoints(num,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }

    @Override
    public Integer updateDoctorUsedUserPoints(Integer num, Integer id) {
        Integer isOk = 0;
        try{
            isOk = agentPointDao.updateDoctorUsedUserPoints(num,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
}
