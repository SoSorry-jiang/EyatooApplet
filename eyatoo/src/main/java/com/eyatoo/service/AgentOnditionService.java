package com.eyatoo.service;

import com.eyatoo.pojo.AgentOndition;

import java.util.List;

public interface AgentOnditionService {
    //根据想要升级到的经纪人等级，获取条件
    public AgentOndition getAgentOnditionByLevel(Integer level);
}
