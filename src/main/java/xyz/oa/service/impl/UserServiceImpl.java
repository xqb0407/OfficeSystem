package xyz.oa.service.impl;

import cn.hutool.crypto.SecureUtil;
import xyz.oa.dao.DepartmentDao;
import xyz.oa.dao.UserDao;
import xyz.oa.dao.impl.DepartmentDaoImpl;
import xyz.oa.dao.impl.UserDaoImpl;
import xyz.oa.entity.Department;
import xyz.oa.entity.Employee;
import xyz.oa.entity.User;
import xyz.oa.service.Exception.BussinessException;
import xyz.oa.service.UserService;

/**
 * 用户service
 */
public class UserServiceImpl  implements UserService {
    private UserDao userDao =new UserDaoImpl();
    private DepartmentDao departmentDao =new DepartmentDaoImpl();
    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    @Override
    public User Login(String username, String password,String vercode,String vcode) {
        String md5Pass=SecureUtil.md5(password);
        //验证码是是否正确
        if (!vcode.equals(vercode)){
            throw  new BussinessException("003","验证码错误");
        }
        User user = userDao.Login(username);
        if (user == null){
            //用户名不存在异常
            throw new BussinessException("001","用户名不存在");
        }
        if (!md5Pass.equals(user.getPassword())){
            throw  new BussinessException("002","密码错误");
        }
        return user;
    }

    /**
     * 查询登录用户的信息
     * @param employeeId
     * @return
     */
    @Override
    public Employee InfoByEmployee(Long employeeId) {
        return userDao.SelectEmployeeById(employeeId);
    }

    @Override
    public Long countUser() {
        return userDao.StatisticalUser();
    }

    /**
     * 根据用户所在的部门查询
     * @param departmentId
     * @return
     */
    @Override
    public Department SelectBydeparTmentId(Long departmentId) {
        return departmentDao.SelectBydeparTmentId(departmentId);
    }
}
