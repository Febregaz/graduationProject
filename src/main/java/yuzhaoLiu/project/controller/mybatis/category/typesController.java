package yuzhaoLiu.project.controller.mybatis.category;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/types")
public class typesController extends topController {

    @RequestMapping("/getAllTypesByCategoryId")
    @ResponseBody
    public List<Types> getAllTypesByCategoryId(int categoryId){
        logger.info("I am good and in getAllTypesByCategoryId");
        SqlSession sqlSession = sqlUtil.getSql();
        List<Types> listType = sqlSession.getMapper(typesMapper.class).getAllTypesByCategoryId(categoryId);
        sqlUtil.sqlClose(sqlSession);
        return listType;
    }

    @RequestMapping("/getAllTypesByCategoryIdAndToTypeCate")
    public String getAllTypesByCategoryIdAndToTypeCate(int categoryId , HttpServletRequest request){
        logger.info("I am good and in getAllTypesByCategoryIdAndToTypeCate");
        SqlSession sqlSession = sqlUtil.getSql();
        List<Types> listType = sqlSession.getMapper(typesMapper.class).getAllTypesByCategoryId(categoryId);
        sqlUtil.sqlClose(sqlSession);
        request.setAttribute("listType" , listType);
        return "category/typeCate";
    }

    @RequestMapping("/updateTypeName")
    public String updateTypeName(String typeName , int typeId){
        logger.info(typeId);
        SqlSession sqlSession = sqlUtil.getSql();
        Types type = sqlSession.getMapper(typesMapper.class).getTypeById(typeId);
        sqlUtil.sqlClose(sqlSession);
        type.setName(typeName);
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(typesMapper.class).updateTypeName(type);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        return "redirect:/category/manageAll";
    }

    @RequestMapping("/addType")
    public String addType(String typeName , int cateId){
        logger.info(typeName+" "+cateId);
        SqlSession sqlSession = sqlUtil.getSql();
        Categorys category = sqlSession.getMapper(categorysMapper.class).getCategoryById(cateId);
        sqlUtil.sqlClose(sqlSession);
        Types type = new Types();
        type.setName(typeName);type.setTypesCategory(category);
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(typesMapper.class).addType(type);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        return "redirect:/category/manageAll";
    }

    @RequestMapping("/deleteType")
    public String deleteType(int typeId){
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(typesMapper.class).deleteType(typeId);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        return "redirect:/category/manageAll";
    }

}
