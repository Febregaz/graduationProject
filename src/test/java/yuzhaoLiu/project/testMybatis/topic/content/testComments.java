package yuzhaoLiu.project.testMybatis.topic.content;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

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
}
