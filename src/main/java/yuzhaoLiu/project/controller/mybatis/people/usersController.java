package yuzhaoLiu.project.controller.mybatis.people;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.download.getResourcesMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.*;
import yuzhaoLiu.project.controller.mybatis.people.sendMailUtil.CodeUtil;
import yuzhaoLiu.project.controller.mybatis.people.sendMailUtil.MailUtil;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getNewsMapper;
import yuzhaoLiu.project.mybatis.entity.download.Resources;
import yuzhaoLiu.project.mybatis.entity.people.Grades;
import yuzhaoLiu.project.mybatis.entity.people.User;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.News;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.people.gradesMapper;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.newsMapper;
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
    String nameFather;
    String passwordFather;

    @RequestMapping("/usersLogin")
    public String usersLogin(String username, String password, HttpServletRequest request){
        logger.info("Logining..."+username+" "+password);
        SqlSession sqlSession = sqlUtil.getSql();
        usersMapper usersMapper = sqlSession.getMapper(usersMapper.class);
        Users users = usersMapper.userLogin(username);
        sqlUtil.sqlClose(sqlSession);
        HttpSession session = request.getSession();
        if(users==null){
            String message = "该账号不存在";
            session.setAttribute("tipMessage", message);
            return "home/error";
        }
        else if(users.getPassword().equals(password)){
            if(users.getStatus()==1){
                String message = "该账号目前处于被禁用状态!无法进行此操作！";
                session.setAttribute("tipMessage", message);
                return "home/error";
            }
            else if(users.getStatus()==2){
                String message = "该账号暂未激活!无法进行此操作！";
                session.setAttribute("tipMessage", message);
                return "home/error";
            }
            session.setAttribute("userInfo",users);
            logger.info("Where");
            //return "home/index";/*localhost:8080*/
            return "redirect:http://www.617museum.top/NC-JSP/admin/manage.jsp";
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
        //logger.info(username+" "+nickname+" "+password+" "+email);
        SqlSession sqlSession = sqlUtil.getSql();
        Users users = sqlSession.getMapper(usersMapper.class).userLogin(email);
        sqlUtil.sqlClose(sqlSession);
        if(users!=null) {
            String message = "该邮箱已存在，请重新注册！";
            request.getSession().setAttribute("tipMessage", message);
            return "home/error";
        }
        else{
            SqlSession sqlSession1 = sqlUtil.getSql();
            List<Grades> gradesList = sqlSession1.getMapper(gradesMapper.class).readGrades();
            sqlUtil.sqlClose(sqlSession1);
            Grades grade = gradesList.get(0);
            Date date = new Date();
            Users user = new Users();
            user.setNickname(nickname);user.setUsername(username);user.setPassword(password);user.setEmail(email);user.setStatus(2);
            user.setRegisterTime(date);
            user.setUsersGrade(grade);
            SqlSession sqlSession2 = sqlUtil.getSql();
            sqlSession2.getMapper(usersMapper.class).registeruser(user);
            sqlUtil.commit(sqlSession2);
            sqlUtil.sqlClose(sqlSession2);
            String code= CodeUtil.generateUniqueCode();
            new Thread(new MailUtil(email, code ,username , password)).start();
            return "user/index";
        }
    }

    @RequestMapping("/toTheHomePageAfterActivation")
    public String toTheHomePageAfterActivation(HttpServletRequest request , String username){
        SqlSession sqlSession = sqlUtil.getSql();
        Users user = sqlSession.getMapper(usersMapper.class).userLogin(username);
        sqlUtil.sqlClose(sqlSession);
        user.setStatus(0);
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(usersMapper.class).updateUserStatus(user);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        request.getSession().setAttribute("userInfo" , user);
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

    @RequestMapping("/uploadUserPicNotUseToo")
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

    @RequestMapping("/uploadUserPic")
    public String uploadUserPicTestTwo(@RequestParam("file")MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response){
        String origFilename = multipartFile.getOriginalFilename(); // 图片名
        String upaloadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";// 得到当前工程路径拼接上文件名
        File dest = new File(upaloadUrl + origFilename); // 保存位置
        try {
            // 先尝试压缩并保存图片
            Thumbnails.of(multipartFile.getInputStream()).scale(0.25f).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            try {
                // 失败了再用springmvc自带的方式
                multipartFile.transferTo(dest);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setPicture("upload/"+origFilename);
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(usersMapper.class).updateUserPic(user);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        return "user/updateInfo";
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(HttpServletRequest request , String userNic , String userSex , String userEmail , String userProfe , String userFrom , String userIntro){
        logger.info(userNic+userSex+userEmail+userProfe+userFrom+userIntro);
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        user.setNickname(userNic);user.setSex(userSex);user.setEmail(userEmail);user.setProfession(userProfe);
        user.setComefrom(userFrom);user.setIntroduction(userIntro);
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(usersMapper.class).updateUserInfo(user);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
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
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(usersMapper.class).updateUserPass(user);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        logger.info(basePath);
        return "redirect:"+basePath+"NC-JSP/user/home.jsp";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Users> usersList = sqlSession.getMapper(usersMapper.class).readUsers();
        sqlUtil.sqlClose(sqlSession);
        this.pageBean = methodForManageAll.ManageUsersForPage(12 , nowPage , usersList);
        request.setAttribute("listUser" , pageBean.getListUser());
        request.setAttribute("pageBean"  , pageBean);
        return "admin/manageUsers";
    }

    @RequestMapping("/getUserNews")
    public String getUserNews(HttpServletRequest request , int nowPage){
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        SqlSession sqlSession = sqlUtil.getSql();
        List<News> newsList = sqlSession.getMapper(newsMapper.class).getNewsByUserId(user.getId());
        sqlUtil.sqlClose(sqlSession);
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
        SqlSession sqlSession = sqlUtil.getSql();
        News news = sqlSession.getMapper(newsMapper.class).getNewById(newId);
        sqlUtil.sqlClose(sqlSession);
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
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(newsMapper.class).updateStatus(news);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        SqlSession sqlSession2 = sqlUtil.getSql();
        sqlSession2.getMapper(usersMapper.class).updateClock(user);
        sqlUtil.commit(sqlSession2);
        sqlUtil.sqlClose(sqlSession2);
        return news;
    }

    @RequestMapping("/getUserById")
    public String getUserById(int userId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Users user = sqlSession.getMapper(usersMapper.class).getUserById(userId);
        sqlUtil.sqlClose(sqlSession);
        HttpSession session = request.getSession();
        session.setAttribute("user" , user);
        return "user/user";
    }

    @RequestMapping("/disabledOrAbleUser")
    public String disabledOrAbleUser(int userId ,HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Users user = sqlSession.getMapper(usersMapper.class).getUserById(userId);
        sqlUtil.sqlClose(sqlSession);
        if(user.getStatus()==0){
            user.setStatus(1);
        }
        else if(user.getStatus()==1){
            user.setStatus(0);
        }
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(usersMapper.class).updateUserStatus(user);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

    //TODO:弹幕视频后台弹幕数据的获取
    @RequestMapping("/barrageGet")
    @ResponseBody
    public String barrageGet(){
        return "{\"code\":1,\"danmaku\":[{\"author\":\"bilibili11b2034b\",\"time\":\"0.43500\",\"text\":\"熟悉的配方\",\"color\":\"#ffffff\",\"type\":\"right\"},{\"author\":\"bilibili71e0f8cf\",\"time\":\"126.82000\",\"text\":\"女神笑的太治愈了（∩△∩）\",\"color\":\"#ffffff\",\"type\":\"right\"},{\"author\":\"bilibili613e4709\",\"time\":\"41.14100\",\"text\":\"尴尬女王\",\"color\":\"#ffffff\",\"type\":\"right\"},{\"author\":\"bilibili6ff6ac4a\",\"time\":\"127.20500\",\"text\":\"回家吧！孩子在家都饿了。。。。\",\"color\":\"#ffffff\",\"type\":\"right\"},{\"author\":\"bilibili55b115e5\",\"time\":\"158.78500\",\"text\":\"素质素质，都哪去了。别拦着我，我也要看\",\"color\":\"#e70012\",\"type\":\"right\"}]}";
    }

    @RequestMapping("/uploadResources")
    public String uploadResources(@RequestParam("file")MultipartFile multipartFile, HttpServletRequest request, int typeId , String resourceName){
        String origFilename = multipartFile.getOriginalFilename(); // 资源名
        String upaloadUrl = request.getSession().getServletContext().getRealPath("/") + "uploadResource/";// 得到当前工程路径拼接上文件名
        File dest = new File(upaloadUrl + origFilename); // 保存位置
        try {
            multipartFile.transferTo(dest); //上传资源
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("userInfo");
        Types type = getTypeMapper.getTheTypesMapper().getTypeById(typeId);
        Resources resource = new Resources();
        resource.setResourcesURL(origFilename);
        resource.setResourcesType(type);
        resource.setDownloadTimes(0);
        resource.setNiceResources(0);
        resource.setPublishTime(new Date());
        resource.setResourcesIntegral(0);
        resource.setResourcesUser(user);
        resource.setResourcesName(resourceName);
        getResourcesMapper.getTheResourcesMapper().addResources(resource);
        getResourcesMapper.sqlCommit();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }
}
