package yuzhaoLiu.project.controller.mybatis.topic.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getCommentsMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

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
        Users user = (Users) session.getAttribute("userInfo");
        Topics topic = (Topics) session.getAttribute("topic");
        comment.setContent(commentContent);
        Boolean flag = newComment(comment , user , topic);
        int id = topic.getId();
        return "redirect:topics/toTheDetailPage?topicId="+id+"&&nowPage=1";
    }

    public boolean newComment(Comments comment, Users user, Topics tpc) {
        Date cTime = new Date();
        comment.setCommentTime(cTime);//设置评论的时间
        //Topics tpc = this.topicDao.find(topic.getId());
        tpc.setCountComment(tpc.getCountComment() + 1); // 帖子评论数加1
        Types type = tpc.getTopicsType();
        type.setCountComments(type.getCountComments() + 1); // 帖子小类型评论数加1
        getTypeMapper.getTheTypesMapper().updateCommentsCount(type);
        getTypeMapper.sqlCommit();
        Categorys category = tpc.getTopicsType().getTypesCategory();
        category.setCountComments(category.getCountComments() + 1); // 帖子大类型评论数加1
        getCategoryMapper.getTheCategorysMapper().updateCommentCount(category);
        getCategoryMapper.sqlCommit();
        user.setIntegral(user.getIntegral()+1);  //回复帖子，用户积分加1
        user.setComCount(user.getComCount()+1);  //用户总评论数加1
        getPeopleMapper.getTheUsersMapper().updateIntegralAndComment(user);
        getPeopleMapper.sqlCommit();
        if (tpc.getTopicsUser().getId() != user.getId()) {
            tpc.getTopicsUser().setClock(tpc.getTopicsUser().getClock() + 1);//如果评论者不是帖子作者本人，则通知帖子作者有新评论
            //TODO:未读信息功能
            /*News tnew = new News();
            tnew.setNewsCommentUser(user);
            tnew.setNewsTopic(tpc);
            tnew.setStatus(0); // 将消息设为未读
            tnew.setNewTime(cTime);
            this.newDao.add(tnew);*/
        }
        /*更新topic的comment数目并且提交事务*/
        getTopicsMapper.getTheTopicsMapper().updateTopicComment(tpc.getId() , tpc.getCountComment());
        getTopicsMapper.sqlCommit();
        comment.setCommentsTopic(tpc);
        comment.setFloor(tpc.getCountComment()); // 设置此评论所在楼层
        comment.setCommentsUser(user);
        getCommentsMapper.getTheCommentsMapper().addTheComment(comment);
        getCommentsMapper.sqlCommit();
        return true;
    }

}
