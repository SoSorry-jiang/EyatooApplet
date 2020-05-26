package com.eyatoo.service.impl;

import com.eyatoo.dao.AgentOnditionDao;
import com.eyatoo.pojo.AgentOndition;
import com.eyatoo.service.AgentOnditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgentOnditionServiceImpl implements AgentOnditionService {
    @Resource
    private AgentOnditionDao agentOnditionDao;

    @Override
    public AgentOndition getAgentOnditionByLevel(Integer level) {
        AgentOndition agentOnditions = new AgentOndition();
        try {
              agentOnditions = agentOnditionDao.getAgentOnditionBy(level);
        }catch (Exception e){
            e.printStackTrace();
        }
        return agentOnditions;
    }
}
