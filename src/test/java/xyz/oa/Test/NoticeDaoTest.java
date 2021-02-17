package xyz.oa.Test;

import org.junit.Test;
import xyz.oa.dao.NoticeDao;
import xyz.oa.entity.Notice;
import xyz.oa.utils.MybatisUtils;

import java.util.Date;

import static org.junit.Assert.*;

public class NoticeDaoTest {

    @Test
    public void insert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            Notice notice =new Notice();
            notice.setContent("测试 请假消息，请审批");
            notice.setCreateTime(new Date());
            notice.setReceiverId(2L);
            sqlSession.getMapper(NoticeDao.class).Insert(notice);
            return null;
        });
    }
}