<%@ page language="java"
		 import="java.util.*,yuzhaoLiu.project.mybatis.entity.people.Users,java.io.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head><link rel="SHORTCUT ICON" href="images/logo.jpg"></head>
<link rel="stylesheet" type="text/css" href="css/manager_left.css">
<%
	Users user = (Users) session.getAttribute("userInfo");
	if (user.getRoleId() == 14) {
%>
<div class="links">
	<a href="users/manageAll?nowPage=1"><span
			style=" line-height:50px;"><font color="white">用 户 管 理</font> </span>
	</a>
</div>
<%
	}
%>
<div class="links">
	<a href="topics/manageAll?nowPage=1"><span
			style=" line-height:50px;"><font color="white">帖 子 管 理</font> </span>
	</a>
</div>
<div class="links">
	<a href="category/manageAll"><span style=" line-height:50px;"><font
			color="white">版 块 管 理</font> </span> </a>
</div>
<div class="links">
	<a href="announcements/manageAll?nowPage=1"><span
			style=" line-height:50px;"><font color="white">公 告 管 理</font> </span>
	</a>
</div>
<div class="links">
	<a href="helps/manageAll?nowPage=1"><span style="line-height:50px;"><font
			color="white">帮 助 管 理</font> </span> </a>
</div>
<div class="links">
	<a href="<%--resources/manageAll?nowPage=1--%>javascript:return false;"><span style="line-height:50px;opacity: 0.2;"><font
			color="white">资 源 管 理</font> </span> </a>
</div>
<div class="links">
	<a href="category/getAllCategory"><span style="line-height:50px;"><font
			color="white">发新文章</font> </span> </a>
</div>