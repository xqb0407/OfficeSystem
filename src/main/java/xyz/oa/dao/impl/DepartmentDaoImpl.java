package xyz.oa.dao.impl;

import xyz.oa.dao.DepartmentDao;
import xyz.oa.entity.Department;
import xyz.oa.utils.MybatisUtils;

/**
 * 部门持久层
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public Department SelectBydeparTmentId(Long departmentId) {
        return (Department)MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(DepartmentDao.class).SelectBydeparTmentId(departmentId);

        });
    }
}
