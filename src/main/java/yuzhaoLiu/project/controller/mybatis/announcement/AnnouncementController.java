package yuzhaoLiu.project.controller.mybatis.announcement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.mybatis.entity.announcement.Announces;
import yuzhaoLiu.project.mybatis.mapper.announcement.announcementsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController extends topController {

    @RequestMapping("/getTheAnnouncements")
    public String getTheAnnouncements(HttpServletRequest request){
        announcementsMapper announcementsMapper = sqlUtil.getSql().getMapper(announcementsMapper.class);
        List<Announces> announcesList = announcementsMapper.getTheAnnouncements();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("announcementsList",announcesList);
        logger.info("I am good and in AnnouncementController");
        return "announcement/indexAnno";
    }
}
