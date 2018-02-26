package yuzhaoLiu.project.testMybatis.people;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.people.Grades;
import yuzhaoLiu.project.mybatis.mapper.people.gradesMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

import java.util.List;

public class testGrades {
    private static Logger logger = Logger.getLogger(testGrades.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void readUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            gradesMapper usersMapper = sqlSession.getMapper(gradesMapper.class);
            List<Grades> gradesList = usersMapper.readGrades();
            for(Grades g : gradesList){
                System.out.println("honor:"+g.getHonor());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }
}
