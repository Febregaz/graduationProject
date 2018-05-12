package yuzhaoLiu.project.controller.mybatis.download;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getTypeMapper;
import yuzhaoLiu.project.mybatis.entity.download.Resources;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.mapper.download.resourcesMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class ResourcesController {

    private static Logger logger = Logger.getLogger(ResourcesController.class);
    Pages pageBean;

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request , HttpServletResponse response , String fileName){
        try{
            // GET请求中，参数中包含中文需要自己动手来转换。
            // 当然如果你使用了“全局编码过滤器”，那么这里就不用处理了

            String filepath = request.getSession().getServletContext().getRealPath("/uploadResource/" + fileName);
            File file = new File(filepath);
            if(!file.exists()) {
                response.getWriter().print("您要下载的文件不存在！");
                return;
            }
            // 所有浏览器都会使用本地编码，即中文操作系统使用GBK
            // 浏览器收到这个文件名后，会使用iso-8859-1来解码
            String browserFileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            response.addHeader("content-disposition", "attachment;filename=" + browserFileName);
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
            Resources resource = getResourcesMapper.getTheResourcesMapper().getResourcesByURL(fileName);
            resource.setDownloadTimes(resource.getDownloadTimes()+1);
            getResourcesMapper.getTheResourcesMapper().updateResourcesTimes(resource);
            getResourcesMapper.sqlCommit();
            getResourcesMapper.sqlClose();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/getAllResources")
    public String getAllResources(HttpServletRequest request , int nowPage){
        List<Resources> resourcesList = getResourcesMapper.getTheResourcesMapper().getAllResources();
        pageBean = methodForGetAllResources.getResourcesForPages(10 , nowPage , resourcesList);
        request.setAttribute("resourcesList" , pageBean.getListResources());
        request.setAttribute("pageBean" , pageBean);
        return "download/allResources";
    }

    @RequestMapping("/manageAll")
    public String manageAll(HttpServletRequest request , int nowPage){
        List<Categorys> categorysList = getCategoryMapper.getTheCategorysMapper().readCategorys();
        List<Resources> resourcesList = getResourcesMapper.getTheResourcesMapper().getAllResources();
        pageBean = methodForGetAllResources.getResourcesForPages(10 , nowPage , resourcesList);
        request.setAttribute("resourcesList" , pageBean.getListResources());
        request.setAttribute("pageBean" , pageBean);
        request.setAttribute("listCate" , categorysList);
        return "admin/manageResources";
    }

    @RequestMapping("/niceOrNot")
    public String niceOrNot(HttpServletRequest request , int resourcesId){
        Resources resource = getResourcesMapper.getTheResourcesMapper().getResourcesById(resourcesId);
        if(resource.getNiceResources()==0){
            resource.setNiceResources(1);
        }
        else if(resource.getNiceResources()==1){
            resource.setNiceResources(0);
        }
        getResourcesMapper.getTheResourcesMapper().updateResourcesNice(resource);
        getResourcesMapper.sqlCommit();
        getResourcesMapper.sqlClose();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }

    @RequestMapping("/deleteOrNot")
    public String deleteOrNot(HttpServletRequest request , int resourcesId){
        Resources resource = getResourcesMapper.getTheResourcesMapper().getResourcesById(resourcesId);
        if(resource.getResourceStatus()==0){
            resource.setResourceStatus(1);
        }
        else if(resource.getResourceStatus()==1){
            resource.setResourceStatus(0);
        }
        getResourcesMapper.getTheResourcesMapper().updateResourcesStatus(resource);
        getResourcesMapper.sqlCommit();
        getResourcesMapper.sqlClose();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        return "redirect:"+basePath+"NC-JSP/admin/manage.jsp";
    }
}
