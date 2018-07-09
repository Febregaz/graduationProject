<%@ page language="java"
         import="java.util.*,yuzhaoLiu.project.mybatis.entity.people.Users,java.io.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>资源管理</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/manage.css" />
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <style type="text/css">
        body,html {
            width: 1004px;
            padding: 0px;
            margin: 0px auto; /*设置居中  */
            text-align: center; /*兼容性更好  */
            font-family: "微软雅黑";
        }

        .pageNav {
            width: 550px;
            height: 30px;
            margin-top: 15px;
            margin-bottom: 5px;
            float: left;
        }

        .pageGo {
            width: 120px;
            height: 25px;
            line-height: 25px;
            margin-top: 15px;
            margin-bottom: 5px;
            float: left;
            font-size: 13px;
        }

        .pageNav a button {
            width: 30px;
            height: 25px;
            font-size: 14px;
            font-family: 微软雅黑;
            background-color: white;
            border: 1px solid silver;
        }

        .pageNav a button:HOVER {
            width: 30px;
            height: 25px;
            font-size: 14px;
            font-family: 微软雅黑;
            background-color: #A2C1DE;
            border: 1px solid silver;
        }

        .pageNav button {
            width: 30px;
            height: 25px;
            font-size: 14px;
            font-family: 微软雅黑;
            background-color: #EFF4FB;
            border: 1px solid silver;
        }

        table tr td {
            border-bottom: 1px solid silver;
            border-right: 1px solid silver;
            padding-left: 3px;
            color:red;
        }

        .annoStyle {
            width: 790px;
            margin-left: 10px;
            margin-right: 5px;
        }

        .annoStyle a:link,.annoStyle a:visited {
            color: blue;
            text-decoration: none;
        } /* 未被访问的链接 */ /*已被访问的链接 */
        .annoStyle a:hover {
            color: #2979BF;
            font-weight: bolder;
            text-decoration: none;
        } /* 鼠标指针移动到链接上 */
        .butt {
            background-color: #6699CC;
            width: 120px;
            height: 30px;
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            color: white;
            font-size: 18px;
            border: 0;
        }

        .butt:hover {
            background-color: #71AAE3;
            border: 0;
        }
    </style>
    <script type="text/javascript">
        function goPage() {
            var currentPage = parseInt($("#currentPage").val());
            var selectedPage = parseInt($("#selectPage").val());
            if (selectedPage != 0 && selectedPage != currentPage) {
                document.goPageForm.action = "announcements/manageAll?nowPage="
                    + selectedPage;
                goPageForm.submit();
            }

        }
        function checkDelete() {
            if (confirm("此操作将逻辑删除该资源，你确认要继续操作吗？")) {
                return true;
            }
        }
        function  whenCateChange(){
            var selectedCate=$("#selectCate").val();
            console.log(selectedCate);
            $.ajax({
                type: "post",
                url: "types/getAllTypesByCategoryId?categoryId="+selectedCate,
                dataType: "json",
                success: function showContent(data) {
                    var typeStr = "";
                    $("#selectType").empty();//清空列表
                    $("#selectType").append("<option value='0'>--请选择小版块--</option>");
                    for(var i=0; i<data.length; i++) {
                        typeStr = data[i];
                        $("#selectType").append("<option value='"+typeStr.id +"' >"+typeStr.name+"</option>");//向清空的列表中增加新值
                    }
                }
            });
        }
        function check () {
            var topType = $("#selectType").val();
            var topCate = $("#selectCate").val();
            if(topType == 0){
                alert("请选择大板块");
                return false;
            }
            else if(topCate == 0){
                alert(请选择小版块);
                return false;
            }
        }
    </script>
</head>
<%
    Users user = (Users) session.getAttribute("userInfo");
    if (user == null) {
        PrintWriter pw = response.getWriter();
        pw.println("<script type='text/javascript'>alert('未登录或登录已失效！请登录！');window.location.href = '"+basePath+"login.jsp';</script>");

    } else if (user.getRoleId() == 0) {
        PrintWriter pw = response.getWriter();
        pw.println("<script type='text/javascript'>alert('权限不够！切换账号登录');window.location.href = '"+basePath+"login.jsp';</script>");
    } else {
%>
<body>
<div class="body">
    <div class="top">
        <img alt="" src="images/70logo.png" />
    </div>
    <div class="left">
        <jsp:include page="/WEB-INF/views/admin/left.jsp"></jsp:include>
    </div>
    <div class="right">
        <div align="center" style="font-size: 24px;margin-top: 10px;">资
            源 管 理</div>
        <form action="users/uploadResources" enctype="multipart/form-data"
              method="POST" onsubmit="return check()">
        <div style="float: left;width:100px;margin-left: 10px;">
            <input type="file" name="file" value="选择" class="butt" style="width: 80px;"/>
        </div>
        <div style="float: left;width:100px;margin-left: 10px;">
            <input type="text" name="resourceName" placeholder="请输入资源名称" class="butt" style="width: 120px;"/>
        </div>
        <div style="float: left;width:100px;margin-left: 30px;">
            <select id="selectCate" class="butt" style="width: 120px;" onchange="whenCateChange();">
                <option value="0">--请选择大版块--</option>
                <c:forEach items="${listCate}" var="cate">
                    <option value='<c:out value="${cate.id}" />'>
                        <c:out value="${cate.namee}" />
                    </option>
                </c:forEach>
            </select>
        </div>
        <div style="float: left;width:100px;margin-left: 30px;">
            <select id="selectType" class="butt" style="width: 120px;" name="typeId">
                <option value="0">--请选择小板块--</option>
            </select>
        </div>
        <div style="float: left;width:100px;margin-left: 30px;">
            <input type="submit" value="上传资源" class="butt" style="width: 80px;"/>
        </div>
        </form>
        <div class="pageNav" align="right">
            <c:if test="${pageBean.totalPages == 1}">
                <button disabled="disabled" style="width: 80px;">上一页</button>
                <button disabled="disabled">1</button>
                <button disabled="disabled" style="width: 80px;">下一页</button>
            </c:if>

            <c:if test="${pageBean.totalPages == 2}">
                <c:if test="${pageBean.currentPage==1}">
                    <button disabled="disabled" style="width: 80px;">上一页</button>
                    <button disabled="disabled">1</button>
                    <a
                            href="announcements/manageAll?nowPage=2"><button>2</button>
                    </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
                            style="width: 80px;">下一页</button> </a>
                </c:if>
                <c:if test="${pageBean.currentPage==2}">
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
                            style="width: 80px;">上一页</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=1"><button>1</button>
                    </a>
                    <button disabled="disabled">2</button>
                    <button disabled="disabled" style="width: 80px;">下一页</button>
                </c:if>
            </c:if>
            <c:if test="${pageBean.totalPages == 3}">
                <c:if test="${pageBean.currentPage==1}">
                    <button disabled="disabled" style="width: 80px;">上一页</button>
                    <button disabled="disabled">1</button>
                    <a
                            href="announcements/manageAll?nowPage=2"><button>2</button>
                    </a>
                    <a
                            href="announcements/manageAll?nowPage=3"><button>3</button>
                    </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
                            style="width: 80px;">下一页</button> </a>
                </c:if>
                <c:if test="${pageBean.currentPage==2}">
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
                            style="width: 80px;">上一页</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=1"><button>1</button>
                    </a>
                    <button disabled="disabled">2</button>
                    <a
                            href="announcements/manageAll?nowPage=3"><button>3</button>
                    </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
                            style="width: 80px;">下一页</button> </a>
                </c:if>
                <c:if test="${pageBean.currentPage==3}">
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
                            style="width: 80px;">上一页</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=1"><button>1</button>
                    </a>
                    <a
                            href="announcements/manageAll?nowPage=2"><button>2</button>
                    </a>
                    <button disabled="disabled">3</button>
                    <button disabled="disabled" style="width: 80px;">下一页</button>
                </c:if>
            </c:if>
            <c:if test="${pageBean.totalPages > 3}">
                <c:if test="${pageBean.currentPage==1}">
                    <button disabled="disabled" style="width: 80px;">上一页</button>
                    <button disabled="disabled">1</button>
                    <a
                            href="announcements/manageAll?nowPage=2"><button>2</button>
                    </a>
                    <a><button disabled="disabled">...</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
                        <c:out value="${page.totalPages}"></c:out>
                    </button> </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
                            style="width: 80px;">下一页</button> </a>
                </c:if>
                <c:if test="${pageBean.currentPage<pageBean.totalPages&&pageBean.currentPage!=1}">
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
                            style="width: 80px;">上一页</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=1"><button>1</button>
                    </a>
                    <c:if test="${pageBean.currentPage>2}">
                        <a><button disabled="disabled">...</button> </a>
                    </c:if>
                    <button disabled="disabled">
                        <c:out value="${pageBean.currentPage}"></c:out>
                    </button>
                    <c:if test="${pageBean.currentPage<pageBean.totalPages-1}">
                        <a><button disabled="disabled">...</button> </a>
                    </c:if>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
                        <c:out value="${pageBean.totalPages}"></c:out>
                    </button> </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
                            style="width: 80px;" style="width: 80px;">下一页</button> </a>
                </c:if>
                <c:if test="${pageBean.currentPage==pageBean.totalPages}">
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
                            style="width: 80px;">上一页</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=1"><button>1</button>
                    </a>
                    <a><button disabled="disabled">...</button> </a>
                    <a
                            href="announcements/manageAll?nowPage=<c:out value="${page.currentPage-1}" />"><button>
                        <c:out value="${pageBean.totalPages-1}"/>
                    </button> </a>
                    <button disabled="disabled">
                        <c:out value="${pageBean.totalPages}"/>
                    </button>
                    <button disabled="disabled" style="width: 80px;">下一页</button>
                </c:if>
            </c:if>
        </div>
        <div class="pageGo" align="right">
            <form action="anno_ManageAll.action" method="post" name="goPageForm">
                <input type="text" id="currentPage"
                       value="<c:out value="${pageBean.currentPage}" />"
                       style="display: none"> 第 <select onchange="goPage();"
                                                        id="selectPage"
                                                        style="width:70px;height:24px;border-radianno:0;border: 1px solid silver;">
                <option value="0">请选择</option>
                <%
                    int i = 1;
                %>
                <c:forEach begin="1" end="${pageBean.totalPages}">
                    <option value="<%=i%>"><%=i%></option>
                    <%
                        i++;
                    %>
                </c:forEach>
            </select> 页
            </form>
        </div>
        <table class="annoStyle" cellspacing="0">
            <tr style="background-color: #A2C1DE;height: 24px;">
                <td width="400px">名称</td>
                <td width="150px">时间</td>
                <td>操作</td>
                <td>管理</td>
            </tr>
            <c:forEach items="${resourcesList}" var="resources">
                <tr style="height:26px">
                    <td style="border-left: 1px solid silver;"><div
                            style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width: 400px;">
                        <c:out value="${resources.resourcesName}" />
                    </div></td>
                    <td><fmt:formatDate value="${resources.publishTime}" type="date"/>
                    </td>
                    <td style="font-size: 12px;color: blue;"><a
                            href="/resources/niceOrNot?resourcesId=<c:out value="${resources.resourcesId}" />"
                            target="_top"><c:if test="${resources.niceResources==0}">加精</c:if><c:if test="${resources.niceResources==1}">去精</c:if></a></td>
                    <td><a href="/resources/deleteOrNot?resourcesId=<c:out value="${resources.resourcesId}" />" style="color: silver;" onclick="return checkDelete()"><c:if test="${resources.resourceStatus==0}">删除</c:if><c:if test="${resources.resourceStatus==1}">恢复</c:if></a>
                    </td>
                </tr>

            </c:forEach>
        </table>

    </div>
</div>

</body>
<%
    }
%>
</html>
