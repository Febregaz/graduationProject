<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/6 0006
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>

</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <title>500</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="css/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div id="main">
    <!-- header -->
    <div id="header">
        <h1>Gone to hell, won't be back<span>500 error - error inside.</span></h1>
    </div>
    <!-- content -->
    <div id="content">
        <ul class="nav">
            <li class="home"><a href="#">Home Page</a></li>
            <li class="site_map"><a href="#">Site Map</a></li>
            <li class="search"><a href="#">Website Search</a></li>
        </ul>
        <p>This has either been a very unrighteous page or never existed at all. Anyways you'd better not know where it is now.<br /> So why don't you go to our <a href="#">homepage</a>, check out our <a href="#">sitemap</a> or try using the <a href="#">website search</a>.</p>
    </div>
    <!-- footer -->
    <div id="footer">
        Designed by TemplateMonster - all <a href="http://www.cssmoban.com" target="_blank">网页模板</a> found and safe!
    </div>
</div>
</body>

</html>

