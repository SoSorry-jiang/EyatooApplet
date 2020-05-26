package com.eyatoo.pojo;

import java.math.BigDecimal;

public class AgentOndition {
    private Integer id;
    private Integer agentLevel;
    private Integer agentPoint;
    private Integer agentIstreatmentCount;
    private BigDecimal agentIstreatmentMoneyCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
        this.agentLevel = agentLevel;
    }

    public Integer getAgentPoint() {
        return agentPoint;
    }

    public void setAgentPoint(Integer agentPoint) {
        this.agentPoint = agentPoint;
    }

    public Integer getAgentIstreatmentCount() {
        return agentIstreatmentCount;
    }

    public void setAgentIstreatmentCount(Integer agentIstreatmentCount) {
        this.agentIstreatmentCount = agentIstreatmentCount;
    }

    public BigDecimal getAgentIstreatmentMoneyCount() {
        return agentIstreatmentMoneyCount;
    }

    public void setAgentIstreatmentMoneyCount(BigDecimal agentIstreatmentMoneyCount) {
        this.agentIstreatmentMoneyCount = agentIstreatmentMoneyCount;
    }
}
