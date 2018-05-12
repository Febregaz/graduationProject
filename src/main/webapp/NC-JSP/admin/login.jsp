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

<title>登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
if(self.location!=top.location) { top.location.href=self.location.href;}
</script>
</head>

<body>
	<div class="background">
		<div class="top_nav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="tbody">
			<div class="login">
				<form action="users/usersLogin" method="post"
					onsubmit="return login();" id="loginForm">
					<br /> <br />
					<h2 align="left" style="margin-left: 50px;">用户登录</h2>
					<br />
					<table style="font-size: 18px;" align="left">
						<tr>
							<td style="text-align: right;width: 220px;">帐&nbsp;号：</td>
							<td><input type="text" name="username" id="uName" placeholder="用户名，昵称，邮箱"
								<%--maxlength="8"撤销字符数限制--%> <%--onfocus="warnName()"--%> onblur="return checkname()" />
							</td>
							<td id="namets"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>

						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 220px;">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
							<td><input type="password" name="password" id="uPass"
								maxlength="16" onfocus="warnPass()" onblur="return checkpass();" />
							</td>
							<td id="passts"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="登录" class="butt" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="button" value="注册"
								onclick="window.location.href='NC-JSP/home/register.jsp'" class="butt">
							</td>
							<td></td>
					</table>
				</form>
			</div>
			<div class="others"></div>
		</div>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
