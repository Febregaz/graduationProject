package yuzhaoLiu.project.mybatis.mapper;

import yuzhaoLiu.project.mybatis.entity.people.User;

import java.util.List;

public interface UserMapper {
    public void insertUser(User user);

    public User getUser(String name);

    public void updateUser(User user);

    public void deleteUser(String name);

    public List<User> readUser();

    public void truncate();

    public void drop();
}
