package xyz.oa.entity;

import java.util.Date;

public class Flow {
    private Long flowId;
    private Date flowTime;
    private Integer flowNum;
    private String flowMethod;
    private Long userId;


    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Date getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Date flowTime) {
        this.flowTime = flowTime;
    }

    public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }

    public String getFlowMethod() {
        return flowMethod;
    }

    public void setFlowMethod(String flowMethod) {
        this.flowMethod = flowMethod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
