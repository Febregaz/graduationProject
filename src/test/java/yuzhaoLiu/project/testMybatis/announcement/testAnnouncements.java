package yuzhaoLiu.project.testMybatis.announcement;

import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.announcement.Announces;
import yuzhaoLiu.project.mybatis.mapper.announcement.announcementsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.List;

public class testAnnouncements {

    @Test
    public void getTheAnnouncements(){
        announcementsMapper announcementsMapper = sqlUtil.getSql().getMapper(announcementsMapper.class);
        List<Announces> announcementsList = announcementsMapper.getTheAnnouncements();
        for(Announces a : announcementsList){
            System.out.println("announce:"+a.getTitle());
        }
        sqlUtil.closeTheSqlSession();
    }

}
