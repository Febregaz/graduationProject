<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title><c:out value="${type.typesCategory.namee}" />-<c:out
		value="${type.name}" />
</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<style type="text/css">
body,html {
	width: 1004px;
	padding: 0px;
	margin: 0px auto; /*设置居中  */
	text-align: center; /*兼容性更好  */
	background-color: #F8F8F8;
	border: none;
}

.background {
	width: 1004px;
	overflow: auto;
	background-color: #F5F5F5;
	margin: 0 auto;
	min-height: 700px;
}

.leftBodyBlank {
	width: 22px;
	min-height: 300px;
	float: left;
}

.tBody {
	width: 960px;
	font-family: 微软雅黑;
	min-height: 500px;
	float: left;
}

.smallNav {
	width: 960px;
	height: 24px;
	margin-top: 10px;
	float: left;
	font-size: 14px;
}

.editMenu {
	width: 360px;
	height: 30px;
	margin-top: 15px;
	float: left;
}

.pageNav {
	width: 480px;
	height: 30px;
	margin-top: 15px;
	float: left;
}

.pageGo {
	width: 120px;
	height: 30px;
	line-height: 30px;
	margin-top: 15px;
	float: left;
	font-size: 13px;
	margin-top: 15px;
}

.pageNav a button {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: white;
	border: 1px solid silver;
}

.pageNav a button:HOVER {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #A2C1DE;
	border: 1px solid silver;
}

.pageNav button {
	width: 35px;
	height: 30px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #EFF4FB;
	border: 1px solid silver;
}

.butt {
	background-color: #6699CC;
	width: 120px;
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

table td {
	height: 30px;
	font-size: 16px;
}

a:link,a:visited {
	color: #525252;
	text-decoration: none;
} /* 未被访问的链接 */ /*已被访问的链接 */
a:hover {
	color: #525252;
	text-decoration: underline;
} /* 鼠标指针移动到链接上 */
.topicTitle:link,.topicTitle:visited {
	color: #525252;
	text-decoration: none;
}

.topicTitle:hover {
	color: #2C86E5;
	text-decoration: underline;
}

.titleStyle {
	width: 960px;
	height: 35px;
	line-height: 30px;
	margin-top: 15px;
	background-color: #A2C1DE;
	float: left;
	text-align: left;
}

.listTopicStyle td {
	border-bottom: 1px dashed silver;
}
</style>
<script type="text/javascript">
	function checkNewTop() {
		var msg = '<c:out value="${sessionScope.userInfo.nickname}"/>';
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
</head>

<body>
	<div class="background" align="center">
		<div class="top_nav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="leftBodyBlank"></div>
		<div class="tbody">
			<div class="smallNav" align="left">
				<div style="float: left;">
					<a href="NC-JSP/home/index.jsp" title="论坛首页"><img
						src="images/homepage_24.png"></img> </a>
				</div>
				<div style="float: left;line-height:24px;">
					&nbsp;&gt;&nbsp;<a href="NC-JSP/home/index.jsp">论坛</a>&nbsp;&gt;&nbsp;<a
						href="category/goCategory?categoryId=<c:out value="${type.typesCategory.id}" />&&nowPage=1"><c:out
							value="${type.typesCategory.namee}" /> </a>&nbsp;&gt;&nbsp;
					<c:out value="${type.name}" />
				</div>
			</div>
			<div>
				<div class="editMenu">
					<input type="button" value="发帖" class="butt" style="width: 80px;"
						onclick="checkNewTop()" /> <input type="button" value="刷新"
						onclick="window.location.href = 'topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1'"
						class="butt" style="width: 80px;" />
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
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=2"><button>2</button>
								</a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage+1}"/>"><button
										style="width: 80px;">下一页</button> </a>
							</c:if>
							<c:if test="${pageBean.currentPage==2}">
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage-1}"/>"><button
										style="width: 80px;">上一页</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1"><button>1</button>
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
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=2"><button>2</button>
								</a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=3"><button>3</button>
								</a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage+1}"/>"><button
										style="width: 80px;">下一页</button> </a>
							</c:if>
							<c:if test="${pageBean.currentPage==2}">
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage-1}"/>"><button
										style="width: 80px;">上一页</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1"><button>1</button>
								</a>
								<button disabled="disabled">2</button>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=3"><button>3</button>
								</a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage+1}"/>"><button
										style="width: 80px;">下一页</button> </a>
							</c:if>
							<c:if test="${pageBean.currentPage==3}">
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage-1}"/>"><button
										style="width: 80px;">上一页</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1"><button>1</button>
								</a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=2"><button>2</button>
								</a>
								<button disabled="disabled">3</button>
								<button disabled="disabled" style="width: 80px;">下一页</button>
							</c:if>
						</c:if>
						<c:if test="${pageBean.totalPages > 3}">
							<c:if test="${pagaBean.currentPage==1}">
								<button disabled="disabled" style="width: 80px;">上一页</button>
								<button disabled="disabled">1</button>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=2"><button>2</button>
								</a>
								<a><button disabled="disabled">...</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.totalPages}" />"><button>
										<c:out value="${pageBean.totalPages}" />
									</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage+1}"/>"><button
										style="width: 80px;">下一页</button> </a>
							</c:if>
							<c:if test="${pageBean.currentPage<pageBean.totalPages}">
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage-1}"/>"><button
										style="width: 80px;">上一页</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1"><button>1</button>
								</a>
								<c:if test="${pageBean.currentPage>2}">
									<a><button disabled="disabled">...</button> </a>
								</c:if>
								<button disabled="disabled">
									<c:out value="${pageBean.currentPage}" />
								</button>
								<c:if test="${pageBean.currentPage<pageBean.totalPages-1}">
									<a><button disabled="disabled">...</button> </a>
								</c:if>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.totalPages}" />"><button>
										<c:out value="${pageBean.totalPages}" />
									</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="typeId" />&&nowPage=<c:out value="${pageBean.currentPage+1}"/>"><button
										style="width: 80px;" style="width: 80px;">下一页</button> </a>
							</c:if>
							<c:if test="${pageBean.currentPage==pageBean.totalPages}">
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.currentPage-1}"/>"><button
										style="width: 80px;">上一页</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=1"><button>1</button>
								</a>
								<a><button disabled="disabled">...</button> </a>
								<a
									href="topics/getTopicsByTypeId?typeId=<c:out value="${type.id}" />&&nowPage=<c:out value="${pageBean.totalPages-1}" />"><button>
										<c:out value="${pageBean.totalPages-1}" />
									</button> </a>
								<button disabled="disabled">
									<c:out value="${pageBean.totalPages}" />
								</button>
								<button disabled="disabled" style="width: 80px;">下一页</button>
							</c:if>
						</c:if>
				</div>
				<script type="text/javascript">
					function goPage() {
						var currentPage = parseInt($("#currentPage").val());
						var selectedPage = parseInt($("#selectPage").val());
						if (selectedPage != 0 && selectedPage != currentPage) {
							document.goPageForm.action = "topics/getTopicsByTypeId?typeId=<c:out value='${type.id}' />&&nowPage="
									+ selectedPage;
							goPageForm.submit();
						}

					}
				</script>
				<div class="pageGo" align="right">
					<form action="cate_goCate.action" method="post" name="goPageForm">
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
			</div>
			<div class="titleStyle" align="left">
				<table border="0" style="margin: 0;		font-size: 12px;">
					<tr style="height: 30px;line-height: 30px;">
						<td style="width:570px" align="left">&nbsp;&nbsp;标题</td>
						<td style="width:140px" align="right">作者</td>
						<td style="width:60px" align="right">积分</td>
						<td style="width:60px" align="right">评论量</td>
						<td style="width:100px" align="right">发布时间</td>
					</tr>
				</table>
			</div>
			<div>
				<table align="left" class="listTopicStyle">
					<c:forEach items="${listTopic}" var="topic">
						<tr>
							<td align="left"><div
									style="width:570px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
									&nbsp;&nbsp;<font style="font-size: 13px;color: #4C9ED9"><c:if
											test="${topic.niceTopic==1}">[<font color="red">精品</font>]</c:if>
										<c:if test="${topic.niceTopic==0}"></c:if>[<c:if test="${topic.status==0}">未结帖</c:if> <c:if test="${topic.status==1}">已结帖</c:if>]</font><a
										href="/topics/toTheDetailPage?topicId=<c:out
								value="${topic.id}" />&&nowPage=1"
										target="_top" class="topicTitle"><c:out
											value="${topic.title}" /> </a>
								</div>
							</td>

							<td align="right" width="140px;"><a class="topicTitle"
								href="" target="_top" style="font-size: 14px;"><c:out
										value="${topic.topicsUser.nickname}" /> </a>
							</td>
							<td align="right" width="60px;" style="font-size: 13px;"><c:out
									value="${topic.integral}" />
							</td>
							<td align="right" width="60px;" style="font-size: 13px;"><c:out
									value="${topic.countComment}" />
							</td>
							<td style="font-size: 12px;" width="100px;" align="right"><fmt:formatDate value="${topic.topicTime}" type="date"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>
