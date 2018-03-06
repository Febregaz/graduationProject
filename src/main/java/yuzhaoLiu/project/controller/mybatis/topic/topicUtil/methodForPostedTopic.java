package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.Date;

public class methodForPostedTopic {

    public static int addTopic(Topics topic, Users user , int typeId , int integral , String topicTitle , String tcontent){
        Date ntime = new Date();
        user.setIntegral(user.getIntegral() + 2 - topic.getIntegral()); // 用户发布一条帖子加2积分
        user.setTopCount(user.getTopCount()+1);
        topic.setTopicsUser(user);

        Types ty = getTypeMapper.getTheTypesMapper().getTypeById(typeId);
        ty.setCountTopics(ty.getCountTopics() + 1); // 帖子相应的类型里的帖子数加1
        ty.getTypesCategory().setCountTopics(
                ty.getTypesCategory().getCountTopics() + 1); // 帖子相应的大类型里的帖子数加1
        topic.setTopicsType(ty);
        topic.setTopicTime(ntime);
        topic.setCountComment(0);
        topic.setStatus(0);
        topic.setIntegral(integral);
        topic.setTitle(topicTitle);
        topic.setContent(tcontent);
        getTopicsMapper.getTheTopicsMapper().addTopic(topic);
        getTopicsMapper.sqlCommit();
        return topic.getId();
    }
}
