<%@ page language="java"
	import="java.util.*,yuzhaoLiu.project.mybatis.entity.people.Users,java.io.*" pageEncoding="UTF-8"%>
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

<title>帖子管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/manage.css" />
<style type="text/css">
body,html {
	width: 1004px;
	padding: 0px;
	margin: 0px auto; /*设置居中  */
	text-align: center; /*兼容性更好  */
	font-family: "微软雅黑";
}

.pageNav {
	width: 550px;
	height: 30px;
	margin-top: 15px;
	margin-bottom: 5px;
	float: left;
}

.pageGo {
	width: 120px;
	height: 25px;
	line-height: 25px;
	margin-top: 15px;
	margin-bottom: 5px;
	float: left;
	font-size: 13px;
}

.pageNav a button {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: white;
	border: 1px solid silver;
}

.pageNav a button:HOVER {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #A2C1DE;
	border: 1px solid silver;
}

.pageNav button {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #EFF4FB;
	border: 1px solid silver;
}

table tr td {
	border-bottom: 1px solid silver;
	border-right: 1px solid silver;
	padding-left: 3px;
	color: red;
}

.topicStyle {
	width: 790px;
	margin-left: 10px;
	margin-right: 5px;
}

.topicStyle a:link,.topicStyle a:visited {
	color: blue;
	text-decoration: none;
} /* 未被访问的链接 */ /*已被访问的链接 */
.topicStyle a:hover {
	color: #2979BF;
	font-weight: bolder;
	text-decoration: none;
} /* 鼠标指针移动到链接上 */
</style>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	function goPage() {
		var currentPage = parseInt($("#currentPage").val());
		var selectedPage = parseInt($("#selectPage").val());
		if (selectedPage != 0 && selectedPage != currentPage) {
			document.goPageForm.action = "topics/manageAll?nowPage="
					+ selectedPage;
			goPageForm.submit();
		}

	}
	function nice() {
		if (confirm("此操作将推荐该帖为精品，你确认要进行操作吗？")) {
			return true;
		}
		return false;
	}
	function unNice() {
		if (confirm("此操作将取消推荐该帖为精品，你确认要进行操作吗？")) {
			return true;
		}
		return false;
	}
	function checkDelete()
	{
        alert("帖子删除功能暂时停用");
        return false;
	}
</script>
</head>
<%
	Users user = (Users) session.getAttribute("userInfo");
	if (user == null) {
		PrintWriter pw = response.getWriter();
		pw.println("<script type='text/javascript'>alert('未登录或登录已失效！请登录！');window.location.href = '"+basePath+"login.jsp';</script>");

	} else if (user.getRoleId() == 0) {
		PrintWriter pw = response.getWriter();
		pw.println("<script type='text/javascript'>alert('权限不够！切换账号登录');window.location.href = '"+basePath+"login.jsp';</script>");
	} else {
%>
<body>
	<div class="body">
		<div class="top">
			<img alt="" src="images/70logo.png" />
		</div>
		<div class="left">
			<jsp:include page="/WEB-INF/views/admin/left.jsp"></jsp:include>
		</div>
		<div class="right">
			<div align="center" style="font-size: 24px;margin-top: 10px;">
				帖 子 管 理<font style="font-size: 14px;">(共有<c:out
						value="${pageBean.allRecords}" />帖子)</font>
			</div>
			<div class="pageNav" align="right">
				<c:if test="${pageBean.totalPages == 1}">
					<button disabled="disabled" style="width: 80px;">上一页</button>
					<button disabled="disabled">1</button>
					<button disabled="disabled" style="width: 80px;">下一页</button>
				</c:if>

				<c:if test="${pageBean.totalPages == 2}">
					<c:if test="${pageBean.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="topics/manageAll?nowPage=2"><button>2</button>
						</a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==2}">
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="topics/manageAll?nowPage=1"><button>1</button>
						</a>
						<button disabled="disabled">2</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>
				<c:if test="${pageBean.totalPages == 3}">
					<c:if test="${pageBean.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="topics/manageAll?nowPage=2"><button>2</button>
						</a>
						<a
								href="topics/manageAll?nowPage=3"><button>3</button>
						</a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==2}">
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="topics/manageAll?nowPage=1"><button>1</button>
						</a>
						<button disabled="disabled">2</button>
						<a
								href="topics/manageAll?nowPage=3"><button>3</button>
						</a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==3}">
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="topics/manageAll?nowPage=1"><button>1</button>
						</a>
						<a
								href="topics/manageAll?nowPage=2"><button>2</button>
						</a>
						<button disabled="disabled">3</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>
				<c:if test="${pageBean.totalPages > 3}">
					<c:if test="${pageBean.currentPage==1}">
						<button disabled="disabled" style="width: 80px;">上一页</button>
						<button disabled="disabled">1</button>
						<a
								href="topics/manageAll?nowPage=2"><button>2</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
							<c:out value="${page.totalPages}"></c:out>
						</button> </a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage<pageBean.totalPages&&pageBean.currentPage!=1}">
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="topics/manageAll?nowPage=1"><button>1</button>
						</a>
						<c:if test="${pageBean.currentPage>2}">
							<a><button disabled="disabled">...</button> </a>
						</c:if>
						<button disabled="disabled">
							<c:out value="${pageBean.currentPage}"></c:out>
						</button>
						<c:if test="${pageBean.currentPage<pageBean.totalPages-1}">
							<a><button disabled="disabled">...</button> </a>
						</c:if>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
							<c:out value="${pageBean.totalPages}"></c:out>
						</button> </a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;" style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==pageBean.totalPages}">
						<a
								href="topics/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="topics/manageAll?nowPage=1"><button>1</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="topics/manageAll?nowPage=<c:out value="${page.currentPage-1}" />"><button>
							<c:out value="${pageBean.totalPages-1}"/>
						</button> </a>
						<button disabled="disabled">
							<c:out value="${pageBean.totalPages}"/>
						</button>
						<button disabled="disabled" style="width: 80px;">下一页</button>
					</c:if>
				</c:if>
			</div>
			<div class="pageGo" align="right">
				<form action="topics/manageAll?nowPage=1" method="post"
					name="goPageForm">
					<input type="text" id="currentPage"
						value="<c:out value="${pageBean.currentPage}" />"
						style="display: none"> 第 <select onchange="goPage();"
						id="selectPage"
						style="width:70px;height:24px;border-radius:0;border: 1px solid silver;">
						<option value="0">请选择</option>
						<%
							int i = 1;
						%>
							<c:forEach begin="1" end="${pageBean.totalPages}">
								<option value="<%=i%>"><%=i%></option>
								<%
									i++;
								%>
							</c:forEach>
					</select> 页
				</form>
			</div>
			<table class="topicStyle" cellspacing="0">
				<tr style="background-color: #A2C1DE;height: 26px;">
					<td width="220px">标题</td>
					<td width="120px">类型</td>
					<td width="120px">作者</td>
					<td width="30px">评论</td>
					<td width="40px">状态</td>
					<td width="30px">精品</td>
					<td>操作</td>
					<td width="120px">管理</td>
				</tr>
				<c:forEach items="${listTopic}" var="topic">
					<tr style="height: 26px;">
						<td style="text-align: left;border-left: 1px solid silver;"><div
								style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width: 220px;">
								<c:out value="${topic.title}" />
							</div></td>
						<td style="font-size: 12px;"><c:out
								value="${topic.topicsType.name}" />-<c:out
								value="${topic.topicsType.typesCategory.namee}" /></td>
						<td><c:out value="${topic.topicsUser.nickname}" /></td>
						<td><c:out value="${topic.countComment}" />
						</td>
						<td style="font-size: 12px;"><c:if test="${topic.status==0}">未结帖</c:if>
							<c:if test="${topic.status==1}">已结帖</c:if></td>
						<td><c:if test="${topic.niceTopic==0}">否</c:if> <c:if test="${topic.niceTopic==1}">是</c:if>
						</td>
						<td style="font-size: 12px;color: blue;"><a
							href="topics/toTheDetailPage?topicId=<c:out
								value="${topic.id}" />&&nowPage=1">查看详细&gt;&gt;</a>
						</td>
						<td style="color: blue;"><c:if test="${topic.niceTopic==0}">
								<a style="color: red" onclick="return nice()"
									href="/topics/niceOrNot?topicId=<c:out
								value="${topic.id}" />">推荐精品</a>
							</c:if> <c:if test="${topic.niceTopic==1}">
								<a onclick="return unNice()"
									href="/topics/niceOrNot?topicId=<c:out
								value="${topic.id}" />">取消精品</a>
							</c:if>&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: silver;"
							onclick="return checkDelete()" href="">删除</a>
						</td>
					</tr>

				</c:forEach>
			</table>

		</div>
	</div>

</body>
<%
	}
%>
</html>
