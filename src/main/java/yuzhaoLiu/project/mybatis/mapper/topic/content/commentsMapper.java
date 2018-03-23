package yuzhaoLiu.project.mybatis.mapper.topic.content;

import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;

import java.util.Date;
import java.util.List;

public interface commentsMapper {

    public List<Comments> readComments();

    public List<Comments> getTheCommentsByTopicId(int id);

    public void addTheComment(Comments comment);

    public List<Comments> getTheCommentsByUserId(int id);

    public void updateCommentIntegral(Comments comment);

}
