package yuzhaoLiu.project.mybatis.mapper.people;

import yuzhaoLiu.project.mybatis.entity.people.Users;

import java.util.List;

public interface usersMapper {

    public List<Users> readUsers();

    public Users userLogin(String username);
}
