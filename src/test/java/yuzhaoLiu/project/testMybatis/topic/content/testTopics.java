package yuzhaoLiu.project.testMybatis.topic.content;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.List;

public class testTopics {
    private static Logger logger = Logger.getLogger(testTopics.class);
    sqlUtil sqlUtil = new sqlUtil();
    topicsMapper topicsMapper;

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    /*
    * obtain all data from t_topic
    */
    @Test
    public void readUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            topicsMapper topicsMapper = sqlSession.getMapper(topicsMapper.class);
            List<Topics> topicsList = topicsMapper.readTopics();
            for(Topics t : topicsList){
                System.out.println("topic:"+t.getTitle());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    * obtain the 10 newest data from t_topic
    */
    @Test
    public void getTheNewestTopics(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            topicsMapper topicsMapper = sqlSession.getMapper(topicsMapper.class);
            List<Topics> topicsList = topicsMapper.getTheNewestTopics();
            for(Topics t : topicsList){
                System.out.println("topic:"+t.getTitle());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getTheHotestTopics(){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheHotestTopics();
        for(Topics t : topicsList){
            System.out.println(t.getTitle());
        }
        sqlUtil.closeTheSqlSession();
    }

    /*
    * in order to test the java file toolForMyBatis useful or not
    */
    @Test
    public void testTheToolForMybatis(){
        topicsMapper = sqlUtil.getSql().getMapper(topicsMapper.class);
        List<Topics> topicsList = topicsMapper.getTheNewestTopics();
        for(Topics t : topicsList){
            System.out.println("topic:"+t.getTopicsType().getTypesCategory().getNamee());
            System.out.println("nickname:"+t.getTopicsUser().getNickname());
        }
        sqlUtil.closeTheSqlSession();
    }
}
/*SELECT
	a.*, b.`name` as bname,c.`name` as cname
FROM
	t_topic a,
	t_type b,
	t_category c
ORDER BY
	a.id DESC
LIMIT 10*/