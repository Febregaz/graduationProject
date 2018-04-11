<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<base href="<%=basePath%>">
<style>
.copyRight {
	margin-top: 30px;
	width: 1004px;
	height: 100px;
	float: left;
	border-top: 1px dashed silver;
	/* 	background-color: yellow; */
}
</style>

<div style="text-align: center;margin-top: 20px;" align="center">
	<font face="微软雅黑" size="2px" style="line-height: 30px;"><a href="NC-JSP/home/aboutUs.jsp">关于论坛</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a  href="NC-JSP/home/technology.jsp">技术支持</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="NC-JSP/home/secret.jsp">隐私和版权</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="NC-JSP/home/connect.jsp">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<c:if test="${sessionScope.userInfo.roleId==6||sessionScope.userInfo.roleId==14}"><a href="NC-JSP/admin/manage.jsp">后台管理</a></c:if><c:if test="${sessionScope.userInfo.roleId!=6&&sessionScope.userInfo.roleId!=14}"><a onclick="alert('您没有权限！')">后台管理</a></c:if>
	</font><br /> <font face="微软雅黑" size="2px">Copyright@2018 sevenZero |
		广东省广州市(天河区)大观中路新园新村<br /> 电话：18818429769
		邮箱：febregazz@gmail.com </font>
</div>
