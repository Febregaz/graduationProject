package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import org.apache.ibatis.session.SqlSession;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;
import yuzhaoLiu.project.mybatis.mapper.topic.content.topicsMapper;
import yuzhaoLiu.project.mybatis.util.sqlUtil;

import java.util.List;

public class methodForGetTopicsByTypeId {

    public static Pages getAllForPages(int pageSize, int nowPage, int typeId) {
        SqlSession sqlSession = sqlUtil.getSql();
        List<Topics> listTopics = sqlSession.getMapper(topicsMapper.class).getTopicsByTypeId(typeId);
        sqlUtil.sqlClose(sqlSession);
        int allRecords = listTopics.size();
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
        List<Topics> subListTopics = listTopics.subList(currentoffset,
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
