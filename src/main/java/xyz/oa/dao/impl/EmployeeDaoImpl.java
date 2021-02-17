package xyz.oa.dao.impl;

import xyz.oa.dao.EmployeeDao;
import xyz.oa.entity.Employee;
import xyz.oa.utils.MybatisUtils;

public class EmployeeDaoImpl implements EmployeeDao {
    /**
     * 根据Id查询用户信息
     */
    @Override
    public Employee SelectEmpById(Long employeeId) {
        return (Employee)MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(EmployeeDao.class).SelectEmpById(employeeId);
        });
    }

    /**
     * 查询所在部门的经理
     * @param employee
     * @return
     */
    @Override
    public Employee SelectLeader(Employee employee) {
        return (Employee) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(EmployeeDao.class).SelectLeader(employee);
        });
    }

}
