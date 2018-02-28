<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
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
</style>

<div style="font-family: 微软雅黑;">
	<%--        <c:forEach items="${testList}" var="test">
                <c:out value="${test}" />
            </c:forEach>--%>
	<table>
		<%
			int i = 1;
		%>
		<c:forEach items="${hotTopicsList}" var="hot">
			<%--<c:out value="${test}"></c:out>--%>
			<tr>
				<td style="width: 35px;font-style: italic;" align="center"><%=i%></td>
				<td><div style="width:600px;">
					<div
							style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width: 400px;float: left;">
						<a
								href="cate_goCate.action?category.id=<c:out value="${hot}"></c:out>"
								target="_top" style="font-size: 14px;"><c:out value="${hot.topicsType.typesCategory.namee}"></c:out> </a>-<a
							href="type_goType.action?type.id=<c:out value="${hot}"></c:out>"
							target="_top" style="font-size: 14px;"><c:out value="${hot.topicsType.name}"></c:out> </a>||&nbsp;<a
							href="toTheDetailPage?topicId=<c:out value="${hot.id}"></c:out>"
							target="_top" class="topicTitle"><c:out value="${hot.title}"></c:out> </a>
					</div>
					<div style="float: left;font-size: 12px;color: #4C9ED9">
						&nbsp;&nbsp;[
						<c:out value="${hot.integral}"></c:out>
						分 ]&nbsp;[回复:
						<c:out value="${hot.countComment}"></c:out>
						]&nbsp;[
						<c:if test="${hot.status==0}">未结帖</c:if>
						<c:if test="${hot.status==1}">已结帖</c:if>
						]
						<c:if test="${hot.niceTopic==1}">[<font color="red">精品</font>]</c:if>
						<c:if test="${hot.niceTopic==0}"></c:if>
					</div>
				</div>
				</td>
				<td align="right" width="80px;"><div
						style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width: 80px;">
					<a class="topicTitle"
					   href="user_GoUser.action?user.id=<c:out value="${test}"></c:out>"
					   target="_top" style="font-size: 14px;"><c:out value="${hot.topicsUser.nickname}"></c:out> </a>&nbsp;&nbsp;
				</div></td>
			</tr>
			<%
				i++;
			%>
		</c:forEach>
	</table>
</div>
