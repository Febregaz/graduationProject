package yuzhaoLiu.project.controller.mybatis.people.peopleUtil;

import yuzhaoLiu.project.controller.methodForMany.theMethodForPage;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.List;

public class methodForGetUserTopics {

    public static Pages getTopicsForPages(int pageSize, int nowPage, int id) {
        List<Topics> topicsList = getTopicsMapper.getTheTopicsMapper().getTopicsByUserId(id);
        int allRecords = topicsList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        toIndex = theMethodForPage.methodForPage(allRecords , currentoffset , length);
        List<Topics> subListTopics = topicsList.subList(currentoffset,
                toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListTopics(subListTopics);
        pagebean.init();
        return pagebean;
    }

}
