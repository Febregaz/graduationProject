package yuzhaoLiu.project.controller.mybatis.people;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getGradeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.methodForGetUserComments;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.methodForGetUserTopics;
import yuzhaoLiu.project.mybatis.entity.people.Grades;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/users")
public class usersController extends topController {

    Pages pageBean ;

    @RequestMapping("/usersLogin")
    public String usersLogin(String username, String password, HttpServletRequest request){
        logger.info("Logining..."+username+" "+password);
        usersMapper usersMapper = sqlUtil.getSql().getMapper(usersMapper.class);
        Users users = usersMapper.userLogin(username);
        HttpSession session = request.getSession();
        if(users.getPassword().equals(password)){
            if(users.getStatus()==1){
                String message = "该账号目前处于被禁用状态!无法进行此操作！";
                session.setAttribute("tipMessage", message);
                return "home/error";
            }
            session.setAttribute("userInfo",users);
            logger.info("Where");
            return "home/index";
        }
        String message = "用户名或密码错误！";
        session.setAttribute("tipMessage", message);
        return "home/error";
    }

    @RequestMapping("/usersLogout")
    public String usersLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("userInfo");
        return "home/login";
    }

    @RequestMapping("/usersRegister")
    public String usersRegister(String username , String nickname , String password , String email , HttpServletRequest request){
        logger.info(username+" "+nickname+" "+password+" "+email);
        List<Grades> gradesList = getGradeMapper.getTheGradesMapper().readGrades();
        Grades grade = gradesList.get(0);
        Date date = new Date();
        Users user = new Users();
        user.setNickname(nickname);user.setUsername(username);user.setPassword(password);user.setEmail(email);
        user.setRegisterTime(date);
        user.setUsersGrade(grade);
        getPeopleMapper.getTheUsersMapper().registeruser(user);
        getPeopleMapper.sqlCommit();
        HttpSession session = request.getSession();
        session.setAttribute("userInfo" , user);
        return "home/index";
    }

    @RequestMapping("/getUserTopics")
    public String getUserTopics(int nowPage , HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("userInfo");
        pageBean = methodForGetUserTopics.getTopicsForPages(5 , nowPage , user.getId());
        List<Topics> topicsList = pageBean.getListTopics();
        session.setAttribute("pageBean" , pageBean);
        session.setAttribute("listTopic" , pageBean.getListTopics());
        return "user/topics";
    }

    @RequestMapping("/getUserComments")
    public String getUserComments(int nowPage , HttpServletRequest request){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        pageBean = methodForGetUserComments.getCommentsForPages(5 ,nowPage  ,user.getId());
        List<Comments> commentsList = pageBean.getListComments();
        session.setAttribute("pageBean" , pageBean);
        session.setAttribute("listComment" , pageBean.getListComments());
        return "user/comments";
    }

    @RequestMapping("/uploadUserPic")
    public String uploadUserPic(HttpServletRequest request) throws IllegalStateException, IOException{
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        String fileName = null;
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {

                    String path="F:/graduationProject/src/main/webapp/upload/"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                    fileName = file.getOriginalFilename();
                }

            }

        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setPicture("upload/"+fileName);
        getPeopleMapper.getTheUsersMapper().updateUserPic(user);
        getPeopleMapper.sqlCommit();
        return "";
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(String userNic , String userSex , String userEmail , String userProfe , String userFrom , String userIntro){
        logger.info(userNic+userSex+userEmail+userProfe+userFrom+userIntro);
        return "";
    }

}
