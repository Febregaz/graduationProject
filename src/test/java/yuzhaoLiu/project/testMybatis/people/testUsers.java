package yuzhaoLiu.project.testMybatis.people;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;
import yuzhaoLiu.project.mybatis.util.sqlUtil;
import yuzhaoLiu.project.testMybatis.testMybatis;

import java.util.List;

public class testUsers {

    private static Logger logger = Logger.getLogger(testUsers.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void readUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            usersMapper usersMapper = sqlSession.getMapper(usersMapper.class);
            List<Users> userList = usersMapper.readUsers();
            for(Users u : userList){
                System.out.println("name:"+u.getUsername());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    * user login test for checking if the user exist
    * */
    @Test
    public void userLogin(){
        usersMapper usersMapper = sqlUtil.getSql().getMapper(usersMapper.class);
        Users users = usersMapper.userLogin("Aragami");
        System.out.println(users.getStatus());
    }
}
