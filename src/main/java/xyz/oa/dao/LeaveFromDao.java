package xyz.oa.dao;

import org.apache.ibatis.annotations.Param;
import xyz.oa.entity.LeaveFrom;

import java.util.List;
import java.util.Map;

public interface LeaveFromDao {
    void Insert(LeaveFrom leaveFrom);
    //查询审批列表
    List<Map> SelectByParems(@Param("apf_state") String apfState, @Param("apf_operatop_id") Long apfOperatopId);

    // void ApproveFrom(Long operatopId, Long fromId, String result, String reason); //审批
    void UpdateFromLeave(LeaveFrom leaveFrom); //更新请假表
    LeaveFrom SelectLeaveFromByFormId(Long fromId); //查询请假表根据formId
}