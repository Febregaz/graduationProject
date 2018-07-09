package yuzhaoLiu.project.controller.mybatis.help;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.help.helpUtil.methodForManageAll;
import yuzhaoLiu.project.mybatis.entity.help.Helps;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.mapper.help.helpsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/helps")
public class helpsController extends topController {

    Pages pageBean;

    @RequestMapping("/getTheHelps")
    public String getTheAnnouncements(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        helpsMapper helpsMapper = sqlSession.getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("helpsList",helpsList);
        logger.info("I am good and in helpsController");
        sqlUtil.sqlClose(sqlSession);
        return "help/indexHelp";
    }

    @RequestMapping("/getAllHelps")
    public String getAllHelps(int helpId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        helpsMapper helpsMapper = sqlSession.getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("listHelp",helpsList);
        request.setAttribute("helpSize" , helpsList.size());
        request.setAttribute("helpId" , helpId);
        //logger.info("I am good and in helpsController");
        sqlUtil.sqlClose(sqlSession);
        return "help/help";
    }

    @RequestMapping("/manageAll")
    public String managaAll(HttpServletRequest request , int nowPage){
        SqlSession sqlSession = sqlUtil.getSql();
        helpsMapper helpsMapper = sqlSession.getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        this.pageBean = methodForManageAll.ManageHelpsForPage(10 , nowPage , helpsList);
        request.setAttribute("listHelp" , pageBean.getListHelp());
        request.setAttribute("pageBean" , pageBean);
        sqlUtil.sqlClose(sqlSession);
        return "admin/manageHelps";
    }

    @RequestMapping("/addHelp")
    public String addHelp(String thelpTitle ,String thelpContent){
        Helps help = new Helps();
        help.setTitle(thelpTitle);help.setContent(thelpContent);help.setNewtime(new Date());
        SqlSession sqlSession = sqlUtil.getSql();
        helpsMapper helpsMapper = sqlSession.getMapper(helpsMapper.class);
        helpsMapper.addHelp(help);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        return "redirect:manageAll?nowPage=1";
    }
}
