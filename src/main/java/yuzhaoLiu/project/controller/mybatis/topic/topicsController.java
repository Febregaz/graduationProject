package yuzhaoLiu.project.controller.mybatis.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getCommentsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.TileObserver;
import java.util.List;

@Controller
@RequestMapping("/topics")
public class topicsController extends topController {

    private Pages pageBean;
    private List<Comments> listComment;

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

    /*
    obtain the 10 hotest result from t_topic
    */
    @RequestMapping("/getTheHotestTopics")
    public String getTheHotestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheHotestTopics();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("hotTopicsList",topicsList);
        logger.info("I am good and in getTheHotestTopics");
        return "topic/indexHotTopic";
    }

    /*
    obtain the 10 nicest result from t_topic
    */
    @RequestMapping("/getTheNicestTopics")
    public String getTheNicestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNicestTopics();
        //sqlUtil.closeTheSqlSession();执行关闭的话*Mapper.xml在执行sql操作的时候有时会报错
        request.setAttribute("niceTopicsList",topicsList);
        logger.info("I am good and in getTheNicestTopics");
        return "topic/indexNiceTopic";
    }

    //TODO:帖子详情页功能未实现.
    @RequestMapping("/toTheDetailPage")
    public String toTheDetailPage(int topicId , int nowPage , HttpServletRequest request){
        logger.info("id+nowpage:"+topicId+" "+nowPage);
        Topics topic = getTopicsMapper.getTheTopicsMapper().getTheTopicById(topicId);
        List<Comments> commentsList = getCommentsMapper.getTheTopicsMapper().getTheCommentsByTopicId(topicId);
        nowPage = (nowPage == 0) ? 1 : nowPage;
        this.pageBean = this.QueryCommentsForPage(10, nowPage, commentsList);
        this.listComment = pageBean.getListComments();
        topic.setContent(ignoreTopicHtml(topic.getContent()));;/*忽略<p></p>*/
        listComment = ignoreListCommentHtml(listComment);/*忽略<p></p>*/
        request.setAttribute("topic" , topic);
        request.setAttribute("nowPage" , nowPage);
        request.setAttribute("page" , pageBean);
        request.setAttribute("listComment" , listComment);
        return "topic/theTopic";
    }

    public Pages QueryCommentsForPage(int pageSize, int nowPage,
                                      List<Comments> listComment) {
        int allRecords = listComment.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        if (allRecords >= length + currentoffset) {
            toIndex = currentoffset + length;
        } else {
            toIndex = allRecords;
        }
        List<Comments> subListComment = listComment.subList(currentoffset,
                toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListComments(subListComment);
        pagebean.init();
        return pagebean;
    }

    public String ignoreTopicHtml(String msg){
        msg = msg.replace("<p>","");
        msg = msg.replace("</p>","");
        return msg;
    }

    public List<Comments> ignoreListCommentHtml(List<Comments> listComment){
        for(Comments c : listComment){
            String msg = c.getContent();
            msg = msg.replace("<p>","");
            msg = msg.replace("</p>","");
            c.setContent(msg);
        }
        return listComment;
    }

}
