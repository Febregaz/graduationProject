package yuzhaoLiu.project.controller.mybatis.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategorysController extends topController {

    @RequestMapping("/getAllCategory")
    public String getAllCategory(HttpServletRequest request){
        logger.info("I am good and in getAllCategory");
        List<Categorys> categorysList = getCategoryMapper.getTheCategorysMapper().readCategorys();
        request.setAttribute("listCate" , categorysList);
        return "topic/newTopic";
    }

}
