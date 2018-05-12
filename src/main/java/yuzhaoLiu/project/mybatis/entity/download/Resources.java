package yuzhaoLiu.project.mybatis.entity.download;

import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;

import java.util.Date;

public class Resources {

    private int resourcesId;
    private String resourcesURL;
    private Types resourcesType; //跟帖子的类型，范畴共用
    private int resourcesIntegral; //下载所需积分
    private int downloadTimes; //下载次数
    private Date publishTime; //发布时间
    private int niceResources; //是否精品
    private int resourceStatus; //是否冻结或激活
    private Users resourcesUser; //发布人
    private String resourcesName; //资源名称

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public Users getResourcesUser() {
        return resourcesUser;
    }

    public void setResourcesUser(Users resourcesUser) {
        this.resourcesUser = resourcesUser;
    }

    public int getResourcesIntegral() {
        return resourcesIntegral;
    }

    public void setResourcesIntegral(int resourcesIntegral) {
        this.resourcesIntegral = resourcesIntegral;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getNiceResources() {
        return niceResources;
    }

    public void setNiceResources(int niceResources) {
        this.niceResources = niceResources;
    }

    public int getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(int resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public int getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(int resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourcesURL() {
        return resourcesURL;
    }

    public void setResourcesURL(String resourcesURL) {
        this.resourcesURL = resourcesURL;
    }

    public Types getResourcesType() {
        return resourcesType;
    }

    public void setResourcesType(Types resourcesType) {
        this.resourcesType = resourcesType;
    }
}
