<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
.table-striped td {
	border-bottom: 1px dashed silver;
}

a:link {
	color: #525252;
	text-decoration: none;
} /* 未被访问的链接 */
a:visited {
	color: #525252;
} /*已被访问的链接 */
a:hover {
	color: red;
	text-decoration: underline;
} /* 鼠标指针移动到链接上 */
</style>

<div style="font-family: 微软雅黑;">
	<table class="table-striped">
		<c:forEach items="${helpsList}" var="help">
			<tr>
				<td width="110px" height="30px" style="font-size: 14px;"><div
						style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width: 100px;float: left;">
						<a
							href="help_getAll.action?helpId=" target="_top"><c:out value="${help.title}"></c:out> </a>
					</div>
				</td>
				<td style="font-size: 10px;">
					<fmt:formatDate value="${help.newtime}" type="date"/>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
