package yuzhaoLiu.project.testMybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.download.getResourcesMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getNewsMapper;
import yuzhaoLiu.project.mybatis.entity.download.Resources;
import yuzhaoLiu.project.mybatis.entity.people.User;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.News;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.mapper.UserMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

import java.util.Date;
import java.util.List;

public class testMybatis {

    private static Logger logger = Logger.getLogger(testMybatis.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    /*
    Operation : Add
    */
    @Test
    public void addUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("log_15",22);
            userMapper.insertUser(user);
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
            logger.info("create a data successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    Operation : Delete
    */
    @Test
    public void deleteUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUser("ring");
            sqlSession.commit();
            logger.info("delete a data successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    Operation : Update
    */
    @Test
    public void updateUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser("aragami");
            user.setName("ring");
            userMapper.updateUser(user);
            sqlSession.commit();
            logger.info("update a data successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    Operation : Read
    */
    @Test
    public void readUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.readUser();
            for(User u : userList){
                System.out.println("name:"+u.getName());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    Operation : truncate
        delete all data from User
    */
    @Test
    public void truncateUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.truncate();
            sqlSession.commit();
            logger.info("truncate User successfully !");
        } finally {
            sqlSession.close();
        }
    }

    /*
    Operation : drop
        abandon table user
    */
    @Test
    public void dropUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.drop();
            logger.info("drop User successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addNews(){
/*        News news = new News();
        news.setNewTime(new Date());
        news.setStatus(0);
        news.setNewsCommentUser(new Users());
        news.setNewsTopic(new Topics());*/
        List<News> usersList = getNewsMapper.getTheNewsMapper().getNewsByUserId(1);
        for(News news : usersList){
            System.out.println(news.getNewsTopic().getTitle());
        }
    }

    @Test
    public void testAddResourcesUrl(){
        Categorys category = getCategoryMapper.getTheCategorysMapper().getCategoryById(1);
        List<Types> typesList = getTypeMapper.getTheTypesMapper().getAllTypesByCategoryId(category.getId());
        Users user = getPeopleMapper.getTheUsersMapper().getUserById(3);
        Resources resource = new Resources();
        resource.setResourcesURL("java.jpg");
        resource.setResourcesType(typesList.get(1));
        resource.setDownloadTimes(10);
        resource.setNiceResources(0);
        resource.setPublishTime(new Date());
        resource.setResourcesIntegral(10);
        resource.setResourcesUser(user);
        resource.setResourcesName("资源三");
        getResourcesMapper.getTheResourcesMapper().addResources(resource);
        getResourcesMapper.sqlCommit();
        getResourcesMapper.sqlClose();
    }

    @Test
    public void getAllResources(){
        List<Resources> resourcesList = getResourcesMapper.getTheResourcesMapper().getAllResources();
        for(Resources resource : resourcesList){
            System.out.println(resource.getResourcesURL()+"/"+resource.getResourcesType().getName()+"/"+resource.getResourcesType().getTypesCategory().getNamee()+"/"+resource.getResourcesUser().getNickname());
        }
        Resources resources = getResourcesMapper.getTheResourcesMapper().getResourcesByURL("中国地图.jpg");
        System.out.println(resources.getDownloadTimes());
    }

    @Test
    public void sengMail(){
        String from = "1844927304@qq.com";//发件人的电子邮箱
        String host = "smtp.qq.com";//指定发送邮件的主机
    }

}
