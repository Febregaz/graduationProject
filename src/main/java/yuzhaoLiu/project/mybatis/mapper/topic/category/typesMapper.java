package yuzhaoLiu.project.mybatis.mapper.topic.category;

import yuzhaoLiu.project.mybatis.entity.topic.category.Types;

import java.lang.reflect.Type;
import java.util.List;

public interface typesMapper {

    public List<Types> readTypes();

    public List<Types> getAllTypesByCategoryId(int id);

    public Types getTypeById(int id);

    public void updateTopicsCount(Types type);

    public void updateCommentsCount(Types type);

    public void updateTypeName(Types type);

    public void addType(Types type);

    public void updateTopicsCountAndCommentCount(Types type);

    public void deleteType(int id);

}
