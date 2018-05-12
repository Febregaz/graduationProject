package yuzhaoLiu.project.mybatis.mapper.download;

import yuzhaoLiu.project.mybatis.entity.download.Resources;

import java.util.List;

public interface resourcesMapper {

    public List<Resources> getAllResources();

    public void addResources(Resources resource);

    public Resources getResourcesById(int id);

    public void updateResourcesNice(Resources resource);

    public void updateResourcesStatus(Resources resource);

    public void updateResourcesTimes(Resources resource);

    public Resources getResourcesByURL(String URL);

}
