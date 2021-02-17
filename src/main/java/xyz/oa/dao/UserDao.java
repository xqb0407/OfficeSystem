package xyz.oa.dao;

import xyz.oa.entity.Employee;
import xyz.oa.entity.User;

public interface UserDao {
    User Login(String username); //登录
    Employee SelectEmployeeById(Long employeeId);//查询登录用户的信息
    Long StatisticalUser(); //统计用户
}
