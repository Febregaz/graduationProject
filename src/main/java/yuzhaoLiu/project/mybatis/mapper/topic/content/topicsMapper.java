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

    /*
     * return ten hot data from t_topic
     */
    public List<Topics> getTheHotestTopics();

    /*
     * return ten nicest data from t_topic
     */
    public List<Topics> getTheNicestTopics();

    /*
    * search the topic by id
    * */
    public Topics getTheTopicById(int id);

    /*
    * update the topic's comment+1
    * */
    public void updateTopicComment(int id , int commentCount);

    /*
    * add a topic data
    * */
    public void addTopic(Topics topic);

    /*
    * obtain the topics by typeId
    * */
    public List<Topics> getTopicsByTypeId(int id);

    /*
     *以下三个分别是首页的三个more按钮的方法
     * */
    public List<Topics> getAllHotTopics();

    public List<Topics> getAllFreshTopics();

    public List<Topics> getAllNiceTopics();

    public List<Topics> searchTopics(String content);

    public List<Topics> getTopicsByUserId(int id);

    public void updateTopicStatus(Topics topic);

    public void updateTopicNice(Topics topic);

    public void deleteTopic(Topics topics);

    public void updateTopicCommentCount(Topics topic);

}
