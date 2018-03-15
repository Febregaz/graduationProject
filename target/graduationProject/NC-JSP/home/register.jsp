<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="js/register.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>

<body>
	<div class="background">
		<div class="top_nav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="tbody">
			<div class="register">
				<form action="users/usersRegister" method="post"
					onsubmit="return register();">
					<br /> 
					<h2 align="left" style="margin-left: 50px;">用户注册</h2>
					<br />
					<table style="font-size: 18px;">
						<tr>
							<td style="text-align: right;width: 220px;">用&nbsp;户&nbsp;名<font
								color="red">*</font>：</td>
							<td><input type="text" name="username"
								onblur="return checkname()" onfocus="warnName()" id="uName" />
							</td>
							<td id="namets"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 220px;">昵&nbsp;&nbsp;&nbsp;&nbsp;称<font
								color="red">*</font>：</td>
							<td><input type="text" name="nickname" maxlength="16"
								onblur="return checknic()" onfocus="warnNic()" id="uNic" />
							</td>
							<td id="nics"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 220px;">密&nbsp;&nbsp;&nbsp;&nbsp;码<font
								color="red">*</font>：</td>
							<td><input type="password" id="uPass" name="password"
								onblur="return checkpass();" onfocus="warnPass()" />
							</td>
							<td id="passts"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 220px;">确认密码<font
								color="red">*</font>：</td>
							<td><input type="password" name="" id="uRPass"
								onblur="return checkrpass();" onfocus="warnRpass()" />
							</td>
							<td id="passrts"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td style="text-align: right;width: 220px;">邮&nbsp;&nbsp;&nbsp;&nbsp;箱<font
								color="red">*</font>：</td>
							<td><input type="text" name="email" id="uEmail" onfocus="warnEmail()"
								onblur="return checkemail();" />
							</td>
							<td id="emailts"
								style="height:20px;line-height:20px;text-align: left;font-size: 12px;"></td>
						</tr>
						<tr>
							<td style="height:20px;"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="注册" class="butt" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="button" value="登录"
								onclick="window.location.href='NC-JSP/home/login.jsp'" class="butt">
							</td>
						</tr>
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
