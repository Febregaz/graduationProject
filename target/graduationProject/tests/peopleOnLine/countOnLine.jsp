<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="yuzhaoLiu.project.peopleOnLine.Counter" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    Counter CountFileHandler=new Counter();//创建对象
    int count=0;
    if(application.getAttribute("count")==null){
    count=0;
    application.setAttribute("count",new Integer(count));
    }
    count=(Integer)application.getAttribute("count");
    if(session.isNew()) ++count;
    application.setAttribute("count",count);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>count</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<p>我们的友谊海枯石烂！ 你是第&nbsp;<%=count %>&nbsp;位访客</p>
</body>
</html>
