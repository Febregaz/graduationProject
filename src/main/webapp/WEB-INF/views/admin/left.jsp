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
	<a href="resources/manageAll?nowPage=1"><span style="line-height:50px;"><font
			color="white">资 源 管 理</font> </span> </a>
</div>
<div class="links">
	<a href="NC-JSP/home/index.jsp"><span style=" line-height:50px;"><font
			color="white">返 回 首 页</font> </span> </a>
</div>
<div style="padding: 6px;margin-left: 5px;margin-right: 5px;margin-top:40px;font-size: 14px;line-height: 20px;color: white;float: left;font-family: 微软雅黑;	border: 1px solid #C2D5E3;">
	<font color="red">注意：</font>由于评论数量众多，评论管理设置在相应的帖子页面，主要就是删除评论功能（即将评论设为被删除状态，并未正删除）。
</div>