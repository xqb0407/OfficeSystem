package xyz.oa.dao.impl;

import xyz.oa.dao.UserDao;
import xyz.oa.entity.Employee;
import xyz.oa.entity.User;
import xyz.oa.utils.MybatisUtils;

/**
 * 用户Dao
 */
public class  UserDaoImpl implements UserDao {
    /**
     * 登录功能
     * @param username 用户名传入
     * @return 返回User实体
     */
    public User Login(String username){
         User user =(User)MybatisUtils.executeQuery(sqlSession -> {
             return  sqlSession.getMapper(UserDao.class).Login(username);
             
         });
         return user;
    }

    /**
     * 根据employeeId 查询登录用户的信息
     * @param employeeId
     * @return 返回Employee实体
     */
    @Override
    public Employee SelectEmployeeById(Long employeeId) {
        return (Employee) MybatisUtils.executeQuery(sqlSession -> {
                return sqlSession.getMapper(UserDao.class).SelectEmployeeById(employeeId);
        });
    }

    /**
     * 统计用户数量
     * @return 用户数量
     */
    @Override
    public Long StatisticalUser() {
        return (Long)MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(UserDao.class).StatisticalUser();
        });
    }

}
