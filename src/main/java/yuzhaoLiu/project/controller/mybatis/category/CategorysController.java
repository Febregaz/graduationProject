package yuzhaoLiu.project.controller.mybatis.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.methodForGoCategory;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategorysController extends topController {

    private Pages pageBean;

    @RequestMapping("/getAllCategory")
    public String getAllCategory(HttpServletRequest request){
        logger.info("I am good and in getAllCategory");
        List<Categorys> categorysList = getCategoryMapper.getTheCategorysMapper().readCategorys();
        request.setAttribute("listCate" , categorysList);
        return "topic/newTopic";
    }

    @RequestMapping("/goCategory")
    public String goCategory(int categoryId , int nowPage , HttpServletRequest request){
        Categorys category = getCategoryMapper.getTheCategorysMapper().getCategoryById(categoryId);
        List<Types> typesList = getTypeMapper.getTheTypesMapper().getAllTypesByCategoryId(categoryId);
        pageBean = methodForGoCategory.getAllForPages(10 , nowPage , typesList.get(0).getId()/*只获取第一个类型的所有帖子*/);
        List<Topics> topicsList = pageBean.getListTopics();
        request.setAttribute("category" , category);
        request.setAttribute("listType" , typesList);
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listTopic" , topicsList);
        return "category/cate";
    }

    @RequestMapping("/getAllCategoryAndGoTypeJsp")
    public String getAllCategoryAndGoTypeJsp(HttpServletRequest request){
        List<Categorys> categorysList = getCategoryMapper.getTheCategorysMapper().readCategorys();
        request.setAttribute("listCate" , categorysList);
        return "category/type";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request){
        List<Categorys> categorysList = getCategoryMapper.getTheCategorysMapper().readCategorys();
        request.setAttribute("listCate" , categorysList);
        return "admin/manageCates";
    }

    @RequestMapping("/updateCategoryName")
    public String updateCategoryName(String categoryName , int categoryId){
        logger.info("categoryId:"+categoryId);
        logger.info("categoryName:"+categoryName);
        Categorys category = getCategoryMapper.getTheCategorysMapper().getCategoryById(categoryId);
        category.setNamee(categoryName);
        getCategoryMapper.getTheCategorysMapper().updateCategoryName(category);
        getCategoryMapper.sqlCommit();
        return "redirect:manageAll";
    }

    @RequestMapping("/addCategory")
    public String addCategory(String categoryName){
        Categorys category = new Categorys();
        category.setNamee(categoryName);
        getCategoryMapper.getTheCategorysMapper().addCategory(category);
        getCategoryMapper.sqlCommit();
        return "redirect:manageAll";
    }

}
