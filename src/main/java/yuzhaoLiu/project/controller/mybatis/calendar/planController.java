package yuzhaoLiu.project.controller.mybatis.calendar;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yuzhaoLiu.project.mybatis.entity.calendar.Plan;
import yuzhaoLiu.project.mybatis.mapper.calendar.planMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/plans")
public class planController {

    @RequestMapping("/addPlan")
    public String addPlan(String tTitle , String tcontent , String startDate , String endDate){
        Plan plan = new Plan();
        plan.setPlanTitle(tTitle);
        plan.setPlanContent(tcontent);
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        plan.setStatus("blue");
        SqlSession sqlSession = sqlUtil.getSql();
        sqlSession.getMapper(planMapper.class).addPlan(plan);
        sqlUtil.commit(sqlSession);
        sqlUtil.sqlClose(sqlSession);
        return "redirect:http://www.617museum.top";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Plan> getAll(HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        List<Plan> planList = sqlSession.getMapper(planMapper.class).getAll();
        HttpSession session = request.getSession();
        session.setAttribute("planList" , planList);
        return planList;
    }

    @RequestMapping("/toPlan")
    public String toPlan(int planId , HttpServletRequest request){
        SqlSession sqlSession = sqlUtil.getSql();
        Plan plan = sqlSession.getMapper(planMapper.class).byID(planId);
        HttpSession session = request.getSession();
        session.setAttribute("plan" , plan);
        return "calendar/editPlan";
    }

    @RequestMapping("/editPlan")
    public String editPlan(int planId){
        SqlSession sqlSession = sqlUtil.getSql();
        Plan plan = sqlSession.getMapper(planMapper.class).byID(planId);
        sqlUtil.sqlClose(sqlSession);
        plan.setStatus("green");
        SqlSession sqlSession1 = sqlUtil.getSql();
        sqlSession1.getMapper(planMapper.class).updateStatus(plan);
        sqlUtil.commit(sqlSession1);
        sqlUtil.sqlClose(sqlSession1);
        /*localhost:8080/www.617museum.top*/
        return "redirect:http://www.617museum.top";
    }

}
