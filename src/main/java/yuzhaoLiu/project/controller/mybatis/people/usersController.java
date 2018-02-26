package yuzhaoLiu.project.controller.mybatis.people;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.mapper.people.usersMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class usersController extends topController {

    @RequestMapping("/usersLogin")
    public String usersLogin(String username, String password, HttpServletRequest request){
        logger.info("Logining..."+username+" "+password);
        usersMapper usersMapper = sqlUtil.getSql().getMapper(usersMapper.class);
        Users users = usersMapper.userLogin(username);
        if(users.getPassword().equals(password)){
            if(users.getStatus()==1){
                String message = "该账号目前处于被禁用状态!无法进行此操作！";
                request.setAttribute("tipMessage", message);
                return "home/error";
            }
            request.setAttribute("userInfo",users);
            logger.info("Where");
            return "home/index";
        }
        String message = "用户名或密码错误！";
        request.setAttribute("tipMessage", message);
        return "home/error";
    }

    @RequestMapping("/usersLogout")
    public String usersLogout(HttpServletRequest request){
        request.removeAttribute("userInfo");
        return "home/login";
    }
}
