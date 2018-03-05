package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getTopicsMapper {

    public static topicsMapper topicsMapper;

    public static topicsMapper getTheTopicsMapper(){
        topicsMapper = sqlUtil.getSql().getMapper(topicsMapper.class);
        return topicsMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

}
