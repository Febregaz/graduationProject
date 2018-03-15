package yuzhaoLiu.project.controller.mybatis.people.peopleUtil;

import yuzhaoLiu.project.controller.methodForMany.theMethodForPage;
import yuzhaoLiu.project.controller.mybatis.topic.topicUtil.getCommentsMapper;
import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;

import java.util.List;

public class methodForGetUserComments {

    public static Pages getCommentsForPages(int pageSize, int nowPage, int id) {
        List<Comments> commentsList = getCommentsMapper.getTheCommentsMapper().getTheCommentsByUserId(id);
        int allRecords = commentsList.size();
        int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
        final int currentoffset = Pages.currentPage_startRecord(pageSize,
                nowPage);// 当前页的开始记录
        final int length = pageSize;
        final int currentPage = Pages.judgeCurrentPage(nowPage);
        int toIndex = 0;
        toIndex = theMethodForPage.methodForPage(allRecords , currentoffset , length);
        List<Comments> subListComments = commentsList.subList(currentoffset,
                toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListComments(subListComments);
        pagebean.init();
        return pagebean;
    }

}
