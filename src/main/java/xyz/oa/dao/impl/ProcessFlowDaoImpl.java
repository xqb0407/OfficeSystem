package xyz.oa.dao.impl;

import xyz.oa.dao.ProcessFlowDao;
import xyz.oa.entity.ProcessFlow;
import xyz.oa.utils.MybatisUtils;

import java.util.List;

public class ProcessFlowDaoImpl implements ProcessFlowDao {
    @Override
    public void Insert(ProcessFlow processFlow) {
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(ProcessFlowDao.class).Insert(processFlow);
            return null;
        });
    }

    @Override
    public List<ProcessFlow> selectByFromId(Long from_Id) {
        List<ProcessFlow> processFlows = (List<ProcessFlow>)MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(ProcessFlowDao.class).selectByFromId(from_Id);
        });
        return processFlows;
    }
    //更新
    @Override
    public void updateProcess(ProcessFlow processFlow) {
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(ProcessFlowDao.class).updateProcess(processFlow);
            return null;
        });
    }
}
