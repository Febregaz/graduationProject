package yuzhaoLiu.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
