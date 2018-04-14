package yuzhaoLiu.project.mybatis.mapper.people;

import yuzhaoLiu.project.mybatis.entity.people.Users;

import java.util.List;

public interface usersMapper {

    public List<Users> readUsers();

    public Users userLogin(String username);

    public void updateIntegralAndComment(Users user);

    public void updateClock(Users user);

    public void updateTopicsCount(Users user);

    public void registeruser(Users user);

    public void updateUserPic(Users user);

    public void updateUserInfo(Users user);

    public void updateUserPass(Users user);

    public Users getUserById(int id);

    public void updateUserIntegral(Users user);

    public void updateUserStatus(Users user);
}
