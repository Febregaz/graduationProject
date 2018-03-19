package yuzhaoLiu.project.controller.mybatis.people.peopleUtil;

import yuzhaoLiu.project.controller.methodForMany.theMethodForPage;
import yuzhaoLiu.project.mybatis.entity.topic.News;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;

import java.util.List;

public class methodForGetNews {

    public static Pages ManageNewsForPage(int pageSize, int nowPage , List<News> usersList) {
        int allRecords = usersList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        toIndex = theMethodForPage.methodForPage(allRecords , currentoffset , length);
        List<News> listNew = usersList.subList(currentoffset , toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListNews(listNew);
        pagebean.init();
        return pagebean;
    }

}
