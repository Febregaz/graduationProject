package yuzhaoLiu.project.mybatis.mapper;

import yuzhaoLiu.project.mybatis.entity.User;

public interface UserMapper {
    public void insertUser(User user);

    public User getUser(String name);
}
