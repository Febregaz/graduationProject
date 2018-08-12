<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
                    <h1><a href="javascript:void(0)" onclick="location.reload()"><b>Seven</b> &amp; Zero</a></h1>
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
                            <li class="cl-effect-11"><a href="http://localhost:8080/NC-JSP/home/login.jsp" target="_blank" data-hover="禁止入内">禁止入内</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>
                <div id="header-search-box">
                    <a id="search-menu" href="#"><span id="search-icon" class="ion-ios-search-strong"></span></a>
                    <div id="search-form" class="search-form">
                        <form role="search" method="post" id="searchform" action="/topics/newSearchTopics">
                            <input type="search" name="content" placeholder="搜索" required>
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
            <main class="col-md-8 newest">

            </main>
            <aside class="col-md-4">
                <div class="widget widget-recent-posts">
                    <h3 class="widget-title">精品文章</h3>
                    <ul class="nicest">

                    </ul>
                </div>
                <div class="widget widget-tag-cloud">
                    <h3 class="widget-title">标签云</h3>
                    <ul class="typeCateLabel">

                    </ul>
                </div>
            </aside>
        </div>
    </div>
</div>
<footer id="site-footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </div>
</footer>
<a href="#" id="back-top">Top</a>
<a href="#" id="back-bottom">Bottom</a>
<script src="newJs/script.js"></script>
</body>
<script src="newJs/homeRequest/getTheNewestTopics.js"></script>
<script src="newJs/homeRequest/getThe5HostestTopics.js"></script>
<script src="newJs/homeRequest/getTheLabel.js"></script>
</html>
