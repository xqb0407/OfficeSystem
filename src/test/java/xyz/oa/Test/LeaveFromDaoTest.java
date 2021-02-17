package xyz.oa.Test;

import org.junit.Test;
import xyz.oa.dao.LeaveFromDao;
import xyz.oa.dao.impl.LeaveFromDaoImpl;
import xyz.oa.entity.LeaveFrom;
import xyz.oa.utils.MybatisUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LeaveFromDaoTest {
    private LeaveFromDao leaveFromDao = new LeaveFromDaoImpl();
    @Test
    public void insert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            LeaveFrom leaveFrom = new LeaveFrom();
            leaveFrom.setCreateTime(new Date());
            leaveFrom.setEmployeeId(4L);
            leaveFrom.setEndTime(new Date());
            leaveFrom.setStartTime(new Date());
            leaveFrom.setCreateTime(new Date());
            leaveFrom.setFormType(1);
            leaveFrom.setReason("回家");
            leaveFrom.setState("processing");
            sqlSession.getMapper(LeaveFromDao.class).Insert(leaveFrom);
            return null;
        });
    }
    @Test
    public void SelectByParams(){
        List<Map> process = leaveFromDao.SelectByParems("process", 2L);
        System.out.println(process.toString());
    }
}