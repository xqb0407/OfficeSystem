package xyz.oa.dao;

import org.apache.ibatis.annotations.Param;
import xyz.oa.entity.ProcessFlow;

import java.util.List;

public interface ProcessFlowDao {
    void Insert(ProcessFlow processFlow);
    List<ProcessFlow> selectByFromId(@Param("value") Long from_Id);
    void updateProcess(ProcessFlow processFlow); //更新processflow表
}
