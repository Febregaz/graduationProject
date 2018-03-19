package yuzhaoLiu.project.controller.mybatis.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/types")
public class typesController extends topController {

    @RequestMapping("/getAllTypesByCategoryId")
    @ResponseBody
    public List<Types> getAllTypesByCategoryId(int categoryId){
        logger.info("I am good and in getAllTypesByCategoryId");
        List<Types> listType = getTypeMapper.getTheTypesMapper().getAllTypesByCategoryId(categoryId);
        return listType;
    }

    @RequestMapping("/getAllTypesByCategoryIdAndToTypeCate")
    public String getAllTypesByCategoryIdAndToTypeCate(int categoryId , HttpServletRequest request){
        logger.info("I am good and in getAllTypesByCategoryIdAndToTypeCate");
        List<Types> listType = getTypeMapper.getTheTypesMapper().getAllTypesByCategoryId(categoryId);
        request.setAttribute("listType" , listType);
        return "category/typeCate";
    }

    @RequestMapping("/updateTypeName")
    public String updateTypeName(String typeName , int typeId){
        logger.info(typeId);
        Types type = getTypeMapper.getTheTypesMapper().getTypeById(typeId);
        type.setName(typeName);
        getTypeMapper.getTheTypesMapper().updateTypeName(type);
        getTypeMapper.sqlCommit();
        return "redirect:/category/manageAll";
    }

    @RequestMapping("/addType")
    public String addType(String typeName , int cateId){
        logger.info(typeName+" "+cateId);
        Categorys category = getCategoryMapper.getTheCategorysMapper().getCategoryById(cateId);
        Types type = new Types();
        type.setName(typeName);type.setTypesCategory(category);
        getTypeMapper.getTheTypesMapper().addType(type);
        getTypeMapper.sqlCommit();
        return "redirect:/category/manageAll";
    }

}
