<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>sevenZero</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        function checkLogin() {
            var msg = '<c:out value="${sessionScope.userInfo.nickname}"></c:out>';
            if (!msg) {
                var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
                if (returnVal) {
                    location.href = 'NC-JSP/home/login.jsp';
                }
                return false;
            }
            return true;
        }
    </script>
    <style type="text/css">

        .btn {
            border: none;
            border-radius: 0;
            min-width: 80px;
            height: 28px;
            line-height: 16px;
            color: #fff;
        }

        .btn-top {
            width: 40px;
            height: 40px;
            background-color: #ccc;
        }

        .btn-top:hover,.btn-top:focus {
            background-color: #676767;
        }

        .btn-top .glyphicon-chevron-up .glyphicon-share-alt {
            font-size: 18px;
        }

        .glyphicon {
            position: relative;
            top: 1px;
            display: inline-block;
            font-style: normal;
            font-weight: normal;
            line-height: 1;
        }

        .glyphicon-share-alt:before {
            content: "回复";
        }

        .glyphicon-chevron-up:before {
            content: "顶部";
        }
    </style>
</head>

<body>
<div class="background">
    <div class="topNav">
        <jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
    </div>
    <div class="leftBodyBlank"></div>
    <div class="tBody">
        <div class="topBody" align="left">
            <div style="float: left;">
                <a href="NC-JSP/home/index.jsp" title="论坛首页"><img
                        src="images/homepage_24.png"/> </a>
            </div>
            <div style="float: left;line-height:24px;">&nbsp;&gt;&nbsp;论坛
            </div>
        </div>
        <div class="leftBody">
            <div class="classics">
                <div class="ltitle">
                    <div style="float: left;width: 660px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;精帖榜&nbsp;&nbsp;&nbsp;&nbsp;<font
                            style="font-style: italic;">TOP 10 </font>
                    </div>
                    <div align="center" style="float: left;width: 80px;color: #6699CC">
                        <a href="/617/Ashe1.617museum">More&gt;&gt;</a>
                    </div>
                </div>
                <div class="classicsContent">
                    <iframe width="740px" height="335px" frameborder=0 scrolling="no"
                            src="/topics/oldGetTheNicestTopics"></iframe>
                </div>
            </div>
            <div class="fresh">
                <div class="ltitle">
                    <div style="float: left;width: 660px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新帖榜&nbsp;&nbsp;&nbsp;&nbsp;<font
                            style="font-style: italic;">TOP 10 </font>
                    </div>
                    <div align="center" style="float: left;width: 80px;">
                        <a href="/617/Blitzcrank1.617museum">More&gt;&gt;</a>
                    </div>
                </div>
                <div class="freshContent">
                    <iframe width="740px" height="335px" frameborder="0" scrolling="no"
                            src="/topics/oldGetTheNewestTopics"></iframe>
                </div>
            </div>
            <div class="hot">
                <div class="ltitle">
                    <div style="float: left;width: 660px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;热帖榜&nbsp;&nbsp;&nbsp;&nbsp;<font
                            style="font-style: italic;">TOP 10 </font>
                    </div>
                    <div align="center" style="float: left;width: 80px;color: #6699CC">
                        <a href="/617/Caitlyn1.617museum">More&gt;&gt;</a>
                    </div>
                </div>
                <div class="hotContent">
                    <iframe width="740px" height="335px" frameborder="0" scrolling="no"
                            src="topics/getTheHotestTopics" ></iframe>
                </div>
            </div>
        </div>
        <div class="rightBody">
            <div class="announces">
                <div class="rtitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;论坛公告
                </div>
                <div class="announcesContent">
                    <iframe width="200px" height="220px" frameborder=0 scrolling="no"
                            src="announcements/getTheAnnouncements"></iframe>
                </div>
            </div>
            <c:if test="${sessionScope.userInfo!=null}">
                <div class="newButton">
                    <form action="category/getAllCategory" method="get">
                        <input type="submit" value="我要发帖">
                    </form>
                </div>
            </c:if>
            <c:if test="${sessionScope.userInfo==null}"></c:if>
            <!-- <div class="hero">
                <div class="rtitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;论坛牛人
                </div>
                <div class="announcesContent"></div>
            </div> -->
            <div class="helps">
                <div class="rtitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;论坛帮助
                </div>
                <div class="announcesContent">
                    <iframe width="200px" style="min-height: 220px;" frameborder=0
                            scrolling="no" src="helps/getTheHelps"></iframe>
                </div>
            </div>
            <div class="friendLink">
                <div class="rtitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;友情链接
                </div>
                <div class="announcesContent">
                    <iframe width="200px" height="170px" frameborder=0 scrolling="no"
                            src="NC-JSP/home/indexFriend.jsp"> </iframe>
                </div>
            </div>
        </div>
    </div>
    <div class="copyRight">
        <jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
    </div>
</div>
</body>
<script>
    (function(){
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>
</html>
