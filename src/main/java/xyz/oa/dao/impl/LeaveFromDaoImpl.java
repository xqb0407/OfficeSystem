package xyz.oa.dao.impl;

import xyz.oa.dao.LeaveFromDao;
import xyz.oa.entity.LeaveFrom;
import xyz.oa.utils.MybatisUtils;

import java.util.List;
import java.util.Map;

/**
 * 请假持久层
 */
public class LeaveFromDaoImpl implements LeaveFromDao {

    /**
     * 插入请假
     * @param leaveFrom
     */
    @Override
    public void Insert(LeaveFrom leaveFrom) {
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(LeaveFromDao.class).Insert(leaveFrom);
            return null;
        });
    }

    /**
     * 查询审批列表
     * @param apfState
     * @param apfOperatopId
     * @return
     */
    @Override
    public List<Map> SelectByParems(String apfState, Long apfOperatopId) {
        return (List<Map>) MybatisUtils.executeQuery(sqlSession -> {
            return sqlSession.getMapper(LeaveFromDao.class).SelectByParems(apfState, apfOperatopId);
        });
    }

    // @Override
    // public void ApproveFrom(Long operatopId, Long fromId, String result, String reason) {
    //
    // }

    //更新请假
    @Override
    public void UpdateFromLeave(LeaveFrom leaveFrom) {
        MybatisUtils.executeUpdate(sqlSession -> {
           sqlSession.getMapper(LeaveFromDao.class).UpdateFromLeave(leaveFrom);
           return null;
        });
    }
    //查询请假表根据formId
    @Override
    public LeaveFrom SelectLeaveFromByFormId(Long fromId) {
        return (LeaveFrom)MybatisUtils.executeUpdate(sqlSession -> {
            return sqlSession.getMapper(LeaveFromDao.class).SelectLeaveFromByFormId(fromId);
        });
    }

}
