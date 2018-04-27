package yuzhaoLiu.project.controller.mybatis.topic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.*;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.TileObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/topics")
public class topicsController extends topController {

    private Pages pageBean;
    private List<Comments> listComment;
    private List<Topics> listTopic;

    /*
    obtain the 10 newest result from t_topic
    */
    @RequestMapping("/getTheNewestTopics")
    public String getTheNewestTopics(HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNewestTopics();
        //sqlUtil.closeTheSqlSession();
        request.setAttribute("newtopicsList",topicsList);
        //logger.info("I am good and in getTheNewestTopics");
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

    @RequestMapping("/toTheDetailPage")
    public String toTheDetailPage(int topicId , int nowPage , HttpServletRequest request){
        logger.info("id+nowpage:"+topicId+" "+nowPage);
        Topics topic = getTopicsMapper.getTheTopicsMapper().getTheTopicById(topicId);
        List<Comments> commentsList = getCommentsMapper.getTheCommentsMapper().getTheCommentsByTopicId(topicId);
        nowPage = (nowPage == 0) ? 1 : nowPage;
        this.pageBean = methodForToTheDetailPage.QueryCommentsForPage(10, nowPage, commentsList);
        this.listComment = pageBean.getListComments();
        HttpSession session = request.getSession();
        session.setAttribute("topic" , topic);
        request.setAttribute("nowPage" , nowPage);
        request.setAttribute("page" , pageBean);
        request.setAttribute("listComment" , listComment);
        return "topic/theTopic";
    }

    @RequestMapping("/postedTopic")
    public String postedTopic(int typeId , String topicTitle , String tcontent , int topicIntegral ,HttpServletRequest request ) throws IOException{
        //String strTopicContent = new String(tcontent.getBytes("iso-8859-1"),"UTF-8") ;
        //String strTopicTitle = new String(topicTitle.getBytes("iso-8859-1"),"UTF-8") ;
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("userInfo");
        Topics topic = new Topics();
        int id = methodForPostedTopic.addTopic(topic , user , typeId , topicIntegral , topicTitle , tcontent);
        logger.info("topicId:"+id);
        return "redirect:toTheDetailPage?topicId="+id+"&&nowPage=1";
    }

    @RequestMapping("/getTopicsByTypeId")
    public String getTopicsByTypeId(int typeId , int nowPage , HttpServletRequest request){
        Types type = getTypeMapper.getTheTypesMapper().getTypeById(typeId);
        this.pageBean = methodForGetTopicsByTypeId.getAllForPages(10 , nowPage , typeId);
        listTopic = pageBean.getListTopics();
        request.setAttribute("type" , type);
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listTopic" , listTopic);
        return "type/theType";
    }

    @RequestMapping("/getAllHotTopics")
    public String getAllHotTopics(int nowPage , HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getAllHotTopics();
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "topic/hotTopic";
    }

    @RequestMapping("/getAllFreshTopics")
    public String getAllFreshTopics(int nowPage , HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getAllFreshTopics();
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "topic/allTopic";
    }

    @RequestMapping("/getAllNiceTopics")
    public String getAllNiceTopics(int nowPage , HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getAllNiceTopics();
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "topic/niceTopic";
    }

    @RequestMapping("/searchTopics")
    public String searchTopics(String content , int nowPage , HttpServletRequest request) throws IOException{
        //String strContent = new String(content.getBytes("iso-8859-1"),"UTF-8") ;
        logger.info(content+" "+nowPage);
        pageBean = methodForSearchResult.search(content , 5 , nowPage);
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("content" , content);
        return "topic/searchResult";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().readTopics();
        this.pageBean = methodForGetAllHotTopics.getHotForPages(12 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "admin/manageTopics";
    }

    @RequestMapping("/getTopicsByUserId")
    public String getTopicsByUserId(int userId , int nowPage , HttpServletRequest request){
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTopicsByUserId(userId);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "user/userTopics";
    }

    @RequestMapping("/goEndTopic")
    public String goEndTopic(int topicId , HttpServletRequest request){
        Topics topic = getTopicsMapper.getTheTopicsMapper().getTheTopicById(topicId);
        List<Comments> commentsList = getCommentsMapper.getTheCommentsMapper().getTheCommentsByTopicId(topicId);
        if (commentsList.size() > 1) {
            methodForToTheDetailPage.QuickSort(commentsList , 0 , commentsList.size()-1);
        }
        request.setAttribute("listComment" , commentsList);
        request.setAttribute("topic" , topic);
        HttpSession session = request.getSession();
        session.setAttribute("topicIntegral" , topic.getIntegral());
        return "topic/endTopic";
    }

    @RequestMapping("/toDoEndTopic")
    public String toDoEndTopic(String listfloor , int topicId , HttpServletRequest request){
        String[] str = listfloor.split(",");
        int[] listFloor = new int[str.length];
        for (int i = 0; i < listFloor.length; i++) {
            listFloor[i] = Integer.parseInt(str[i]);
        }
        Topics topic = getTopicsMapper.getTheTopicsMapper().getTheTopicById(topicId);
        List<Comments> list = new ArrayList<Comments>();
        List<Comments> listTemp = new ArrayList<Comments>();
        listTemp = getCommentsMapper.getTheCommentsMapper().getTheCommentsByTopicId(topicId);
        for (int i = 0; i < listTemp.size(); i++) {
            int m = listTemp.get(i).getCommentsUser().getId();
            int n = topic.getTopicsUser().getId();
            if (m != n) {
                list.add(listTemp.get(i));
            }
        }
        if (list.size() > 1) {
            methodForToTheDetailPage.QuickSort(list, 0, list.size() - 1);
        }
        methodForToTheDetailPage.endTopic(listFloor, list);
        topic.setStatus(1);
        getTopicsMapper.getTheTopicsMapper().updateTopicStatus(topic);
        getTopicsMapper.sqlCommit();
        return "redirect:toTheDetailPage?topicId="+topicId+"&&nowPage=1";//toTheDetailPage
    }

    @RequestMapping("/niceOrNot")
    public String niceOrNot(int topicId , HttpServletRequest request){
        Topics topic = getTopicsMapper.getTheTopicsMapper().getTheTopicById(topicId);
        if(topic.getNiceTopic()==0){
            topic.setNiceTopic(1);
        }
        else if(topic.getNiceTopic()==1){
            topic.setNiceTopic(0);
        }
        getTopicsMapper.getTheTopicsMapper().updateTopicNice(topic);
        getTopicsMapper.sqlCommit();
        getTopicsMapper.sqlClose();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

}
