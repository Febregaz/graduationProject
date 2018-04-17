<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>失败</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="./CSS/top.css">
<link rel="stylesheet" type="text/css" href="./CSS/copyRight.css">
<script type="text/javascript">
 	var msg=""+'${sessionScope.tipMessage}';
 	if(msg!=""){ 
   		alert(msg); 
 	} 
</script>
</head>

<body>
	<div style="background-color: #F5F5F5">
		<div class="top_nav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="tbody"  style="min-height:400px;background-color: #F5F5F5 ">
			<form name=loading>
				<div style="height: 150px;width: 800px;"></div>
					<font color="#0066ff">操作失败，正在返回，请稍等</font><font color="#0066ff"
						face="Arial">...</font> <input type="text" name="chart"
						style="font-weight:bolder;width: 304px; color:#CBCBCB; background-color:#EFEFEF; border-style:none;">

					<input type="text" name="percent"
						style="color:#0066ff; text-align:center; border-width:medium; border-style:none;">
					<script type="text/javascript">
						var bar = 0;
						var line = "||";
						var amount = "||";
						count();
						function count() {
							bar = bar + 2;
							amount = amount + line;
							document.loading.chart.value = amount;
							document.loading.percent.value = bar + "%";
							if (bar < 99) {
								setTimeout("count()", 100);
							} else {
								history.back();
							}
						}
					</script>
				</p>
			</form>
			<p align="center" style="font-size: 16px;">
				如果您的浏览器不支持跳转,<a style="text-decoration: none" href="javascript:history.go(-1);"><font
					color="#FF0000">请点击这里</font></a>.
			</p>
		</div>
						<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
