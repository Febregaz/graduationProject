package yuzhaoLiu.project.mybatis.mapper.topic.category;

import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;

import java.util.List;

public interface categorysMapper {

    public List<Categorys> readCategorys();

    public Categorys getCategoryById(int id);

    public void updateTopicCount(Categorys categorys);

    public void updateCommentCount(Categorys categorys);

    public void updateCategoryName(Categorys categorys);

    public void addCategory(Categorys categorys);

    public void updateTopicsCountAndCommentCount(Categorys category);

}
