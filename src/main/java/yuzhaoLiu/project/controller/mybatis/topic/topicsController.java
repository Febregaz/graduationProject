package yuzhaoLiu.project.controller.mybatis.topic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.people.sendMailUtil.CodeUtil;
import yuzhaoLiu.project.controller.mybatis.people.sendMailUtil.MailUtil;
import yuzhaoLiu.project.controller.mybatis.people.sendMailUtil.sendAllPeople;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.*;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
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
    @ResponseBody
    public List<Topics> getTheNewestTopics(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTheNewestTopics();
        //List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNewestTopics();
        //sqlUtil.closeTheSqlSession();
        //getTopicsMapper.sqlClose();
        request.setAttribute("newtopicsList",topicsList);
        //logger.info("I am good and in getTheNewestTopics");
        sqlUtil.sqlClose(sqlSession);
        return topicsList;
        //return /*"redirect:"+basePath+"/NC-JSP/home/index.jsp"*/"home/index";
    }

    @RequestMapping("/oldGetTheNewestTopics")
    public String oldGetTheNewestTopics(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTheNewestTopics();
        //List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTheNewestTopics();
        //sqlUtil.closeTheSqlSession();
        //getTopicsMapper.sqlClose();
        request.setAttribute("newtopicsList",topicsList);
        //logger.info("I am good and in getTheNewestTopics");
        sqlUtil.sqlClose(sqlSession);
        return "topic/indexFreshTopic";
        //return /*"redirect:"+basePath+"/NC-JSP/home/index.jsp"*/"home/index";
    }

    /*
    obtain the 10 hotest result from t_topic
    */
    @RequestMapping("/getTheHotestTopics")
    public String getTheHotestTopics(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTheHotestTopics();
        //sqlUtil.closeTheSqlSession();
        //getTopicsMapper.sqlClose();
        request.setAttribute("hotTopicsList",topicsList);
        //logger.info("I am good and in getTheHotestTopics");
        sqlUtil.sqlClose(sqlSession);
        return "topic/indexHotTopic";
    }

    /*
    obtain the 10 nicest result from t_topic
    */
    @RequestMapping("/getTheNicestTopics")
    @ResponseBody
    public List<Topics> getTheNicestTopics(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTheNicestTopics();
        //sqlUtil.closeTheSqlSession();执行关闭的话*Mapper.xml在执行sql操作的时候有时会报错
        //getTopicsMapper.sqlClose();
        request.setAttribute("niceTopicsList",topicsList);
        //logger.info("I am good and in getTheNicestTopics");
        sqlUtil.sqlClose(sqlSession);
        return topicsList;
    }

    @RequestMapping("/oldGetTheNicestTopics")
    public String oldGetTheNicestTopics(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTheNicestTopics();
        //sqlUtil.closeTheSqlSession();执行关闭的话*Mapper.xml在执行sql操作的时候有时会报错
        //getTopicsMapper.sqlClose();
        request.setAttribute("niceTopicsList",topicsList);
        //logger.info("I am good and in getTheNicestTopics");
        sqlUtil.sqlClose(sqlSession);
        return "topic/indexNiceTopic";
    }

    @RequestMapping("/toTheDetailPage")
    public String toTheDetailPage(int topicId , int nowPage , HttpServletRequest request){
        logger.info("id+nowpage:"+topicId+" "+nowPage);
        SqlSession tSqlSession = sqlUtil.getSql();
        Topics topic = tSqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(tSqlSession);
        SqlSession cSqlSession = sqlUtil.getSql();
        List<Comments> commentsList = cSqlSession.getMapper(commentsMapper.class).getTheCommentsByTopicId(topicId);
        sqlUtil.sqlClose(cSqlSession);
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

    @RequestMapping("/newToTheDetailPage")
    public String newToTheDetailPage(int topicId , HttpServletRequest request){
        SqlSession tSqlSession = sqlUtil.getSql();
        Topics topic = tSqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(tSqlSession);
        HttpSession session = request.getSession();
        session.setAttribute("topic" , topic);
        SqlSession cSqlSession = sqlUtil.getSql();
        List<Comments> commentsList = cSqlSession.getMapper(commentsMapper.class).getTheCommentsByTopicId(topicId);
        sqlUtil.sqlClose(cSqlSession);
        request.setAttribute("listComment" , commentsList);
        return "gp2.0Frontend/single";
    }

    @RequestMapping("/postedTopic")
    public String postedTopic(int typeId , String topicTitle , String tcontent , int topicIntegral , int ifOrNot , int priOrPub , HttpServletRequest request ) throws IOException{
        //String strTopicContent = new String(tcontent.getBytes("iso-8859-1"),"UTF-8") ;
        //String strTopicTitle = new String(topicTitle.getBytes("iso-8859-1"),"UTF-8") ;
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("userInfo");
        Topics topic = new Topics();
        int id = methodForPostedTopic.addTopic(topic , user , typeId , topicIntegral , topicTitle , tcontent , priOrPub);
        System.out.println("sendAllOrNot:"+ifOrNot);
        if(ifOrNot==1){
            SqlSession uSqlSession = sqlUtil.getSql();
            List<Users> usersList = uSqlSession.getMapper(usersMapper.class).readUsers();
            sqlUtil.sqlClose(uSqlSession);
            for(Users u : usersList){
                String code= CodeUtil.generateUniqueCode();
                new Thread(new sendAllPeople(u.getEmail() , code ,id , topicTitle , priOrPub , user.getId())).start();
            }
        }
        if(priOrPub>0){
            return "redirect:/617/12139"+id+"_"+priOrPub+".617museum";
        }
        return "redirect:/617/newDetail"+id+".617museum";
        /*http://www.617museum.top/topics/toTheDetailPage?topicId=26&&nowPage=1*/
    }

    @RequestMapping("/getTopicsByTypeId")
    public String getTopicsByTypeId(int typeId , int nowPage , HttpServletRequest request){
        SqlSession typeSession = sqlUtil.getSql();
        Types type = typeSession.getMapper(typesMapper.class).getTypeById(typeId);
        sqlUtil.sqlClose(typeSession);
        this.pageBean = methodForGetTopicsByTypeId.getAllForPages(10 , nowPage , typeId);
        listTopic = pageBean.getListTopics();
        request.setAttribute("type" , type);
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listTopic" , listTopic);
        return "type/theType";
    }

    @RequestMapping("/newGetTopicsByTypeId")
    public String newGetTopicsByTypeId(int typeId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTopicsByTypeId(typeId);
        sqlUtil.sqlClose(sqlSession);
        request.setAttribute("listTopic" , topicsList);
        return "gp2.0Frontend/typeDetail";
    }

    @RequestMapping("/getAllHotTopics")
    public String getAllHotTopics(int nowPage , HttpServletRequest request){
        SqlSession tSqlSession = sqlUtil.getSql();
        List<Topics> topicsList = tSqlSession.getMapper(topicsMapper.class).getAllHotTopics();
        sqlUtil.sqlClose(tSqlSession);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "topic/hotTopic";
    }

    @RequestMapping("/getAllFreshTopics")
    public String getAllFreshTopics(int nowPage , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getAllFreshTopics();
        sqlUtil.sqlClose(sqlSession);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "topic/allTopic";
    }

    @RequestMapping("/getAllNiceTopics")
    public String getAllNiceTopics(int nowPage , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getAllNiceTopics();
        sqlUtil.sqlClose(sqlSession);
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

    @RequestMapping("/newSearchTopics")
    public String newSearchTopics(String content , HttpServletRequest request) throws IOException{
        //String strContent = new String(content.getBytes("iso-8859-1"),"UTF-8") ;
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).searchTopics(content);
        request.setAttribute("listTopic" , topicsList);
        return "gp2.0Frontend/search";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).readTopics();
        sqlUtil.sqlClose(sqlSession);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(12 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "admin/manageTopics";
    }

    @RequestMapping("/getTopicsByUserId")
    public String getTopicsByUserId(int userId , int nowPage , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getTopicsByUserId(userId);
        sqlUtil.sqlClose(sqlSession);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage , topicsList);
        request.setAttribute("listTopic" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "user/userTopics";
    }

    @RequestMapping("/goEndTopic")
    public String goEndTopic(int topicId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(sqlSession);
        SqlSession sqlSession1 = sqlUtil.getSql();
        List<Comments> commentsList = sqlSession1.getMapper(commentsMapper.class).getTheCommentsByTopicId(topicId);
        sqlUtil.sqlClose(sqlSession1);
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
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(sqlSession);
        List<Comments> list = new ArrayList<Comments>();
        List<Comments> listTemp = new ArrayList<Comments>();
        SqlSession sqlSession1 = sqlUtil.getSql();
        listTemp = sqlSession1.getMapper(commentsMapper.class).getTheCommentsByTopicId(topicId);
        sqlUtil.sqlClose(sqlSession1);
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
        SqlSession sqlSession2 = sqlUtil.getSql();
        sqlSession2.getMapper(topicsMapper.class).updateTopicStatus(topic);
        sqlUtil.commit(sqlSession2);
        sqlUtil.sqlClose(sqlSession2);
        return "redirect:/617/Ahri"+topicId+"_1.617museum";//toTheDetailPage
    }

    @RequestMapping("/niceOrNot")
    public String niceOrNot(int topicId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(sqlSession);
        if(topic.getNiceTopic()==0){
            topic.setNiceTopic(1);
        }
        else if(topic.getNiceTopic()==1){
            topic.setNiceTopic(0);
        }
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(topicsMapper.class).updateTopicNice(topic);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

    @RequestMapping("/deleteTopicOrNot")
    public String deleteTopicOrNot(int topicId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(sqlSession);
        Types type = topic.getTopicsType();
        int oldTypeTopicCount = type.getCountTopics();
        /*int oldTypeCommentCount = type.getCountComments();//获取type的最初总评论数
        int oldTypeTopicCount = type.getCountTopics();//获取type的最初总帖子数
        Categorys category = type.getTypesCategory();
        int oldCateCommentCount = category.getCountComments();//获取category的最初总评论数
        int oldCateTopicCount = category.getCountTopics();//获取category的最初总帖子数*/
        if(topic.getStatus()==2){
            topic.setStatus(0);
            /*type.setCountComments(oldTypeCommentCount+topic.getCountComment());//若是恢复，则类型加上当前帖子的评论数
            category.setCountComments(oldCateCommentCount+topic.getCountComment());//若是恢复，则范畴加上当前帖子的评论数
            type.setCountTopics(oldTypeTopicCount+1);
            category.setCountTopics(oldCateTopicCount+1);*/
            type.setCountTopics(oldTypeTopicCount+1);
        }
        else if(topic.getStatus()==0||topic.getStatus()==1){
            topic.setStatus(2);
            /*type.setCountComments(oldTypeCommentCount-topic.getCountComment());//若是删除，则类型减去当前帖子的评论数
            category.setCountComments(oldCateCommentCount-topic.getCountComment());//若是删除，则范畴减去当前帖子的评论数
            type.setCountTopics(oldTypeTopicCount-1);
            category.setCountTopics(oldCateTopicCount-1);*/
            type.setCountTopics(oldTypeTopicCount-1);
        }
        /*SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(typesMapper.class).updateTopicsCountAndCommentCount(type);//更新类型的评论数和帖子数
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);*/
        /*SqlSession sqlSession2 = sqlUtil.getSql();
        sqlSession2.getMapper(categorysMapper.class).updateTopicsCountAndCommentCount(category);//更新范畴的评论数和帖子数
        sqlUtil.commit(sqlSession2);
        sqlUtil.sqlClose(sqlSession2);*/
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(typesMapper.class).updateTopicsCount(type);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);//更新类型帖子数
        SqlSession sqlSession3 = sqlUtil.getSql();
        sqlSession3.getMapper(topicsMapper.class).deleteTopic(topic);//将帖子逻辑删除或恢复
        sqlUtil.commit(sqlSession3);
        sqlUtil.sqlClose(sqlSession3);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

    @RequestMapping("/getThePrivateTopics")
    public String getThePrivateTopics(int userId , int nowPage , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> topicsList = sqlSession.getMapper(topicsMapper.class).getThePrivateTopic(userId);
        sqlUtil.sqlClose(sqlSession);
        this.pageBean = methodForGetAllHotTopics.getHotForPages(10 , nowPage ,topicsList);
        request.setAttribute("priTopics" , pageBean.getListTopics());
        request.setAttribute("pageBean" , pageBean);
        return "user/pri";
    }

    @RequestMapping("/toPrivateTopic")
    public String toPrivateTopic(int topicId , int userId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getPrivateTopic(topicId , userId);
        sqlUtil.sqlClose(sqlSession);
        HttpSession session = request.getSession();
        session.setAttribute("topic" , topic);
        return "topic/thePrivateTopics";
    }

    @RequestMapping("/toOldHome")
    public String toOldHome(){
        return "home/index";
    }

    @RequestMapping("/toNewHome")
    public String toNewHome(){
        return "gp2.0Frontend/index";
    }

    @RequestMapping("/getTheLabel")
    @ResponseBody
    public List<Types> getTheLabel(){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Types> typesList = sqlSession.getMapper(typesMapper.class).readTypes();
        return typesList;
    }

    @RequestMapping("/editTopic")
    public String editTopic(int topicId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Topics topic = sqlSession.getMapper(topicsMapper.class).getTheTopicById(topicId);
        sqlUtil.sqlClose(sqlSession);
        HttpSession session = request.getSession();
        session.setAttribute("topic" , topic);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/user/newPlan.jsp";
    }

    @RequestMapping("/updateTopicContent")
    public void updateTopicContent(String tcontent , String tId){
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(topicsMapper.class).updateTopicContent(tId , tcontent);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
    }


}
