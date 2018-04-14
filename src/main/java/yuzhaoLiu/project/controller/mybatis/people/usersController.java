package yuzhaoLiu.project.controller.mybatis.people;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.*;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getNewsMapper;
import yuzhaoLiu.project.mybatis.entity.people.Grades;
import yuzhaoLiu.project.mybatis.entity.people.User;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.News;
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

    @RequestMapping("/uploadUserPicNotUse")
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
                    //F:/graduationProject/src/main/webapp/upload/
                    //D:/graduationProject/graduationProject/src/main/webapp/upload/
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
        return "user/updateInfo";
    }

    @RequestMapping("/uploadUserPic")
    public String uploadUserPicTest(HttpServletRequest request){
        String fileName="";
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = Murequest.getFileMap();// 得到文件map对象
        String upaloadUrl = request.getSession().getServletContext()
                .getRealPath("/")
                + "upload/";// 得到当前工程路径拼接上文件名
        File dir = new File(upaloadUrl);
        int counter=0;
        System.out.println(upaloadUrl);
        if (!dir.exists())// 目录不存在则创建
            dir.mkdirs();
        for (MultipartFile file : files.values()) {
            counter++;
            fileName = file.getOriginalFilename();
            File tagetFile = new File(upaloadUrl + fileName);// 创建文件对象
            if (!tagetFile.exists()) {// 文件名不存在 则新建文件，并将文件复制到新建文件中
                try {
                    tagetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    file.transferTo(tagetFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setPicture("upload/"+fileName);
        getPeopleMapper.getTheUsersMapper().updateUserPic(user);
        getPeopleMapper.sqlCommit();
        return "user/updateInfo";
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(HttpServletRequest request , String userNic , String userSex , String userEmail , String userProfe , String userFrom , String userIntro){
        logger.info(userNic+userSex+userEmail+userProfe+userFrom+userIntro);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setNickname(userNic);user.setSex(userSex);user.setEmail(userEmail);user.setProfession(userProfe);
        user.setComefrom(userFrom);user.setIntroduction(userIntro);
        getPeopleMapper.getTheUsersMapper().updateUserInfo(user);
        getPeopleMapper.sqlCommit();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        logger.info(basePath);
        return "redirect:"+basePath+"NC-JSP/user/home.jsp";
    }

    @RequestMapping("/updateUserPass")
    public String updateUserPass(HttpServletRequest request , String userPass){
        logger.info("I am not good and in updateUserPass");
        logger.info(userPass);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setPassword(userPass);
        getPeopleMapper.getTheUsersMapper().updateUserPass(user);
        getPeopleMapper.sqlCommit();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        logger.info(basePath);
        return "redirect:"+basePath+"NC-JSP/user/home.jsp";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        List<Users> usersList = getPeopleMapper.getTheUsersMapper().readUsers();
        this.pageBean = methodForManageAll.ManageUsersForPage(12 , nowPage , usersList);
        request.setAttribute("listUser" , pageBean.getListUser());
        request.setAttribute("pageBean"  , pageBean);
        return "admin/manageUsers";
    }

    @RequestMapping("/getUserNews")
    public String getUserNews(HttpServletRequest request , int nowPage){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        List<News> newsList = getNewsMapper.getTheNewsMapper().getNewsByUserId(user.getId());
        logger.info("size:"+newsList.size());
        this.pageBean = methodForGetNews.ManageNewsForPage(5 , nowPage , newsList);
        logger.info(pageBean.getListNews().size());
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listNews" , pageBean.getListNews());
        return "user/news";
    }

    @RequestMapping("/hadRead")
    @ResponseBody
    public News hadRead(int newStatus , int newId , HttpServletRequest request){
        News news = getNewsMapper.getTheNewsMapper().getNewById(newId);
        HttpSession session = request.getSession();
        Users user =(Users) session.getAttribute("userInfo");
        if(newStatus==0){
            news.setStatus(1);
            user.setClock(user.getClock()-1);
        }
        else if(newStatus==1){
            news.setStatus(0);
            user.setClock(user.getClock()+1);
        }
        //将t_new的数据的状态改为已读或者未读
        getNewsMapper.getTheNewsMapper().updateStatus(news);
        getNewsMapper.sqlCommit();
        getNewsMapper.sqlClose();
        getPeopleMapper.getTheUsersMapper().updateClock(user);
        getPeopleMapper.sqlCommit();
        getPeopleMapper.sqlClose();
        return news;
    }

    @RequestMapping("/getUserById")
    public String getUserById(int userId , HttpServletRequest request){
        Users user = getPeopleMapper.getTheUsersMapper().getUserById(userId);
        HttpSession session = request.getSession();
        session.setAttribute("user" , user);
        return "user/user";
    }

    @RequestMapping("/disabledOrAbleUser")
    public String disabledOrAbleUser(int userId ,HttpServletRequest request){
        Users user = getPeopleMapper.getTheUsersMapper().getUserById(userId);
        if(user.getStatus()==0){
            user.setStatus(1);
        }
        else if(user.getStatus()==1){
            user.setStatus(0);
        }
        getPeopleMapper.getTheUsersMapper().updateUserStatus(user);
        getPeopleMapper.sqlCommit();
        getPeopleMapper.sqlClose();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

}
