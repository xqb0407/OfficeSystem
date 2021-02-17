package xyz.oa.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import xyz.oa.utils.MybatisUtils;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class TestConnlication {
    @Test
    public void Test() throws IOException {
        Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        System.out.println(connection);
    }

    @Test
    public void TestSimpl(){
        String str =(String) MybatisUtils.executeQuery(sqlSession -> {
            String out = (String) sqlSession.selectOne("test.sample");
            return out;
        });
        System.out.println(str);
    }
}
