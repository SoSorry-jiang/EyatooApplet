package com.eyatoo.dao;

import com.eyatoo.pojo.AgentOndition;

import java.util.List;

public interface AgentOnditionDao {
    //根据想要升级到的经纪人等级，获取条件
    AgentOndition getAgentOnditionBy(Integer level);
}
