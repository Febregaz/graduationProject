package yuzhaoLiu.project.mybatis.mapper.help;

import yuzhaoLiu.project.mybatis.entity.help.Helps;

import java.util.List;

public interface helpsMapper {

    public List<Helps> getTheHelps();

    public List<Helps> getOneHelp(int id);

}
