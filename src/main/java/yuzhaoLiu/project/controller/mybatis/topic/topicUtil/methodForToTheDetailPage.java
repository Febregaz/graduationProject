package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.mybatis.entity.topic.content.Comments;
import yuzhaoLiu.project.mybatis.entity.topic.content.Pages;

import java.util.List;

public class methodForToTheDetailPage {

    public static Pages QueryCommentsForPage(int pageSize, int nowPage,
                                      List<Comments> listComment) {
        int allRecords = listComment.size();
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
        List<Comments> subListComment = listComment.subList(currentoffset,
                toIndex);
        Pages pagebean = new Pages();
        pagebean.setPageSize(pageSize);
        pagebean.setAllRecords(allRecords);
        pagebean.setCurrentPage(currentPage);
        pagebean.setTotalPages(totalPage);
        pagebean.setListComments(subListComment);
        pagebean.init();
        return pagebean;
    }

    /*
    * 去掉topic的html标签/将&nbsp转换为空格
    * */
    public static String ignoreTopicHtml(String msg){
        msg = msg.replaceAll("<p>","");
        msg = msg.replaceAll("</p>", "");
        return msg;
    }

    /*
    * 去掉comment的html标签
    * */
    public static List<Comments> ignoreListCommentHtml(List<Comments> listComment){
        for(Comments c : listComment){
            String msg = c.getContent();
            msg = msg.replaceAll("<[.[^>]]*>","");
            msg = msg.replaceAll(" ", "");
            c.setContent(msg);
        }
        return listComment;
    }

}
