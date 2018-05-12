package yuzhaoLiu.project.controller.mybatis.download;

import yuzhaoLiu.project.mybatis.entity.download.Resources;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.List;

public class methodForGetAllResources {

    public static Pages getResourcesForPages(int pageSize, int nowPage,
                                             List<Resources> resourcesList) {
        int allRecords = resourcesList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        if (allRecords >= length + currentoffset) {
            toIndex = currentoffset + length;
        } else {
            toIndex = allRecords;
        }
        List<Resources> subListResources = resourcesList.subList(currentoffset,
                toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListResources(subListResources);
        pagebean.init();
        return pagebean;
    }

}
