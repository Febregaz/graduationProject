package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import org.apache.ibatis.session.SqlSession;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.Date;

public class methodForPostedTopic {

    public static int addTopic(Topics topic, Users user , int typeId , int integral , String topicTitle , String tcontent ,int priOrPub){
        Date ntime = new Date();
        /*取消用户积分增长*/
        /*user.setIntegral(user.getIntegral() + 2 - topic.getIntegral()); // 用户发布一条帖子加2积分
        user.setTopCount(user.getTopCount()+1);
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(usersMapper.class).updateTopicsCount(user);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);*/
        topic.setTopicsUser(user);
        Types ty = new Types();
        if(priOrPub<=0){
            SqlSession sqlSession1 = sqlUtil.getSql();
            ty = sqlSession1.getMapper(typesMapper.class).getTypeById(typeId);
            ty.setCountTopics(ty.getCountTopics() + 1); // 帖子相应的类型里的帖子数加1
            sqlUtil.sqlClose(sqlSession1);
            /*去掉type的记录*/
            /*SqlSession sqlSession1 = sqlUtil.getSql();
            ty = sqlSession1.getMapper(typesMapper.class).getTypeById(typeId);
            ty.setCountTopics(ty.getCountTopics() + 1); // 帖子相应的类型里的帖子数加1
            sqlUtil.sqlClose(sqlSession1);
            SqlSession sqlSession2 = sqlUtil.getSql();
            sqlSession2.getMapper(typesMapper.class).updateTopicsCount(ty);
            sqlUtil.commit(sqlSession2);
            sqlUtil.sqlClose(sqlSession2);*/
            /*去掉category的记录*/
            /*int categoryId = ty.getTypesCategory().getId();
            SqlSession sqlSession3 = sqlUtil.getSql();
            Categorys category = sqlSession3.getMapper(categorysMapper.class).getCategoryById(categoryId);
            category.setCountTopics(
                    category.getCountTopics() + 1); // 帖子相应的大类型里的帖子数加1
            getCategoryMapper.getTheCategorysMapper().updateTopicCount(category);
            sqlUtil.commit(sqlSession3);
            sqlUtil.sqlClose(sqlSession3);*/
        }
        else{
            SqlSession sqlSession1 = sqlUtil.getSql();
            ty = sqlSession1.getMapper(typesMapper.class).getTypeById(typeId);
            sqlUtil.sqlClose(sqlSession1);
        }
        SqlSession sqlSession2 = sqlUtil.getSql();
        sqlSession2.getMapper(typesMapper.class).updateTopicsCount(ty);
        sqlUtil.commit(sqlSession2);
        sqlUtil.sqlClose(sqlSession2);
        topic.setTopicsType(ty);
        topic.setTopicTime(ntime);
        topic.setCountComment(0);
        topic.setStatus(0);
        topic.setIntegral(integral);
        topic.setTitle(topicTitle);
        topic.setContent(tcontent);
        topic.setOnlyUserCanSee(priOrPub);
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(topicsMapper.class).addTopic(topic);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        return topic.getId();
    }
}
