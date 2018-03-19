package yuzhaoLiu.project.controller.mybatis.announcement.announcementUtil;

import yuzhaoLiu.project.mybatis.mapper.announcement.announcementsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

public class getAnnounceMapper {

    public static announcementsMapper annosMapper;

    public static announcementsMapper getTheAnnosMapper(){
        annosMapper = sqlUtil.getSql().getMapper(announcementsMapper.class);
        return annosMapper;
    }

    public static void sqlCommit(){
        sqlUtil.sqlCommit();
    }

}
