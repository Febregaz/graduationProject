<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

<title>个人中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
.Homebackground {
	width: 1004px;
	margin: 0 auto;
	min-height: 800px;
	background-color: #F5F5F5;
}

.topNav {
	float: left;
}

.leftBodyBlank {
	width: 22px;
	min-height: 100px;
	float: left;
}

.tBody {
	width: 960px;
	font-family: 微软雅黑;
	float: left;
}

.topBody {
	width: 960px;
	height: 24px;
	margin-top: 10px;
	float: left;
	font-size: 14px;
	font-family: 微软雅黑;
}

.userStyle {
	width: 960px;
	margin-top: 15px;
	float: left;
	font-size: 14px;
	/* border: 1px solid #C2D5E3; */
	min-height: 400px;
}

.leftBodyNav {
	width: 140px;
	max-height: 450px;
	float: left;
	padding: 0px 25px 0px 15px;
}

.leftBodyNavStyle {
	width: 100%;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	float: left;
	border-bottom: 1px solid white;
	background-color: #A2C1DE;
}

.leftBodyNavStyle:hover {
	background-color: #89ADCE;
	color: white;
}

.leftBodyNavDown {
	width: 100%;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	font-weight: bolder;
	float: left;
	color: white;
	/* border-bottom: 1px solid white; */
	background-color: #7EA5C6;
}

.rightBody {
	width: 778px;
	max-height: 450px;
	float: left;
	/* border-left: 1px solid #C2D5E3; */
}

.content {
	width: 778px;
	float: left;
	background-color: white;
}

.butt {
	background-color: #6699CC;
	width: 100px;
	height: 30px;
	border: 0;
	color: white;
	font-size: 18px;
}

.butt:hover {
	background-color: #71AAE3;
	border: 0;
}

.leftBodyNav a:LINK,.leftBodyNav a:HOVER,.leftBodyNav a:ACTIVE,.leftBodyNav a:VISITED,.leftBodyNav a:FOCUS
	{
	text-decoration: none;
}
</style>
<script type="text/javascript">
	if (self.location != top.location) {
		top.location.href = self.location.href;
	}
	function iFrameHeight(n) {
		var ifm = document.getElementById("test" + n);
		var subWeb = document.frames ? document.frames["test" + n].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
	function test_item(n) {
		var menu = document.getElementById("leftBodyNav");
		var menuli = menu.getElementsByTagName("div");
		for ( var i = 1; i <= menuli.length; i++) {
			menuli[n - 1].className = "leftBodyNavDown";
			menuli[i - 1].className = "leftBodyNavStyle";
			document.getElementById("home").style.display = 'none';//隐藏
			document.getElementById("content").style.display = 'block';//隐藏  
		}
	};
	function checkULogin() {
		var msg = '<c:out value="${sessionScope.userInfo.nickname}"/>';
		if (!msg) {
			var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
			if (returnVal) {
				location.href = 'NC-JSP/home/login.jsp';
			}
			return false;
		}
		return true;
	}
	function checkULoginAndAlert() {
        var msg = '<c:out value="${sessionScope.userInfo.nickname}"/>';
        var num = '<c:out value="${sessionScope.userInfo.clock}" />';
        if(num>=1){
            alert("消息太多的话可以设置为已读哦");
        }
        if (!msg) {
            var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
            if (returnVal) {
                location.href = 'NC-JSP/home/login.jsp';
            }
            return false;
        }
        return true;
    }
	
</script>
<script type="text/javascript">
	${document}.ready(function () {
        var msg=""+'<c:out value="${updateMessage}" />';
        if(msg!=""){
            alert(msg);
        }
    })
</script>
</head>

<body>
	<div class="Homebackground">
		<div class="topNav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="leftBodyBlank"></div>
		<div class="tBody">
			<div class="topBody" align="left">
				<div style="float: left;">
					<a href="NC-JSP/home/index.jsp" title="论坛首页"><img
						src="images/homepage_24.png"></img> </a>
				</div>
				<div style="float: left;line-height:24px;">
					&nbsp;&gt;&nbsp;<a href="NC-JSP/home/index.jsp">论坛</a>&nbsp;&gt;&nbsp;个人中心
				</div>
			</div>
			<div class="userStyle">
				<div class="leftBodyNav" id="leftBodyNav">
					<a href="users/getUserNews?nowPage=1" target="test1"
						onclick="return checkULoginAndAlert()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_1" onclick="javascript:test_item(1);">
							我的消息
							<c:if test="${sessionScope.userInfo.clock==0}"></c:if>
							<c:if test="${sessionScope.userInfo.clock>=1}">
								<sup style="color: red;font-size: 14px;"><c:out
										value="${sessionScope.userInfo.clock}" /> </sup>
							</c:if>
						</div> </a> <a href="users/getUserTopics?nowPage=1" target="test1"
						onclick="return checkULogin()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_2" onclick="javascript:test_item(2);">我的帖子</div>
					</a> <a href="users/getUserComments?nowPage=1" target="test1"
						onclick="return checkULogin()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_3" onclick="javascript:test_item(3);">我的评论</div>
					</a> <a href="NC-JSP/user/updateInfo.jsp" target="test1"
						onclick="return checkULogin()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_5" onclick="javascript:test_item(4);">修改资料</div>
					</a> <a href="NC-JSP/user/updatePass.jsp" target="test1"
						onclick="return checkULogin()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_6" onclick="javascript:test_item(5);">修改密码</div>
					</a> <a href="/617/12138<c:out value="${sessionScope.userInfo.id}" />_1.617museum" target="test1"
							onclick="return checkULogin()"><div class="leftBodyNavStyle"
							id="leftBodyNavStyle_7" onclick="javascript:test_item(6);">秘密花园</div>
					</a>
					<a href="users/usersLogout" target="_top"
						onclick="return checkULogin()"><div class="leftBodyNavStyle"
							style="color: red">安全退出</div> </a>
				</div>
				<div class="rightBody">
					<div id="home" style="background-color: white;">
						<div
							style="float: left;background-color: white;width: 50px;max-height:450px;min-height: 420px;"></div>
						<div align="left"
							style="float: left;background-color: white;width: 360px;max-height:450px;min-height: 420px;line-height: 28px;">
							<p>
								用户名： <font
									style="font-size: 20px;line-height: 30px;color: #6699CC;font-weight: bolder;">
									<c:out value="${sessionScope.userInfo.username}" /> </font><br /> 昵称：
								<c:out value="${sessionScope.userInfo.nickname}" />
								<br />性别：
								<c:out value="${sessionScope.userInfo.sex}" />
								<br />邮箱：
								<c:out value="${sessionScope.userInfo.email}" />
								<br />职业：
								<c:out value="${sessionScope.userInfo.profession}" />
								<br />现居地：
								<c:out value="${sessionScope.userInfo.comefrom}" />
								<br />用户权限：
								<c:if test="${sessionScope.userInfo.roleId==0}">普通用户</c:if>
								<c:if test="${sessionScope.userInfo.roleId==6}">普通管理员</c:if>
								<c:if test="${sessionScope.userInfo.roleId==14}">超级管理员</c:if>
								<br />论坛等级：
								<c:out value="${sessionScope.userInfo.usersGrade.id}" />
								<br />论坛头衔：
								<c:out value="${sessionScope.userInfo.usersGrade.honor}" />
								<br />可用积分：
								<c:out value="${sessionScope.userInfo.integral}" />
								<br />我的帖子数：
								<c:out value="${sessionScope.userInfo.topCount}" />
								&nbsp;&nbsp;&nbsp;&nbsp;我的评论数：
								<c:out value="${sessionScope.userInfo.comCount}" />
								<br />注册时间：
								<fmt:formatDate value="${sessionScope.userInfo.registerTime}" type="date"/>
						</div>
						<div align="left"
							style="float: left;background-color: white;width: 360px;min-height: 420px;max-height:450px">
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;<img alt="我的头像"
									style="width: 140px;height: 200px;"
									src="${pageContext.request.contextPath}/<c:out value="${sessionScope.userInfo.picture}" />">
							<p>
								个人简介：<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<c:out value="${sessionScope.userInfo.introduction}" />
						</div>

					</div>
					<div class="content" id="content" style="display: none">
						<iframe id="test1" class="no" name="test1" frameBorder="0"
							scrolling="no" width="100%" height="100%"
							onLoad="iFrameHeight(1)"></iframe>
					</div>
				</div>
			</div>
		</div>

		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
<script>
    (function(){
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>

</html>
