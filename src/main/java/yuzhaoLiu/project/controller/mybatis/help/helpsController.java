package yuzhaoLiu.project.controller.mybatis.help;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.mybatis.entity.help.Helps;
import yuzhaoLiu.project.mybatis.mapper.help.helpsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/helps")
public class helpsController extends topController {

    @RequestMapping("/getTheHelps")
    public String getTheAnnouncements(HttpServletRequest request){
        helpsMapper helpsMapper = sqlUtil.getSql().getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("helpsList",helpsList);
        logger.info("I am good and in helpsController");
        return "help/indexHelp";
    }

    @RequestMapping("/getAllHelps")
    public String getAllHelps(int helpId , HttpServletRequest request){
        helpsMapper helpsMapper = sqlUtil.getSql().getMapper(helpsMapper.class);
        List<Helps> helpsList = helpsMapper.getTheHelps();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("listHelp",helpsList);
        request.setAttribute("helpSize" , helpsList.size());
        request.setAttribute("helpId" , helpId);
        logger.info("I am good and in helpsController");
        return "help/help";
    }

}
