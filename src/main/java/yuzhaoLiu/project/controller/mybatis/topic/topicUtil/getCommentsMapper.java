package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getCommentsMapper {
    public static commentsMapper commentsMapper;

    public static commentsMapper getTheTopicsMapper(){
        commentsMapper = sqlUtil.getSql().getMapper(commentsMapper.class);
        return commentsMapper;
    }
}
