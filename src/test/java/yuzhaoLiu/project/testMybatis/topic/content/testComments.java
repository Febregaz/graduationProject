package yuzhaoLiu.project.testMybatis.topic.content;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getCommentsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.Date;
import java.util.List;

public class testComments {
    private static Logger logger = Logger.getLogger(testTopics.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void readUsers(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            commentsMapper usersMapper = sqlSession.getMapper(commentsMapper.class);
            List<Comments> commentsList = usersMapper.readComments();
            for(Comments c : commentsList){
                System.out.println("topic:"+c.getFloor());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getCommentsByTopicId(){
        commentsMapper commentsMapper = sqlUtil.getSql().getMapper(commentsMapper.class);
        List<Comments> commentsList = commentsMapper.getTheCommentsByTopicId(8);
        for(Comments c : commentsList){
            logger.info("comments:"+c.getCommentTime());
        }
    }

    @Test
    public void addTheComment(){
        Date date = new Date();
        Comments comments = new Comments();
        comments.setContent("我爱你");
        comments.setFloor(2);
        comments.setCommentTime(date);
        comments.setCommentsUser(getPeopleMapper.getTheUsersMapper().userLogin("Aragami"));
        comments.setCommentsTopic(getTopicsMapper.getTheTopicsMapper().getTheTopicById(8));
        getCommentsMapper.getTheCommentsMapper().addTheComment(comments);
        getCommentsMapper.sqlCommit();
    }
}
