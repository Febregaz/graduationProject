package yuzhaoLiu.project.controller.mybatis.category.categoryUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getTypeMapper {
    public static typesMapper typesMapper;

    public static typesMapper getTheTypesMapper(){
        typesMapper = sqlUtil.getSql().getMapper(typesMapper.class);
        return typesMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }
}
