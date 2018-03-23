package yuzhaoLiu.project.controller.mybatis.topic.topicUtil;

import yuzhaoLiu.project.controller.mybatis.people.peopleUtil.getPeopleMapper;
import yuzhaoLiu.project.mybatis.entity.people.Users;
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

    public static void QuickSort(List<Comments> list, int first, int end) {
        /* 初始化各变量 */
        int i = first;
        int j = end;
        Comments temp = list.get(i); // 用子表的第一个记录作为枢轴记录
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && temp.getFloor() <= list.get(j).getFloor())
                j--;
            list.set(i, list.get(j)); // 采用替换而不是交换的方式进行操作
            while (i < j && list.get(i).getFloor() <= temp.getFloor())
                i++;
            list.set(j, list.get(i)); // 采用替换而不是交换的方式进行操作
        }
        list.set(i, temp); // 将枢轴数值替换回L->R[i]
        if (first < i - 1)
            QuickSort(list, first, i - 1); // 对低子表进行递归
        if (i + 1 < end)
            QuickSort(list, i + 1, end); // 对高子表进行递归
    }

    /*
    * 更新用户和评论的积分
    * */
    public static void endTopic(int[] listFloor, List<Comments> listComment) {
        int integral = 0;
        for (int i = 0; i < listComment.size(); i++) {
            if (listFloor[i]!=0){
                listComment.get(i).setIntegral(listFloor[i]);
                getCommentsMapper.getTheCommentsMapper().updateCommentIntegral(listComment.get(i));
                getCommentsMapper.sqlCommit();
                System.out.println(listComment.get(i).getCommentsUser().getUsername());
                Users user = listComment.get(i).getCommentsUser();
                user.setIntegral(user.getIntegral() + listFloor[i]);
                getPeopleMapper.getTheUsersMapper().updateUserIntegral(user);
                getPeopleMapper.sqlCommit();
            }
        }
    }


}
