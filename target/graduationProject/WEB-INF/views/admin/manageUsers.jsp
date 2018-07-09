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

<title>用户管理</title>

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

.userStyle {
	width: 790px;
	margin-left: 10px;
	margin-right: 5px;
}

.userStyle a:link,.userStyle a:visited {
	color: blue;
	text-decoration: none;
} /* 未被访问的链接 */ /*已被访问的链接 */
.userStyle a:hover {
	color: #2979BF;
	font-weight: bolder;
	text-decoration: none;
} /* 鼠标指针移动到链接上 */
</style>
<script type="text/javascript">
				function goPage() {
					var currentPage = parseInt($("#currentPage").val());
					var selectedPage = parseInt($("#selectPage").val());
					if (selectedPage != 0 && selectedPage != currentPage) {
						document.goPageForm.action = "user_ManageAll.action?nowPage="
								+ selectedPage;
						goPageForm.submit();
					}

				}
				function deleteUser() {
					if (confirm("此操作将暂时禁用该账户，你确认要进行操作吗？")) {
						return true;
					}
					return false;
				}
				function unDeleteUser() {
					if (confirm("此操作将解除该账户的禁用状态，你确认要进行操作吗？")) {
						return true;
					}
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
			<img alt="" src="images/70logo.png" style="display: block; margin-left: auto; margin-right: auto;" />
		</div>
		<div class="left">
			<jsp:include page="/WEB-INF/views/admin/left.jsp"></jsp:include>
		</div>
		<div class="right">
			<div align="center" style="font-size: 24px;margin-top: 10px;">
				用 户 管 理<font style="font-size: 14px;">(共有<c:out
						value="${pageBean.allRecords}" />用户)</font>
			</div>
			<div align="left" style="font-size: 16px;margin-top: 10px;color: red;padding-left: 20px;">
				注意：高级管理员人数尽量保持在3人以内！
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
								href="users/manageAll?nowPage=2"><button>2</button>
						</a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==2}">
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="users/manageAll?nowPage=1"><button>1</button>
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
								href="users/manageAll?nowPage=2"><button>2</button>
						</a>
						<a
								href="users/manageAll?nowPage=3"><button>3</button>
						</a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==2}">
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="users/manageAll?nowPage=1"><button>1</button>
						</a>
						<button disabled="disabled">2</button>
						<a
								href="users/manageAll?nowPage=3"><button>3</button>
						</a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==3}">
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="users/manageAll?nowPage=1"><button>1</button>
						</a>
						<a
								href="users/manageAll?nowPage=2"><button>2</button>
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
								href="users/manageAll?nowPage=2"><button>2</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
							<c:out value="${page.totalPages}"></c:out>
						</button> </a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage<pageBean.totalPages&&pageBean.currentPage!=1}">
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="users/manageAll?nowPage=1"><button>1</button>
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
								href="users/manageAll?nowPage=<c:out value="${pageBean.totalPages}" />"><button>
							<c:out value="${pageBean.totalPages}"></c:out>
						</button> </a>
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage+1}" />"><button
								style="width: 80px;" style="width: 80px;">下一页</button> </a>
					</c:if>
					<c:if test="${pageBean.currentPage==pageBean.totalPages}">
						<a
								href="users/manageAll?nowPage=<c:out value="${pageBean.currentPage-1}" />"><button
								style="width: 80px;">上一页</button> </a>
						<a
								href="users/manageAll?nowPage=1"><button>1</button>
						</a>
						<a><button disabled="disabled">...</button> </a>
						<a
								href="users/manageAll?nowPage=<c:out value="${page.currentPage-1}" />"><button>
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
				<form action="topic_getAllTopic.action" method="post"
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
			<table class="userStyle" cellspacing="0">
				<tr style="background-color: #A2C1DE;height: 24px;">
					<td width="160px">用户名</td>
					<td width="140px">昵称</td>
					<td width="80px">权限</td>
					<td width="60px">状态</td>
					<td width="90px">等级</td>
					<td width="40px">积分</td>
					<td width="50px">升级分</td>
					<td>操作</td>
					<td width="50px">管理</td>
				</tr>
				<c:forEach items="${listUser}" var="us">
					<tr style="height: 26px;">
						<td style="border-left: 1px solid silver;"><c:out
								value="${us.username}" />
						</td>
						<td><c:out value="${us.nickname}" />
						</td>
						<td><c:if test="${us.roleId==0}">普通用户</c:if> <c:if
								test="${us.roleId==6}">普通管理员</c:if> <c:if
								test="${us.roleId==14}">高级管理员</c:if>
						</td>
						<td><c:if test="${us.status==0}">可用状态</c:if> <c:if test="${us.status==1}">禁用状态</c:if>
						</td>
						<td><c:out value="${us.usersGrade.id}" />:<c:out
								value="${us.usersGrade.honor}" />
						</td>
						<td><c:out value="${us.integral}" />
						</td>
						<td><c:out value="${us.gradeIntegral}" />
						</td>
						<td style="font-size: 12px;color: blue;"><a
							href="/users/getUserById?userId=<c:out value="${us.id}" />">查看详细&gt;&gt;</a>
						</td>
						<td style="color: blue;"><c:if test="${us.roleId!=16}">
								<c:if test="${us.status==1}">
									<a style="color: red"
										href="/users/disabledOrAbleUser?userId=<c:out value="${us.id}" />"
										onclick="unDeleteUser();">解禁</a>
								</c:if>
								<c:if test="${us.status==0}">
									<a
										href="/users/disabledOrAbleUser?userId=<c:out value="${us.id}" />"
										onclick="deleteUser(<c:out value="${us.id}" />);">禁用</a>
								</c:if>
							</c:if> <c:if test="${us.roleId==16}">
								<font style="color:silver;">无权限</font>
							</c:if>
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
