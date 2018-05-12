<%@ page language="java"
         import="java.util.*,yuzhaoLiu.project.neo4j.entity.Actor,java.io.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>">
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="js/top.js"></script>
<head>
<link rel="SHORTCUT ICON" href="images/logo.jpg">
</head>
<style type="text/css">
body,html {
	width: 1004px;
	padding: 0px;
	margin: 0px auto; /*设置居中  */
	text-align: center; /*兼容性更好  */
	background-color: #F8F8F8;
	border: none;
}

a {
	text-decoration: none;
}

a:link {
	color: black;
	text-decoration: none;
} /* 未被访问的链接 */
a:visited {
	color: black;
} /* 已被访问的链接 */
a:hover {
	text-decoration: underline;
	color: #67ADF2;
} /* 鼠标指针移动到链接上 */
a:active {
	color: black;
	text-decoration: underline;
}

a img {
	border: none;
}

.top {
	width: 1004px;
	height: 150px;
	background-color: white;
}

.search {
	width: 284px;
	height: 40px;
	margin-top: 3px;
	line-height: 40px;
	float: left;
	background-color: #6699CC;
}

#nav {
	width: 720px;
	height: 40px;
	margin-top: 3px;
	float: left;
	background-color: #6699CC;
}

#nav ul {
	margin: 0;
	padding: 0;
	list-style: none;
	list-style-type: none;
}

#nav ul li {
	position: relative;
	width: 110px;
	background: #6699CC;
	float: left;
	text-align: center;
	font-weight: bold;
	font-size: 18px;
	font-weight: bold
}

#nav ul li a { /* border-right: 1px solid #e9e9e9; */
	color: white;
	height: 40px;
	line-height: 40px;
	text-decoration: none;
	display: block;
	text-align: center;
	overflow: hidden;
	text-decoration: none;
}

#nav ul li a:hover {
	font-weight: bold;
	background: #71AAE3;
}

#nav ul li ul {
	display: none;
}

#nav ul li:hover ul {
	display: block;
	position: absolute;
	min-width: 190px;
	left: 0;
}

#nav ul li:hover ul li a {
	display: block;
	background: #6699CC;
	color: white;
	width: 110px;
	text-align: center;
	border-right: none;
}

#nav ul li:hover ul li a:hover {
	background: #71AAE3;
	color: white;
}
</style>
<script type="text/javascript">
	function checkLogin() {
		var msg = '<c:out value="${sessionScope.userInfo.nickname}"></c:out>';
		if (!msg) {
			var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
			if (returnVal) {
				location.href = 'NC-JSP/home/login.jsp';
			}
			return false;
		}
		return true;
	}
	function checkContent() {
		var searchContent = $("#searchContent").val();
		content = searchContent.replace(/(&nbsp;)|\s|\u00a0/g, '');
		if (content == "" || content == null) {
			alert("请输入查找内容！");
			return false;
		}
		return true;
	}
</script>
<script type="text/javascript" language="javascript">
	//加入收藏
	function AddFavorite(sURL, sTitle) {
		sURL = encodeURI(sURL);
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
			} catch (e) {
				alert("加入收藏失败，请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
			}
		}
	}
	//设为首页
	function SetHome(url) {
		if (document.all) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(url);
		} else {
			alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
		}
	}
	function toDesktop(sUrl, sName) {
		try {
			var WshShell = new ActiveXObject("WScript.Shell");
			var oUrlLink = WshShell.CreateShortcut(WshShell
					.SpecialFolders("Desktop")
					+ "\\" + sName + ".url");
			oUrlLink.TargetPath = sUrl;
			oUrlLink.Save();
		} catch (e) {
			alert("当前浏览器安全级别不允许操作！");
		}
	}
</script>
<div class="top">
	<div
		style="height: 30px;width:1004px;line-height: 30px;background-color: #F8F8F8"
		align="center">
		<div
			style="width: 580px;height: 30px;float: left;text-align:left;font-size: 12px;font-family: 微软雅黑">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; <a
				onclick="SetHome(window.location)" href="javascript:void(0)">设为首页</a>
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
				onclick="AddFavorite(window.location,document.title)"
				href="javascript:void(0)">收藏本站</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
				onClick="toDesktop('http://giun.vxg197.10000net.cn','DS-Java论坛')"
				name="btn" id="btn" class="font-color" style="cursor:pointer">将本站放到桌面</a>
			&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
				name="onLine" id="onLine" class="font-color" style="color: #aa1111;text-decoration:none;/*超链接无下划线*/
}">当前在线人数为<%=application.getAttribute("onLine")%></a>
		</div>
		<c:if test="${sessionScope.userInfo==null}">
			<div
				style="width: 300px;height: 30px;line-height: 30px;float: left;text-align: right;font-weight: bold;font-size: 16px;font-family: 微软雅黑 /* background-color: red; */"
				id="loginRegisterText">
				<a href="NC-JSP/home/login.jsp">登录</a>&nbsp;&nbsp;<a href="NC-JSP/home/register.jsp">注册</a>
			</div>
		</c:if>
		<c:if test="${sessionScope.userInfo!=null}">
			<div
				style="width: 300px;height: 30px;line-height: 30px;float: left;text-align: right;font-weight: bold;font-size: 16px;font-family: 微软雅黑/* background-color: red; */">
				<font color="#6699CC"><c:out value="${sessionScope.userInfo.nickname}"></c:out> </font>
				<c:if test="${sessionScope.userInfo.clock==0}"></c:if>
				<c:if test="${sessionScope.userInfo.clock>0}">
					<sup style="color: red;font-size: 14px;"><c:out value="${sessionScope.userInfo.clock}"></c:out></sup>
				</c:if>
				[ <a href="users/usersLogout" onclick="return logout()"
					style="color:red">安全退出</a> ]
			</div>
		</c:if>
		<div
			style="width: 120px;height: 30px;line-height: 30px;float: left;font-weight: bold;text-align: center;font-size: 16px;font-family: 微软雅黑">
			<a href="NC-JSP/user/home.jsp" onclick="return checkLogin()">个人中心</a>
		</div>
	</div>
	<div>
		<img id="logoImg" src="images/70logo.png" />
	</div>
</div>
<div id="nav" align="center">
	<ul id="menuNav">
		<li><a href="NC-JSP/home/index.jsp">首页</a>
		</li>
		<li><a href="topics/getAllFreshTopics?nowPage=1">帖子</a>
			<ul>
				<li><a href="topics/getAllHotTopics?nowPage=1" target="_top">热门帖子</a></li>
				<li><a href="topics/getAllNiceTopics?nowPage=1" target="_top">精品帖子</a>
				</li>
			</ul>
		</li>
		<li><a href="category/getAllCategoryAndGoTypeJsp">版块</a>
		</li>

		<li><a onfocus="this.blur()" href="/resources/getAllResources?nowPage=1">资源下载</a></li>
	</ul>
</div>
<div class="search" align="left">
	<form action="topics/searchTopics?nowPage=1" method="post"
		onsubmit="return checkContent();">
		<input type="text" name="content" id="searchContent"
			style="width:180px;height:22px;font-size:12px;background-color: white;border: 0;padding-left: 6px;" />
		<input type="submit" value="Search"
			style="background-color: white;font-size:12px;height:22px;border: 0;color: #6699CC;font-weight: bolder;" />
	</form>
</div>

