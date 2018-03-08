package yuzhaoLiu.project.controller.mybatis.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuzhaoLiu.project.controller.chiefController.topController;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
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

}
