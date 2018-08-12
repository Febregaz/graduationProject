package yuzhaoLiu.project.mybatis.mapper.calendar;

import yuzhaoLiu.project.mybatis.entity.calendar.Plan;

import java.util.List;

public interface planMapper {

    public void addPlan(Plan plan);

    public List<Plan> getAll();

    public Plan byID(int id);

    public void updateStatus(Plan plan);
}
