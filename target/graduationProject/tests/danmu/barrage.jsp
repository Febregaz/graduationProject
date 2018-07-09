<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <title>barrage</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/DPlayer.min.css">
    <script src="js/DPlayer.min.js"></script>
</head>

<body>

    <div id="dplayer" style="width: 800px;margin:0 auto;"></div><%--不设置宽度如何宽度自适应--%>

</body>
<script type="text/javascript">
    const dp = new DPlayer({
        container: document.getElementById('dplayer'),
        screenshot: true,
        video: {
            url: 'videos/love theme.mp4',
            pic: 'images/chuishi.jpg',
            thumbnails: 'images/70logo.png'
        },
        subtitle: {
            url: 'webvtt.vtt'
        },
        danmaku: {
            id: 'demo',
            api: 'https://api.prprpr.me/dplayer/'
        }
    });
</script>
</html>
