package yuzhaoLiu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {
    @RequestMapping("/print")
    public String testPrint(){
        System.out.println("Test Successful132");
        return "rightClickTest/test";
    }
}
