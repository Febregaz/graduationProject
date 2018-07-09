<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>联系我们</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<style type="text/css">
.background {
	width: 1004px;
	overflow: auto;
	background-color: #F5F5F5;
	margin: 0 auto;
}

.leftBodyBlank {
	width: 22px;
	min-height: 300px;
	float: left;
}

.tBody {
	width: 960px;
	margin-top: 10px;
	min-height: 400px;
	float: left;
	font-size: 14px;
	font-family: 微软雅黑;
}
</style>
</head>

<body>
	<div class="background">
		<div class="topNav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="leftBodyBlank"></div>
		<div class="tBody" style="float: left;">
			<div class="topBody" align="left">
				<div style="float: left;">
					<a href="NC-JSP/home/index.jsp" title="论坛首页"><img
						src="images/homepage_24.png" /> </a>
				</div>
				<div style="float: left;line-height:24px;">
					&nbsp;&gt;&nbsp;<a href="NC-JSP/home/index.jsp">论坛</a>&nbsp;&gt;&nbsp;联系我们
				</div>
			</div>
			<div
				style="float: left;margin-top: 5px;width: 820px;margin-left:100px;word-wrap: break-word; word-break: normal;escape:false;text-align: left;">

				<p style="text-align: center;font-size: 24px;">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:24px;font-family:'宋体'">联系我们</span>
				</p>

				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">电话：</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">18818429769&nbsp;&nbsp;</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">&nbsp;</span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">Email:</span><span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">febregazz@gmail.com</span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">地址：广东省广州市(天河区)大观中路新园新村</span>
				</p>
				<p
					style=";text-autospace:ideograph-other;text-align:left;line-height:22px">
					<span
						style=";color:rgb(0,0,0);font-weight:normal;font-style:normal;font-size:14px;font-family:'宋体'">邮编：******</span>
				</p>
				<p>
					<iframe class="ueditor_baidumap"
						src="ueditor/dialogs/map/show.html#center=113.423101,23.169872&amp;zoom=14&amp;width=530&amp;height=340&amp;markers=113.423101,23.169872&amp;markerStyles=l,A"
						frameborder="0" height="344" width="534"></iframe>
				</p>

			</div>
		</div>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
