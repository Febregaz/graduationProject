package yuzhaoLiu.project.mybatis.mapper.topic;

import yuzhaoLiu.project.mybatis.entity.topic.News;

import java.util.List;

public interface newsMapper {

    public void addNew(News news);

    public List<News> getNewsByUserId(int id);

    public News getNewById(int id);

    public void updateStatus(News news);

}
