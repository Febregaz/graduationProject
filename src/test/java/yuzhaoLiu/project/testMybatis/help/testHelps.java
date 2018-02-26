package yuzhaoLiu.project.testMybatis.help;

import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.help.Helps;
import yuzhaoLiu.project.mybatis.mapper.help.helpsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.List;

public class testHelps {

    @Test
    public void getTheAnnouncements(){
        helpsMapper helpsMapper = sqlUtil.getSql().getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        for(Helps h : helpsList){
            System.out.println("announce:"+h.getTitle());
        }
        sqlUtil.closeTheSqlSession();
    }
}
