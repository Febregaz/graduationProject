package yuzhaoLiu.project.controller.mybatis.category.categoryUtil;

import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.List;

public class methodForGoCategory {

    public static Pages getAllForPages(int pageSize, int nowPage, int typeId) {
        List<Topics> listTopic = getTopicsMapper.getTheTopicsMapper().getTopicsByTypeId(typeId);
        int allRecords = listTopic.size();
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
        List<Topics> subListTopic = listTopic.subList(currentoffset, toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListTopics(subListTopic);
        pagebean.init();
        return pagebean;
    }

}
