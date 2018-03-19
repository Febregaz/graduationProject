package yuzhaoLiu.project.controller.mybatis.announcement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.announcement.announcementUtil.getAnnounceMapper;
import yuzhaoLiu.project.controller.mybatis.announcement.announcementUtil.methodForManageAll;
import yuzhaoLiu.project.mybatis.entity.announcement.Announces;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.mapper.announcement.announcementsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController extends topController {

    Pages pageBean ;

    @RequestMapping("/getTheAnnouncements")
    public String getTheAnnouncements(HttpServletRequest request){
        announcementsMapper announcementsMapper = sqlUtil.getSql().getMapper(announcementsMapper.class);
        List<Announces> announcesList = announcementsMapper.getTheAnnouncements();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("announcementsList",announcesList);
        logger.info("I am good and in AnnouncementController");
        return "announcement/indexAnno";
    }

    @RequestMapping("/getAllAnnouncements")
    public String getAllAnnos(int annoId , HttpServletRequest request){
        announcementsMapper announcementsMapper = sqlUtil.getSql().getMapper(announcementsMapper.class);
        List<Announces> annosList = announcementsMapper.getTheAnnouncements();
        request.setAttribute("listAnno",annosList);
        request.setAttribute("annoSize" , annosList.size());
        request.setAttribute("annoId" , annoId);
        return "announcement/announce";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        List<Announces> announcesList = getAnnounceMapper.getTheAnnosMapper().getTheAnnouncements();
        this.pageBean = methodForManageAll.ManageAnnosForPage(10 , nowPage ,announcesList );
        request.setAttribute("listAnno" , announcesList);
        request.setAttribute("pageBean" , pageBean);
        return "admin/manageAnnos";
    }

    @RequestMapping("/annoAdd")
    public String annoAdd(String announceTitle , String announceAnnouncement){
        Announces announce = new Announces();
        Date date = new Date();
        announce.setTitle(announceTitle);announce.setAnnouncement(announceAnnouncement);announce.setThetime(date);
        getAnnounceMapper.getTheAnnosMapper().addAnno(announce);
        getAnnounceMapper.sqlCommit();
        return "redirect:manageAll?nowPage=1";
    }
}
