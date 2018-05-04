<%@ page language="java" pageEncoding="UTF-8"%>
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

    <title>激活页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<div class="background">
    <div class="top_nav">
        <jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
    </div>
    <div style="margin-top: 200px;margin-bottom: 200px;">
        <a href="/users/toTheHomePageAfterActivation" style="margin: 0 auto;color: #1006F1;font-size: large;">欢迎来到sevenZero，请点击确认成功</a>
    </div>
    <div class="copyRight">
        <jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
    </div>
</div>
</body>
</html>

