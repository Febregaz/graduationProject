package yuzhaoLiu.project.controller.mybatis.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/topics")
public class topicsController extends topController {

    /*
    obtain the 10 newest result from t_topic
    */
    @RequestMapping("/getTheNewestTopics")
    public String getTheNewestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNewestTopics();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("newtopicsList",topicsList);
        logger.info("I am good and in getTheNewestTopics");
        return "topic/indexFreshTopic";
    }

    @RequestMapping("/getTheHotestTopics")
    public String getTheHotestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheHotestTopics();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("hotTopicsList",topicsList);
        logger.info("I am good and in getTheHotestTopics");
        return "topic/indexHotTopic";
    }

    @RequestMapping("/getTheNicestTopics")
    public String getTheNicestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNicestTopics();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("niceTopicsList",topicsList);
        logger.info("I am good and in getTheNicestTopics");
        return "topic/indexNiceTopic";
    }

    //TODO:帖子详情页功能未实现.
    @RequestMapping("/toTheDetailPage")
    public String toTheDetailPage(int id){

        return "";
    }

}
