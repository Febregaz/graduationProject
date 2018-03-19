package yuzhaoLiu.project.controller.mybatis.announcement.announcementUtil;

import yuzhaoLiu.project.controller.methodForMany.theMethodForPage;
import yuzhaoLiu.project.mybatis.entity.announcement.Announces;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;

import java.util.List;

public class methodForManageAll {

    public static Pages ManageAnnosForPage(int pageSize, int nowPage , List<Announces> annosList) {
        int allRecords = annosList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        toIndex = theMethodForPage.methodForPage(allRecords , currentoffset , length);
        List<Announces> listAnno = annosList.subList(currentoffset , toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListAnno(listAnno);
        pagebean.init();
        return pagebean;
    }

}
