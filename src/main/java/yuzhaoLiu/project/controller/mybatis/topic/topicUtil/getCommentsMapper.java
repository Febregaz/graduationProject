package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getCommentsMapper {
    public static commentsMapper commentsMapper;

    public static commentsMapper getTheCommentsMapper(){
        commentsMapper = sqlUtil.getSql().getMapper(commentsMapper.class);
        return commentsMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

    public static void sqlClose(){
        sqlUtil.closeTheSqlSession();
    }
}
