package yuzhaoLiu.project.controller;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class testController {

    private static Logger logger = Logger.getLogger(testController.class);

    @RequestMapping("/print")
    public String testPrint(HttpServletRequest request , HttpServletResponse response){
        logger.info("I am good and here !");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        request.setAttribute("testList",list);
        return "freshTopic/indexFreshTopic";
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request , HttpServletResponse response , String fileName){
        try{
            // GET请求中，参数中包含中文需要自己动手来转换。
            // 当然如果你使用了“全局编码过滤器”，那么这里就不用处理了
            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");

            String filepath = request.getSession().getServletContext().getRealPath("/WEB-INF/statics/images/" + fileName);
            File file = new File(filepath);
            if(!file.exists()) {
                response.getWriter().print("您要下载的文件不存在！");
                return;
            }
            // 所有浏览器都会使用本地编码，即中文操作系统使用GBK
            // 浏览器收到这个文件名后，会使用iso-8859-1来解码
            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            response.addHeader("content-disposition", "attachment;filename=" + fileName);
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
