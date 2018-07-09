package yuzhaoLiu.project.controller.mybatis.category.categoryUtil;

import org.apache.ibatis.session.SqlSession;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getTopicsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.List;

public class methodForGoCategory {

    public static Pages getAllForPages(int pageSize, int nowPage, int typeId) {
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> listTopic = sqlSession.getMapper(topicsMapper.class).getTopicsByTypeId(typeId);
        sqlUtil.sqlClose(sqlSession);
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
