package yuzhaoLiu.project.mybatis.mapper.topic.category;

import yuzhaoLiu.project.mybatis.entity.topic.category.Types;

import java.util.List;

public interface typesMapper {

    public List<Types> readTypes();

    public List<Types> getAllTypesByCategoryId(int id);

    public Types getTypeById(int id);

}
