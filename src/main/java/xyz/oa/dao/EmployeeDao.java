package xyz.oa.dao;

import org.apache.ibatis.annotations.Param;
import xyz.oa.entity.Employee;

public interface EmployeeDao {
    Employee SelectEmpById(Long employeeId);  //根据id查询员工信息
    Employee SelectLeader(@Param("Employee") Employee employee); //查询部门经理
}
