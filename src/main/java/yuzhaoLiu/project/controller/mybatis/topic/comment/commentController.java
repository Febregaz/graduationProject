package yuzhaoLiu.project.controller.mybatis.topic.comment;

import com.mchange.v2.sql.SqlUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getCommentsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getNewsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.News;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.content.commentsMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.newsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class commentController extends topController {

    Comments comment = new Comments();

    @RequestMapping("/postedComment")
    public String postedComment (String commentContent , HttpServletRequest request) throws IOException {
        //String strComment = new String(commentContent.getBytes("iso-8859-1"),"UTF-8") ;//将前台传进来的数据进行常规转码
        HttpSession session = request.getSession();
        //Users user = (Users) session.getAttribute("userInfo");
        Users user = new Users();
        user.setNickname("peopleUnknown");
        user.setId(2);
        Topics topic = (Topics) session.getAttribute("topic");
        logger.info("cateName:"+topic.getTopicsType().getTypesCategory().getNamee());
        comment.setContent(commentContent);
        Boolean flag = newComment(comment , user , topic);
        int id = topic.getId();
        return "redirect:/617/Ahri"+id+"_1.617museum";
    }

    @RequestMapping("/newPostedComment")
    public String newPostedComment (String commentContent , HttpServletRequest request) throws IOException {
        //String strComment = new String(commentContent.getBytes("iso-8859-1"),"UTF-8") ;//将前台传进来的数据进行常规转码
        HttpSession session = request.getSession();
        //Users user = (Users) session.getAttribute("userInfo");
        Users user = new Users();
        user.setNickname("peopleUnknown");
        user.setId(2);
        Topics topic = (Topics) session.getAttribute("topic");
        logger.info("cateName:"+topic.getTopicsType().getTypesCategory().getNamee());
        comment.setContent(commentContent);
        Boolean flag = newComment(comment , user , topic);
        int id = topic.getId();
        return "redirect:/617/newDetail"+id+".617museum";
    }

    public boolean newComment(Comments comment, Users user, Topics tpc) {
        Date cTime = new Date();
        comment.setCommentTime(cTime);//设置评论的时间
        //Topics tpc = this.topicDao.find(topic.getId());
        /*tpc.setCountComment(tpc.getCountComment() + 1); // 帖子评论数加1
        Types type = tpc.getTopicsType();
        logger.info("type前:"+type.getCountComments());
        type.setCountComments(type.getCountComments() + 1); // 帖子小类型评论数加1
        logger.info("type后:"+type.getCountComments());
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(typesMapper.class).updateCommentsCount(type);
        //getTypeMapper.getTheTypesMapper().updateCommentsCount(type);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);*/

        /*Categorys category = tpc.getTopicsType().getTypesCategory();
        logger.info("category前:"+category.getCountComments());
        category.setCountComments(category.getCountComments() + 1); // 帖子大类型评论数加1
        logger.info("category后:"+category.getCountComments());
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(categorysMapper.class).updateCommentCount(category);
        //getCategoryMapper.getTheCategorysMapper().updateCommentCount(category);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);*/
        //user.setIntegral(user.getIntegral()+1);  //回复帖子，用户积分加1
        //user.setComCount(user.getComCount()+1);  //用户总评论数加1
        //getPeopleMapper.getTheUsersMapper().updateIntegralAndComment(user);
        //getPeopleMapper.sqlCommit();
        /*if (tpc.getTopicsUser().getId() != user.getId()) {*/
            tpc.getTopicsUser().setClock(tpc.getTopicsUser().getClock() + 1);//如果评论者不是帖子作者本人，则通知帖子作者有新评论
            SqlSession sqlSession2 = sqlUtil.getSql();
            sqlSession2.getMapper(usersMapper.class).updateClock(tpc.getTopicsUser());
            //getPeopleMapper.getTheUsersMapper().updateClock(tpc.getTopicsUser());
            sqlUtil.commit(sqlSession2);
            sqlUtil.sqlClose(sqlSession2);
            //TODO:未读信息功能
            News tnew = new News();
            tnew.setNewsCommentUser(user);
            tnew.setNewsTopic(tpc);
            tnew.setStatus(0); // 将消息设为未读
            tnew.setNewTime(cTime);
            SqlSession sqlSession3 = sqlUtil.getSql();
            sqlSession3.getMapper(newsMapper.class).addNew(tnew);
            //getNewsMapper.getTheNewsMapper().addNew(tnew);
            sqlUtil.commit(sqlSession3);
            sqlUtil.sqlClose(sqlSession3);
        /*}*/
        /*更新topic的comment数目并且提交事务*/
        /*SqlSession sqlSession4 = sqlUtil.getSql();
        sqlSession4.getMapper(topicsMapper.class).updateTopicComment(tpc.getId() , tpc.getCountComment());
        //getTopicsMapper.getTheTopicsMapper().updateTopicComment(tpc.getId() , tpc.getCountComment());
        sqlUtil.commit(sqlSession4);
        sqlUtil.sqlClose(sqlSession4);*/
        comment.setCommentsTopic(tpc);
        comment.setFloor(tpc.getCountComment()); // 设置此评论所在楼层
        comment.setCommentsUser(user);
        SqlSession sqlSession5 = sqlUtil.getSql();
        sqlSession5.getMapper(commentsMapper.class).addTheComment(comment);
        //getCommentsMapper.getTheCommentsMapper().addTheComment(comment);
        sqlUtil.commit(sqlSession5);
        sqlUtil.sqlClose(sqlSession5);
        return true;
    }

    @RequestMapping("/deleteComment")
    public String deleteComment(int commentId){
        Comments comment = getCommentsMapper.getTheCommentsMapper().deleteComment(commentId);
        comment.setStatus(1);//评论状态设为1,逻辑删除
        Users user = comment.getCommentsUser();
        int oldUserCommentCount = user.getComCount();
        user.setComCount(oldUserCommentCount-1);//对应的用户评论数减一
        Topics topic = comment.getCommentsTopic();
        int oldTopicCommentCount = topic.getCountComment();
        topic.setCountComment(oldTopicCommentCount-1);//对应的帖子的评论数减一
        Types type = comment.getCommentsTopic().getTopicsType();
        int oldTypeCommentCount = type.getCountComments();
        type.setCountComments(oldTypeCommentCount-1);//对应的帖子的类型的评论数减一
        Categorys category = comment.getCommentsTopic().getTopicsType().getTypesCategory();
        int oldCategoryCommentCount = category.getCountComments();
        category.setCountComments(oldCategoryCommentCount-1);//对应的帖子的范畴的评论数减一
        getCommentsMapper.getTheCommentsMapper().updateCommentStatus(comment);
        getCommentsMapper.sqlCommit();
        getCommentsMapper.sqlClose();
        getPeopleMapper.getTheUsersMapper().updateCommentsCount(user);
        getPeopleMapper.sqlCommit();
        getPeopleMapper.sqlClose();
        getTopicsMapper.getTheTopicsMapper().updateTopicCommentCount(topic);
        getTopicsMapper.sqlCommit();
        getTopicsMapper.sqlClose();
        getTypeMapper.getTheTypesMapper().updateCommentsCount(type);
        getTypeMapper.sqlCommit();
        getTypeMapper.sqlClose();
        getCategoryMapper.getTheCategorysMapper().updateCommentCount(category);
        getCategoryMapper.sqlCommit();
        getCategoryMapper.sqlClose();
        return "redirect:/617/Ahri"+topic.getId()+"_1.617museum";
    }

}
