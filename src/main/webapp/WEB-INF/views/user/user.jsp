<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title><c:out value="${sessionScope.user.nickname}" />的资料</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<style type="text/css">
.background {
	width: 1004px;
	overflow: auto;
	background-color: #F5F5F5;
	margin: 0 auto;
	min-height: 700px;
}

.tBody {
	margin-left: 22px;
	margin-right: 22px;
	font-family: 微软雅黑;
}

.smallNav {
	width: 982px;
	height: 24px;
	margin-top: 10px;
	float: left;
	font-size: 14px;
}
</style>
</head>

<body>
	<div class="background">
		<div class="topNav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="tBody">
			<div class="smallNav" align="left">
				<div style="float: left;">
					<a href="NC-JSP/home/index.jsp" title="论坛首页"><img
						src="images/homepage_24.png"></img> </a>
				</div>
				<div style="float: left;line-height:24px;">
					&nbsp;&gt;&nbsp;<a href="NC-JSP/home/index.jsp">论坛</a>&nbsp;&gt;&nbsp;论坛帮助
				</div>
			</div>
			<div class="userStyle" style="float: left;margin-top: 50px;min-height: 380px;">
				<div align="right" style="width:300px;float: left;margin-right: 30px;">
					<img alt="我的头像" style="width: 140px;height: 200px;"
						src="<c:out value="${sessionScope.user.picture}" />">
				</div>
				<div  align="left" style="width:400px;float: left;margin-left: 30px;">
					<table>
						<tr>
							<td>昵称：</td>
							<td><c:out value="${sessionScope.user.nickname}" />
							</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><c:out value="${sessionScope.user.sex}" />
							</td>
						</tr>
						<tr>
							<td>职业：</td>
							<td><c:out value="${sessionScope.user.profession}" /></td>
						</tr>
						<tr>
							<td>现居地：</td>
							<td><c:out value="${sessionScope.user.comefrom}" /></td>
						</tr>
						<tr>
							<td>论坛等级：</td>
							<td><c:out value="${sessionScope.user.usersGrade.id}" />
							</td>
						</tr>
						<tr>
							<td>论坛头衔：</td>
							<td><c:out value="${sessionScope.user.usersGrade.honor}" /></td>
						</tr>
						<tr>
							<td>可用积分：</td>
							<td><c:out value="${sessionScope.user.integral}" />
							</td>
						</tr>
						<tr>
							<td>他的帖子数：</td>
							<td><c:out value="${sessionScope.user.topCount}" />&nbsp;&nbsp;<a href="/topics/getTopicsByUserId?userId=<c:out value="${user.id}" />&&nowPage=1" style="color: blue">前往查看&gt;&gt;</a>
							</td>
						<tr>
							<td>他的评论数：</td>
							<td><c:out value="${sessionScope.user.comCount}" />
							</td>
						</tr>
					</table>
				</div>

			</div>
		</div>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
