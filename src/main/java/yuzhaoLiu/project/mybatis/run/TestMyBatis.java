package yuzhaoLiu.project.mybatis.run;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import yuzhaoLiu.project.mybatis.entity.User;
import yuzhaoLiu.project.mybatis.mapper.UserMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

public class TestMyBatis {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    public static void main(String[] args) {
        testAdd();
        //getUser();
    }

    public static void testAdd() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("aragami", new Integer(02));
            userMapper.insertUser(user);
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
        } finally {
            sqlSession.close();
        }
    }

    public static void getUser() {
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
