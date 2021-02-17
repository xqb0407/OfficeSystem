package xyz.oa.service;

import xyz.oa.entity.LeaveFrom;

import java.util.List;
import java.util.Map;


public interface LeaveFromService {
    LeaveFrom createLeaveFrom(LeaveFrom leaveFrom);
    List<Map> SelectExamineFrom(Long employeeId,Integer page ,Integer limit); //查询审批列表
    void ApproveFrom(Long operatopId, Long formId, String result, String reason); //审批
    // void Approve(String Re);
}
