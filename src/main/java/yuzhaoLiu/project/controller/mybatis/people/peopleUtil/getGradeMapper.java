package yuzhaoLiu.project.controller.mybatis.people.peopleUtil;

import yuzhaoLiu.project.mybatis.mapper.people.gradesMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getGradeMapper {

        public static gradesMapper gradesMapper;

        public static gradesMapper getTheGradesMapper(){
            gradesMapper = sqlUtil.getSql().getMapper(gradesMapper.class);
            return gradesMapper;
        }

        public static void sqlCommit(){
            sqlUtil.sqlCommit();
        }

}
