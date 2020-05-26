package com.eyatoo.controller;

import com.eyatoo.pojo.AgentOndition;
import com.eyatoo.service.AgentOnditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentOnditionController {

    @Autowired
    private AgentOnditionService agentOnditionService;

    //根据经纪人等级获取所需条件
    @RequestMapping("getAgentOndition")
    public AgentOndition getAgentOndition(Integer level){
        return agentOnditionService.getAgentOnditionByLevel(level);
    }
}
