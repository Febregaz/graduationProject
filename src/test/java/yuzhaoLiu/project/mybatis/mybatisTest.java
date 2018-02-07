package yuzhaoLiu.project.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.User;
import yuzhaoLiu.project.mybatis.mapper.UserMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

public class mybatisTest {

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("ring", new Integer(03));
            userMapper.insertUser(user);
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getAUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser("yuzhao");
            System.out.println("name: " + user.getName() + "|age: "
                    + user.getAge());
        } finally {
            sqlSession.close();
        }
    }

}
