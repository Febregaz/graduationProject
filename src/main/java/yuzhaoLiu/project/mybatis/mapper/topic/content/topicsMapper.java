package yuzhaoLiu.project.mybatis.mapper.topic.content;

import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.List;

public interface topicsMapper{

    /*
    * return the all data from t_topic
    */
    public List<Topics> readTopics();

    /*
    * return ten newest data from t_topic
    */
    public List<Topics> getTheNewestTopics();

    public List<Topics> getTheHotestTopics();

    public List<Topics> getTheNicestTopics();

}
