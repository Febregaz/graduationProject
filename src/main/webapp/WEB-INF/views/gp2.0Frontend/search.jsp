<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<head>
    <base href="<%=basePath%>">

    <title>Black &amp; White</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- meta -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- css -->
    <link rel="stylesheet" href="newCss/bootstrap.min.css">
    <link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="newCss/pace.css">
    <link rel="stylesheet" href="newCss/custom.css">

    <!-- js -->
    <script src="newJs/jquery-2.1.3.min.js"></script>
    <script src="newJs/bootstrap.min.js"></script>
    <script src="newJs/pace.min.js"></script>
    <script src="newJs/modernizr.custom.js"></script>
</head>

<body>
<div class="container">
    <header id="site-header">
        <div class="row">
            <div class="col-md-4 col-sm-5 col-xs-8">
                <div class="logo">
                    <h1><a href="/topics/toNewHome"><b>Seven</b> &amp; Zero</a></h1>
                </div>
            </div><!-- col-md-4 -->
            <div class="col-md-8 col-sm-7 col-xs-4">
                <nav class="main-nav" role="navigation">
                    <div class="navbar-header">
                        <button type="button" id="trigger-overlay" class="navbar-toggle">
                            <span class="ion-navicon"></span>
                        </button>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="cl-effect-11"><a href="http://www.617museum.top/NC-JSP/home/login.jsp" target="_blank" data-hover="禁止入内">禁止入内</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
                <div id="header-search-box">
                    <a id="search-menu" href="#"><span id="search-icon" class="ion-ios-search-strong"></span></a>
                    <div id="search-form" class="search-form">
                        <form role="search" method="get" id="searchform" action="#">
                            <input type="search" placeholder="搜索" required>
                            <button type="submit"><span class="ion-ios-search-strong"></span></button>
                        </form>
                    </div>
                </div>
            </div><!-- col-md-8 -->
        </div>
    </header>
</div>

<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12 newest">
                <c:forEach items="${listTopic}" var="test">
                <div style="border:1px solid #666666;background:#eef3f7">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title">
                            <a href="javascript:void(0)" onclick="location.reload()"><c:out value="${test.title}"></c:out></a>
                        </h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#"><c:out value="${test.topicsType.typesCategory.namee}"></c:out>/<c:out value="${test.topicsType.name}"></c:out></a></span>
                            <span class="post-date"><a href="#"><time class="entry-date"
                                                                      datetime="2012-11-09T23:15:57+00:00"><fmt:formatDate value="${test.topicTime}" type="date"/></time></a></span>
                            <span class="post-author"><a href="#"><c:out value="${test.topicsUser.nickname}"></c:out></a></span>
                            <span class="comments-link"><a href="#"><c:out value="${test.countComment}"></c:out> 评论</a></span>
                            <span class="views-count"><a href="#"></a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <div class="read-more cl-effect-14">
                            <a href="/617/newDetail<c:out value="${test.id}"></c:out>.617museum" class="more-link">继续阅读 <span class="meta-nav">→</span></a>
                        </div>
                    </div>
                </article>
                </div>
                </c:forEach>
            </main>
        </div>
    </div>
</div>

<script src="newJs/script.js"></script>
</body>
</html>
