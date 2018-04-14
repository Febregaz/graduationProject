package yuzhaoLiu.project.controller.mybatis.people.peopleUtil;

import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getPeopleMapper {
    public static usersMapper usersMapper;

    public static usersMapper getTheUsersMapper(){
        usersMapper = sqlUtil.getSql().getMapper(usersMapper.class);
        return usersMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

    public static void sqlClose(){
        sqlUtil.closeTheSqlSession();
    }
}
