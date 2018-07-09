package yuzhaoLiu.project.controller.mybatis.category;

import org.apache.ibatis.session.SqlSession;
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
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

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
        SqlSession sqlSession = sqlUtil.getSql();
        List<Categorys> categorysList = sqlSession.getMapper(categorysMapper.class).readCategorys();
        sqlUtil.sqlClose(sqlSession);
        request.setAttribute("listCate" , categorysList);
        return "topic/newTopic";
    }

    @RequestMapping("/goCategory")
    public String goCategory(int categoryId , int nowPage , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Categorys category = sqlSession.getMapper(categorysMapper.class).getCategoryById(categoryId);
        sqlUtil.sqlClose(sqlSession);
        SqlSession sqlSession1 = sqlUtil.getSql();
        List<Types> typesList = sqlSession1.getMapper(typesMapper.class).getAllTypesByCategoryId(categoryId);
        sqlUtil.sqlClose(sqlSession1);
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
        SqlSession sqlSession = sqlUtil.getSql();
        List<Categorys> categorysList = sqlSession.getMapper(categorysMapper.class).readCategorys();
        sqlUtil.sqlClose(sqlSession);
        request.setAttribute("listCate" , categorysList);
        return "category/type";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Categorys> categorysList = sqlSession.getMapper(categorysMapper.class).readCategorys();
        sqlUtil.sqlClose(sqlSession);
        request.setAttribute("listCate" , categorysList);
        return "admin/manageCates";
    }

    @RequestMapping("/updateCategoryName")
    public String updateCategoryName(String categoryName , int categoryId){
        logger.info("categoryId:"+categoryId);
        logger.info("categoryName:"+categoryName);
        SqlSession sqlSession = sqlUtil.getSql();
        Categorys category = sqlSession.getMapper(categorysMapper.class).getCategoryById(categoryId);
        sqlUtil.sqlClose(sqlSession);
        category.setNamee(categoryName);
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(categorysMapper.class).updateCategoryName(category);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        return "redirect:manageAll";
    }

    @RequestMapping("/addCategory")
    public String addCategory(String categoryName){
        Categorys category = new Categorys();
        category.setNamee(categoryName);
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(categorysMapper.class).addCategory(category);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        return "redirect:manageAll";
    }

}
