package xyz.oa.dao;

import xyz.oa.entity.Department;

public interface DepartmentDao {
    Department SelectBydeparTmentId(Long departmentId); //查询用户所在部门
}
