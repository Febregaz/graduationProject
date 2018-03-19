package yuzhaoLiu.project.controller.mybatis.help.helpUtil;

import yuzhaoLiu.project.controller.methodForMany.theMethodForPage;
import yuzhaoLiu.project.mybatis.entity.help.Helps;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;

import java.util.List;

public class methodForManageAll {

    public static Pages ManageHelpsForPage(int pageSize, int nowPage , List<Helps> helpsList) {
        int allRecords = helpsList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        toIndex = theMethodForPage.methodForPage(allRecords , currentoffset , length);
        List<Helps> listHelp = helpsList.subList(currentoffset , toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListHelp(listHelp);
        pagebean.init();
        return pagebean;
    }

}
