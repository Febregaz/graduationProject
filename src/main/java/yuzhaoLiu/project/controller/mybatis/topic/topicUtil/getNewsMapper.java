package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.newsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getNewsMapper {

    public static newsMapper newsMapper;

    public static newsMapper getTheNewsMapper(){
        newsMapper = sqlUtil.getSql().getMapper(newsMapper.class);
        return newsMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

    public static void sqlClose(){
        sqlUtil.closeTheSqlSession();
    }

}
