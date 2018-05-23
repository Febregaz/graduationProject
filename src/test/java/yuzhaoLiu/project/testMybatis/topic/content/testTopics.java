package yuzhaoLiu.project.testMybatis.topic.content;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.methodForToTheDetailPage;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.Date;
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
    public void readTopics(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            topicsMapper topicsMapper = sqlSession.getMapper(topicsMapper.class);
            List<Topics> topicsList = topicsMapper.readTopics();
            for(Topics t : topicsList){
                t.setContent(methodForToTheDetailPage.ignoreTopicHtml(t.getContent()));
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
                System.out.println("topic:"+t.getTopicsType().getName());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getTheHotestTopics(){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNewestTopics();
        for(Topics t : topicsList){
            System.out.println(t.getTitle());
        }
        //sqlUtil.closeTheSqlSession();
    }

    /*
    * in order to test the java file toolForMyBatis useful or not
    */
    @Test
    public void testTheToolForMybatis(){
        topicsMapper = sqlUtil.getSql().getMapper(topicsMapper.class);
        List<Topics> topicsList = topicsMapper.searchTopics("国");
        System.out.println(topicsList.size());
        for(Topics t : topicsList){
            System.out.println("topic:"+t.getTitle());
        }
        sqlUtil.closeTheSqlSession();
    }

    @Test
    public void getTheTopicById(){
        /*SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            topicsMapper topicsMapper = sqlSession.getMapper(topicsMapper.class);
            Topics topic = topicsMapper.getTheTopicById(8);
            topic.setCountComment(35);
            topicsMapper.updateTopicComment(topic.getId() , topic.getCountComment());
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }*/
        Topics topics = getTopicsMapper.getTheTopicsMapper().getTheTopicById(1);
        logger.info(topics.getTopicsType().getTypesCategory().getNamee());
    }

    @Test
    public void addTopic(){
        Users user = getPeopleMapper.getTheUsersMapper().userLogin("Aragami");
        Types type = getTypeMapper.getTheTypesMapper().getTypeById(1);
        Topics topic = new Topics();
        topic.setTitle("习近平");topic.setContent("连任犯得上反对");topic.setCountComment(0);topic.setNiceTopic(0);
        topic.setTopicTime(new Date());topic.setIntegral(20);topic.setStatus(0);topic.setTopicsUser(user);
        topic.setTopicsType(type);
        getTopicsMapper.getTheTopicsMapper().addTopic(topic);
        getTopicsMapper.sqlCommit();
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