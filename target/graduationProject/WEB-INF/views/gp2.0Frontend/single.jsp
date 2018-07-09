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
<html>
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
                    <h1><a href="/topics/toNewHome"><b>Black</b> &amp; White</a></h1>
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
            <main class="col-md-12">
                <article class="post post-1">
                    <header class="entry-header">
                        <h1 class="entry-title"><c:out value="${sessionScope.topic.title}"></c:out></h1>
                        <div class="entry-meta">
                            <span class="post-category"><a href="#"><c:out value="${sessionScope.topic.topicsType.typesCategory.namee}">/</c:out>/<c:out value="${sessionScope.topic.topicsType.name}"></c:out></a></span>
                            <span class="post-date"><a href="#"><time class="entry-date"
                                                                      datetime="2012-11-09T23:15:57+00:00"><fmt:formatDate value="${sessionScope.topic.topicTime}" type="date"/></time></a></span>
                            <span class="post-author"><a href="#"><c:out
                                    value="${sessionScope.topic.topicsUser.nickname}" /></a></span>
                            <span class="comments-link"><a href="#"></a></span>
                            <span class="views-count"><a href="#"></a></span>
                        </div>
                    </header>
                    <div class="entry-content clearfix">
                        <c:if test="${sessionScope.topic.status==2}">
                            <c:out value="帖子已被删除，请浏览其它内容" />
                        </c:if>
                        <c:if test="${sessionScope.topic.status==0||sessionScope.topic.status==1}">
                            <c:out value="${sessionScope.topic.content}" default="expression" escapeXml="false" />
                        </c:if>
                    </div>
                </article>
                <section class="comment-area" id="comment-area">
                    <hr>
                    <h3>发表评论</h3>
                    <form action="newPostedComment" method="post" class="comment-form">
                        <div class="row">
                        <div class="col-md-12">
                            <label for="id_comment">评论：</label>
                            <textarea name="commentContent" id="id_comment" required placeholder="支持匿名评论"></textarea>
                            <button type="submit" class="comment-btn">发表</button>
                        </div>
                    </div>    <!-- row -->
                    </form>
                    <div class="comment-list-panel">
                        <h3>评论列表</h3>
                        <ul class="comment-list list-unstyled">
                        <c:if test="${sessionScope.topic.status==1||sessionScope.topic.status==0}">
                            <c:forEach items="${listComment}" var="comment">
                            <c:if test="${comment.status==0}">
                            <li class="comment-item">
                                <span class="nickname"><c:out value="${comment.commentsUser.nickname}" /></span>
                                <time class="submit-date" datetime="2012-11-09T23:15:57+00:00"><fmt:formatDate value="${comment.commentTime}" type="date"/></time>
                                <div class="text">
                                    <c:out value="${comment.content}" default="expression" escapeXml="false" />
                                </div>
                            </li>
                            </c:if>
                            </c:forEach>
                        </c:if>
                        </ul>
                    </div>
                </section>
            </main>
            <aside class="col-md-4">

            </aside>
        </div>
    </div>
</div>
<footer id="site-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p class="copyright">&copy 2017 - Collect from <a href="http://www.cssmoban.com/"
                                                                  target="_blank" title="模板之家">模板之家</a>
                    - Modified by <a href="http://zmrenwu.com/" title="网页模板" target="_blank">追梦人物的博客</a>
                </p>
            </div>
        </div>
    </div>
</footer>

<!-- Mobile Menu -->
<div class="overlay overlay-hugeinc">
    <button type="button" class="overlay-close"><span class="ion-ios-close-empty"></span></button>
    <nav>
        <ul>
            <li><a href="index.html">首页</a></li>
            <li><a href="full-width.html">博客</a></li>
            <li><a href="about.html">关于</a></li>
            <li><a href="contact.html">联系</a></li>
        </ul>
    </nav>
</div>

<script src="newJs/script.js"></script>
<script src="newJs/detailPageRequest/"></script>
</body>
</html>

