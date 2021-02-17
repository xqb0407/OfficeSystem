package xyz.oa.dao.impl;

import xyz.oa.dao.NoticeDao;
import xyz.oa.entity.Notice;
import xyz.oa.utils.MybatisUtils;

public class NoticeDaoImpl implements NoticeDao {


    @Override
    public void Insert(Notice notice) {
        MybatisUtils.executeUpdate(sqlSession -> {
            sqlSession.getMapper(NoticeDao.class).Insert(notice);
            return null;
        });
    }
}
