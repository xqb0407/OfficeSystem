package xyz.oa.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

/**
 * Mybatis工具类，创建全局唯一的SqlSessionFactory工厂对象
 *
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory =null;
    static{
        Reader reader =null;
        try {
                reader = Resources.getResourceAsReader("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch (IOException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询sql
     * @param func 要执行查询语句的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Object obj = func.apply(sqlSession);
            return obj;
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 执行写操作，删除更新插入操作SQL
     * @param func 要执行的代码块
     * @return 写操作执行后的返回结果
     */
    public static Object executeUpdate(Function<SqlSession,Object> func){
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        try {
            Object obj = func.apply(sqlSession);
            sqlSession.commit();
            return obj;
        }catch (RuntimeException e){
            sqlSession.rollback();
            throw e;
        }finally {
            sqlSession.close();
        }
    }
}
