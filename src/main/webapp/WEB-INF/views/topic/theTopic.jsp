<%@ page language="java" import="java.util.*,yuzhaoLiu.project.mybatis.entity.people.Users"
	pageEncoding="UTF-8"%>
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

<title><c:if test="topic.status==0">[未结帖]</c:if> <c:if test="topic.status==1">[已结帖]</c:if>
	<c:out value="${topic.title}"></c:out></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript"
	src="ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script type="text/javascript" src="ueditor/Comment-ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="http://apps.bdimg.com/libs/ueditor/1.4.3.1/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="js/lazysizes.min.js"></script>
<script type="text/javascript" language="javascript">
	SyntaxHighlighter.all();
	function iFrameHeight() {
		var ifm = document.getElementById("iframepage");
		var subWeb = document.frames ? document.frames["iframepage"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	};
	function goDiv(div) {
		var a = $("#" + div).offset().top;
		$("html,body").animate({
			scrollTop : a
		}, 'slow');
	}
	function goTop() {
		$('html, body').animate({
			scrollTop : 0
		}, 'slow');
	};
	function validComment() {
		var msg = '<c:out value="${sessionScope.userInfo.nickname}"></c:out>';
		if (!msg) {
			var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
			if (returnVal) {
				location.href = 'NC-JSP/home/login.jsp';
			}
			return false;
		} else if (editor.hasContents()) {
			//console.info("评论内容存在！");
			//console.info(editor.getContent());
			if (editor.getContentLength(true) >= 5) {
				$("#content").val(editor.getContent());
				return true;
			}
			alert("评论字数不得少于5个字符！");
			return false;
		}
		alert("评论内容不能为空!");
		return false;
	}
	function reference(aid, floor, nickname) {
		editor
				.setContent(editor.getContent()
						+ "<fieldset style='font-size: 12px;border: 1px solid #CCC;word-break: break-word;word-wrap: break-word;overflow-x: hidden;margin: 0 0 5px 0;padding: 0 5px 5px;'><legend style='font-weight:bold;font-size:14px;'>引自 "
						+ floor + " 楼 " + nickname + " 的回复" + "</legend>"
						+ document.getElementById(aid).innerHTML
						+ "</fieldset><br />");
		$("#content").val(editor.getContent());
		var a = $("#newComment").offset().top;
		$("html,body").animate({
			scrollTop : a
		}, 'slow');
	}
	function goEndTopic() {
		var topic_id = $("#topicId").val();
		var topic_user_id = $("#topicUserId").val();
		var current_user_id = $("#currentUserId").val();
		if (current_user_id == "" || current_user_id == null) {
			var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
			if (returnVal) {
				location.href = 'NC-JSP/home/login.jsp';
			}
		} else if (topic_user_id == current_user_id) {
			window.location.href = "/617/Brand" + topic_id+".617museum";
		} else if (topic_user_id != current_user_id) {
			alert("您的账号不对，请切换账号登录!");
		}
	}
	function deleteComment(commentId) {
		var topic_id = $("#topicId").val();
		if (confirm("删除是不可恢复的，你确认要删除吗？")) {
			window.location.href = "/deleteComment?commentId="
					+ commentId;
			return true;
		}
		return false;
	}
	function selectComment(selectValue){
		var topic_id = $("#topicId").val();
		window.location.href = "topic_goTopic.action?comFlag="
					+ selectValue+"&&topic.id="+topic_id;
	}
	function checkNewTop() {
		var msg = '<c:out value="${sessionScope.userInfo.nickname}"></c:out>';
		if (!msg) {
			var returnVal = window.confirm("未登录或登录已失效！请登录！", "提示");
			if (returnVal) {
				location.href = 'NC-JSP/home/login.jsp';
			}
		} else {
			window.location.href = 'category/getAllCategory';
		}
	}
</script>
<style type="text/css">
.btn {
	border: none;
	border-radius: 0;
	min-width: 80px;
	height: 28px;
	line-height: 16px;
	color: #fff;
}

.btn-top {
	width: 40px;
	height: 40px;
	background-color: #ccc;
}

.btn-top:hover,.btn-top:focus {
	background-color: #676767;
}

.btn-top .glyphicon-chevron-up .glyphicon-share-alt {
	font-size: 18px;
}

.glyphicon {
	position: relative;
	top: 1px;
	display: inline-block;
	font-style: normal;
	font-weight: normal;
	line-height: 1;
}

.glyphicon-share-alt:before {
	content: "回复";
}

.glyphicon-chevron-up:before {
	content: "顶部";
}

.background {
	width: 1004px;
	overflow: auto;
	background-color: #F5F5F5;
	margin: 0 auto;
	min-height: 700px;
}

.tBody {
	margin-left: 22px;
	font-family: 微软雅黑;
}

.smallNav {
	width: 982px;
	height: 24px;
	margin-top: 10px;
	float: left;
	font-size: 14px;
}

.editMenu {
	width: 520px;
	height: 30px;
	margin-top: 15px;
	float: left;
}

.pageNav {
	width: 440px;
	height: 30px;
	margin-top: 15px;
	float: left;
}

.pageNav a button {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: white;
	border: 1px solid #C2D5E3;
}

.pageNav a button:HOVER {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #A2C1DE;
	border: 1px solid #C2D5E3;
}

.pageNav button {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #EFF4FB;
	border: 1px solid #C2D5E3;
}

.butt {
	background-color: #6699CC;
	width: 100px;
	height: 30px;
	margin-right: 10px;
	float: left;
	border: 0;
	color: white;
	font-size: 18px;
}

.butt:hover {
	background-color: #71AAE3;
	border: 0;
}

.titleStyle {
	width: 960px;
	height: 40px;
	line-height: 40px;
	margin-top: 15px;
	background-color: #A2C1DE;
	float: left;
	text-align: left;
}

.topicStyle {
	float: left;
	width: 958px;
	min-height: 300px;
	background-color: #EFF4FB;
	border: 1px solid #C2D5E3;
}

.newComment {
	margin-top: 10px;
	float: left;
	width: 960px;
	height: 300px;
	background-color: white;
	border: 1px solid #C2D5E3;
	border-right: none;
	float: left;
}

.userInfo {
	width: 190px;
	height: 300px;
	float: left;
	background-color: #EFF4FB;
}

.commentInfo {
	float: left;
	width: 958px;
	/* 	min-height: 50px; */
	background-color: #EFF4FB;
	border: 1px solid #C2D5E3;
	font-family: 微软雅黑;
}

.commentUserInfo {
	width: 190px;
	height: 280px;
	float: left;
	background-color: #EFF4FB;
}

.theComment {
	text-align: left;
	width: 728px;
	min-height: 260px;
	float: left;
	padding: 10px 20px 20px 20px;
	word-break: break-all;
	float: left;
}

.commentEditLeftBlank {
	border-top: 1px solid #C2D5E3;
	width: 650px;
	float: left;
	height: 30px;
	line-height: 30px;
	word-break: break-all;
}

.commentEdit {
	border-top: 1px solid #C2D5E3;
	width: 100px;
	float: left;
	height: 30px;
	line-height: 30px;
	word-break: break-all;
}

.commentEdit input {
	background-color: silver;
	width: 40px;
	height: 20px;
	margin: 5px;
	float: left;
	border: 0;
	color: white;
	font-size: 14px;
}

.commentEdit input:hover {
	background-color: #71AAE3;
	border: 0;
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
					&nbsp;&gt;&nbsp;<a href="NC-JSP/home/index.jsp">论坛</a>&nbsp;&gt;&nbsp;<a
						href="/617/Thresh<c:out value="${sessionScope.topic.topicsType.typesCategory.id}" />_1.617museum"><c:out value="${sessionScope.topic.topicsType.typesCategory.namee}"></c:out> </a>&nbsp;&gt;&nbsp;<a
						href="/617/Janna<c:out
								value="${sessionScope.topic.topicsType.id}" />_1.617museum"><c:out value="${sessionScope.topic.topicsType.name}"></c:out> </a>&nbsp;&gt;&nbsp;
					<c:out value="${sessionScope.topic.title}"></c:out>
				</div>
			</div>
			<div class="editMenu">
				<c:if test="${sessionScope.topic.status==0}">
					<input onclick="goEndTopic();" type="button" value="结帖"
						class="butt" style="width: 60px;" />
				</c:if>
				<c:if test="${sessionScope.topic.status==1}"></c:if>
				<input type="button" value="发帖" onclick="checkNewTop()" class="butt"
					style="width: 60px;" /> <input type="button" value="回复"
					class="butt" style="width: 60px;" onclick="goDiv('newComment')" />
			</div>
			<div class="pageNav" align="right">

					<c:if test="${page.totalPages == 1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>

					<c:if test="${page.totalPages == 2}">
						<c:if test="${page.currentPage==1}">
							<button disabled="disabled" style="width: 80px;">上一页</button>
							<button disabled="disabled">1</button>
							<a
								href="/617/Ahri<c:out value="${sessionScope.topic.id}" />_2.617museum"><button>2</button>
							</a>
							<a
								href="/617/Ahri<c:out value="${sessionScope.topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
									style="width: 80px;">下一页</button> </a>
						</c:if>
						<c:if test="${page.currentPage==2}">
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
									style="width: 80px;">上一页</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
							</a>
							<button disabled="disabled">2</button>
							<button disabled="disabled" style="width: 80px;">下一页</button>
						</c:if>
					</c:if>
					<c:if test="${page.totalPages == 3}">
						<c:if test="${page.currentPage==1}">
							<button disabled="disabled" style="width: 80px;">上一页</button>
							<button disabled="disabled">1</button>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
							</a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_3.617museum"><button>3</button>
							</a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
									style="width: 80px;">下一页</button> </a>
						</c:if>
						<c:if test="${page.currentPage==2}">
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
									style="width: 80px;">上一页</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
							</a>
							<button disabled="disabled">2</button>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_3.617museum"><button>3</button>
							</a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
									style="width: 80px;">下一页</button> </a>
						</c:if>
						<c:if test="${page.currentPage==3}">
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
									style="width: 80px;">上一页</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
							</a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
							</a>
							<button disabled="disabled">3</button>
							<button disabled="disabled" style="width: 80px;">下一页</button>
						</c:if>
					</c:if>
					<c:if test="${page.totalPages > 3}">
						<c:if test="${page.currentPage==1}">
							<button disabled="disabled" style="width: 80px;">上一页</button>
							<button disabled="disabled">1</button>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
							</a>
							<a><button disabled="disabled">...</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.totalPages}" />.617museum"><button>
									<c:out value="${page.totalPages}"></c:out>
								</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
									style="width: 80px;">下一页</button> </a>
						</c:if>
						<c:if test="${page.currentPage<page.totalPages&&page.currentPage!=1}">
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
									style="width: 80px;">上一页</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
							</a>
							<c:if test="${page.currentPage>2}">
								<a><button disabled="disabled">...</button> </a>
							</c:if>
							<button disabled="disabled">
								<c:out value="${page.currentPage}"></c:out>
							</button>
							<c:if test="${page.currentPage<page.totalPages-1}">
								<a><button disabled="disabled">...</button> </a>
							</c:if>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.totalPages}" />.617museum"><button>
								<c:out value="${page.totalPages}"></c:out>
								</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
									style="width: 80px;" style="width: 80px;">下一页</button> </a>
						</c:if>
						<c:if test="${page.currentPage==page.totalPages}">
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
									style="width: 80px;">上一页</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
							</a>
							<a><button disabled="disabled">...</button> </a>
							<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button>
									<c:out value="${page.totalPages-1}"/>
								</button> </a>
							<button disabled="disabled">
								<c:out value="${page.totalPages}"/>
							</button>
							<button disabled="disabled" style="width: 80px;">下一页</button>
						</c:if>
					</c:if>

			</div>
			<div class="titleStyle" align="left">
				<div style="width:800px;float: left;font-size: 13px; ">
					&nbsp; <font style="font-size: 13px;color: white"><c:if
							test="${sessionScope.topic.niceTopic==1}">[<font color="red">精品</font>]</c:if> <c:if test="${sessionScope.topic.niceTopic==0}"></c:if>
						<c:if test="${sessionScope.topic.status==0}">[未结帖]</c:if> <c:if test="${sessionScope.topic.status==1}">[已结帖]</c:if> </font>
					<c:out value="${sessionScope.topic.title}"/>
					<font style="font-size: 13px;color: white">[积分: <c:out
							value="${sessionScope.topic.integral}" /> 分]</font>
				</div>

			</div>
			<div class="topicStyle">
				<input id="topicId" value="<c:out value="${sessionScope.topic.id}" />"
					style="display: none;"> <input id="topicUserId"
					value="<c:out value="${sessionScope.topic.topicsUser.id}" />"
					style="display: none;"> <input id="currentUserId"
					value="<c:out value="${userInfo.id}" />"
					style="display: none;">
				<div class="userInfo">
					<div style="height:180px;" align="center">
						<img width="120px;" height="160px;" style="padding-top: 20px;"
							src="<c:out value="${sessionScope.topic.topicsUser.picture}"/>">
					</div>
					<div style="height:80px;padding-top: 20px" align="center">
						<a
							href="/617/Morgana${sessionScope.topic.topicsUser.id}.617museum"><c:out
								value="${sessionScope.topic.topicsUser.nickname}" /> </a> <br />
						<c:out value="${sessionScope.topic.topicsUser.usersGrade.id}" />
						:
						<c:out value="${sessionScope.topic.topicsUser.usersGrade.honor}" />
					</div>
				</div>
				<div
					style="border-left:1px solid  #C2D5E3;min-height: 300px;float: left;width: 760px;">
					<div class="theTop">
						<div
							style="height: 28px;line-height:28px;font-size:13px;width:440px;text-align: left;float: left;border-bottom:1px solid  #C2D5E3;"
							align="left">
							&nbsp;&nbsp;发表于：
							<fmt:formatDate value="${sessionScope.topic.topicTime}" type="date"/>
						</div>
						<div
							style="height: 28px;line-height:28px;font-size:14px;width:320px;text-align: right;float: left;border-bottom:1px solid  #C2D5E3;"
							align="left">楼主&nbsp;&nbsp;&nbsp;&nbsp;</div>
					</div>
					<div
						style="width: 766px;min-height:270px;background-color: #F8F8F8;float: left;">
						<div
							style="text-align: left;width: 728px;float: left;padding:10px 20px 20px 20px;word-break: break-all;">
							<c:if test="${sessionScope.topic.status==2}">
								<c:out value="帖子已被删除，请浏览其它内容" />
							</c:if>
							<c:if test="${sessionScope.topic.status==0||sessionScope.topic.status==1}">
								<c:out value="${sessionScope.topic.content}" default="expression" escapeXml="false" />
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div>
				<c:if test="${sessionScope.topic.status==1||sessionScope.topic.status==0}">
				<c:forEach items="${listComment}" var="comment">
					<c:if test="${comment.status==0}">
					<div class="commentInfo">

							<div class="commentUserInfo">
								<div style="width:200px;height:180px;" align="center">
									<img alt="上传图片" width="120px;" height="160px;"
										style="padding-top: 20px"
										data-src="<c:out value="${comment.commentsUser.picture}"/>" class="lazyload" />
								</div>
								<div style="width:200px;height:80px;padding-top: 20px"
									align="center">
									<a
										href="/617/Morgana${comment.commentsUser.id}.617museum"><c:out
											value="${comment.commentsUser.nickname}" /> </a> <br />
									<c:out value="${comment.commentsUser.usersGrade.id}" />
									:
									<c:out value="${comment.commentsUser.usersGrade.honor}" />
								</div>
							</div>
							<div
								style="border-left:1px solid  #C2D5E3;min-height: 280px;float: left;width: 758px">
								<div
									style="height: 28px;line-height:28px;font-size:13px;width:440px;text-align: left;float: left;border-bottom:1px solid  #C2D5E3;"
									align="left">
									&nbsp;&nbsp;回复于：
									<fmt:formatDate value="${comment.commentTime}" type="date"/>
								</div>
								<div
									style="height: 28px;line-height:28px;font-size:14px;width:318px;text-align: right;float: left;border-bottom:1px solid  #C2D5E3;"
									align="left">
									得分：
									<c:out value="${comment.integral}" />
									分 &nbsp;&nbsp;#
									<c:out value="${comment.floor}" />
									&nbsp;&nbsp;
								</div>
								<div
									style="width: 766px;min-height:260px;background-color: #F8F8F8;float: left;">
									<div class="theComment"
										id="comment<c:out value="${comment.floor}" />">
										<c:out value="${comment.content}" default="expression" escapeXml="false" />
									</div>
								</div>
								<div class="commentEditLeftBlank"></div>
								<div class="commentEdit" align="right">
									<c:if
										test="${sessionScope.userInfo.roleId==14}">
										<input type="button" value="删除"
											onclick="deleteComment(<c:out value="${comment.id}" />);" />
									</c:if>
									<c:if test="${comment.status==1}&&${sessionScope.userInfo.roleId!=6}">
										123
									</c:if>
									<input type="button" value="引用"
										onclick="reference('comment<c:out value="${comment.floor}" />','<c:out value="${comment.floor}" />','<c:out value="${u.nickname}" />')" />
								</div>
							</div>


					</div>
					</c:if>
				</c:forEach>
				</c:if>
			</div>
			<div class="editMenu">
				<c:if test="${sessionScope.topic.status==0}">
					<input onclick="goEndTopic();" type="button" value="结帖"
						class="butt" style="width: 60px;" />
				</c:if>
				<c:if test="${sessionScope.topic.status==1}"></c:if>
				<input type="button" value="发帖" onclick="checkNewTop()" class="butt"
					style="width: 60px;" /> <input type="button" value="回复"
					class="butt" style="width: 60px;" onclick="goDiv('newComment')" />
			</div>
			<div class="pageNav" align="right">

				<c:if test="${page.totalPages == 1}">
					<button disabled="disabled" style="width: 80px;">上一页</button>
					<button disabled="disabled">1</button>
					<button disabled="disabled" style="width: 80px;">下一页</button>
				</c:if>

				<c:if test="${page.totalPages == 2}">
					<c:if test="${page.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="/617/Ahri<c:out value="${sessionScope.topic.id}" />_2.617museum"><button>2</button>
						</a>
						<a
								href="/617/Ahri<c:out value="${sessionScope.topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${page.currentPage==2}">
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
						</a>
						<button disabled="disabled">2</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>
				<c:if test="${page.totalPages == 3}">
					<c:if test="${page.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
						</a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_3.617museum"><button>3</button>
						</a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${page.currentPage==2}">
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
						</a>
						<button disabled="disabled">2</button>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_3.617museum"><button>3</button>
						</a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${page.currentPage==3}">
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
						</a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
						</a>
						<button disabled="disabled">3</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>
				<c:if test="${page.totalPages > 3}">
					<c:if test="${page.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_2.617museum"><button>2</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.totalPages}" />.617museum"><button>
							<c:out value="${page.totalPages}"></c:out>
						</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${page.currentPage<page.totalPages&&page.currentPage!=1}">
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
						</a>
						<c:if test="${page.currentPage>2}">
							<a><button disabled="disabled">...</button> </a>
						</c:if>
						<button disabled="disabled">
							<c:out value="${page.currentPage}"></c:out>
						</button>
						<c:if test="${page.currentPage<page.totalPages-1}">
							<a><button disabled="disabled">...</button> </a>
						</c:if>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.totalPages}" />.617museum"><button>
							<c:out value="${page.totalPages}"></c:out>
						</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage+1}" />.617museum"><button
								style="width: 80px;" style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${page.currentPage==page.totalPages}">
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_1.617museum"><button>1</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="/617/Ahri<c:out value="${topic.id}" />_<c:out value="${page.currentPage-1}" />.617museum"><button>
							<c:out value="${page.totalPages-1}"/>
						</button> </a>
						<button disabled="disabled">
							<c:out value="${page.totalPages}"/>
						</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>

			</div>
			<c:if test="${sessionScope.topic.status==1||sessionScope.topic.status==0}">
			<div class="newComment" id="newComment">

				<form action="postedComment" method="post"
					style="float:left;">
					<input name="topic.id" value="<c:out value="${topic.id}" />"
						style="display: none;"> <input name="pageBean.allRecords"
						value="<c:out value="${pageBean.allRecords}" />"
						style="display: none;"> <input name="pageBean.pageSize"
						value="<c:out value="${pageBean.pageSize}" />"
						style="display: none;">
					<div class="userInfo">
						<%
							Users user = (Users) session.getAttribute("userInfo");
							if (user != null) {
						%>
						<div style="height:180px;" align="center">
							<img width="120px;" height="160px;" style="padding-top: 20px;"
								src="<c:out value="${sessionScope.userInfo.picture}"/>">
						</div>
						<div style="height:80px;padding-top: 20px" align="center">
							<c:out value="${sessionScope.userInfo.nickname}" />
						</div>
						<%
							} else {
						%>

						<div style="height:180px;" align="center">
							<img src="images/notUser.jpg" width="120px;" height="160px;"
								style="padding-top: 20px;"></img>
						</div>
						<div style="height:80px;padding-top: 20px" align="center">
							游客(未<a href="NC-JSP/home/login.jsp" style="color: red">登录</a>)
						</div>
						<%
							}
						%>
					</div>
					<div style="float:left;width:740px;">
						<textarea id="content" style="display: none;"
							name="commentContent" /></textarea>
						<script id="container" type="text/plain"></script>
					</div>
					<script type="text/javascript">
						var editor = UE.getEditor('container');
					</script>
					<div
						style="width: 768px;height: 52px;line-height: 50px;float: left;border-left:1px solid  #C2D5E3;border-right:1px solid  silver;background-color: #F8F8F8"
						align="left">
						<input type="submit" value="发表评论" class="butt"
							onclick="return validComment();"
							style="margin-top: 10px;margin-left: 20px" /><font size="2px;">提示：请不要恶意灌水！谢谢！</font>
					</div>
				</form>
			</div>
			</c:if>
		</div>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
		<script type="text/javascript">
		function goDiv(div) {
		var a = $("#" + div).offset().top;
		$("html,body").animate({
			scrollTop : a
		}, 'slow');
	}
	function goTop() {
		$('html, body').animate({
			scrollTop : 0
		}, 'slow');
	};
	</script>
		<button onclick="goDiv('newComment')" id="quick-reply"
			class="btn btn-top" title="快速回复"
			style="cursor: pointer; border: 0px none; bottom: 35px; min-width: 40px; height: 40px; margin: 0px; padding: 0px; position: fixed; right: 40px; display: block;">
			<span class="glyphicon glyphicon-share-alt"></span>
		</button>
		<button onclick="goTop()" id="scroll-top" class="btn btn-top"
			title="返回顶部"
			style="cursor: pointer; border: 0px none; bottom: 80px; min-width: 40px; height: 40px; margin: 0px; padding: 0px; position: fixed; right: 40px; display: block;">
			<span class="glyphicon glyphicon-chevron-up"></span>
		</button>
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
