package xyz.oa.service;

import xyz.oa.entity.Department;
import xyz.oa.entity.Employee;
import xyz.oa.entity.User;

public interface UserService {
    User Login(String username, String password, String vercode, String vcode); //登录
    Employee InfoByEmployee(Long employeeId);    //查询登录用户的信息
    Long countUser();                            //统计用户人数
    Department SelectBydeparTmentId(Long departmentId); //根据部门id查询用户所在的部门
}
