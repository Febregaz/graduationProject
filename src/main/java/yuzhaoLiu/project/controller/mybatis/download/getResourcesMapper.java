package yuzhaoLiu.project.controller.mybatis.download;

import yuzhaoLiu.project.mybatis.mapper.download.resourcesMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getResourcesMapper {

    public static resourcesMapper resourcesMapper;

    public static resourcesMapper getTheResourcesMapper(){
        resourcesMapper = sqlUtil.getSql().getMapper(resourcesMapper.class);
        return resourcesMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

    public static void sqlClose(){
        sqlUtil.closeTheSqlSession();
    }

}
