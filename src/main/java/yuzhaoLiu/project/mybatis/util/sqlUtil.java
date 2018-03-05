package yuzhaoLiu.project.mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
/**
 *provide the sqlsession for mapper
 */
public class sqlUtil {

    static SqlSession sqlSession;

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    public static SqlSession getSql(){
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    public static void sqlCommit(){
        sqlSession.commit();
    }

    public static void closeTheSqlSession(){
        sqlSession.close();
    }

}
