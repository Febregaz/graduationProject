package yuzhaoLiu.project.controller.mybatis.category.categoryUtil;

import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getCategoryMapper {
    public static categorysMapper categorysMapper;

    public static categorysMapper getTheCategorysMapper(){
        categorysMapper = sqlUtil.getSql().getMapper(categorysMapper.class);
        return categorysMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }
}
