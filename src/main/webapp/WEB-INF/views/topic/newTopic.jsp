<%@ page language="java"
	import="java.util.*,yuzhaoLiu.project.mybatis.entity.people.Users,java.io.*" pageEncoding="UTF-8"%>
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
<%
	Users user = (Users) session.getAttribute("userInfo");
	if (user == null) {
		PrintWriter pw = response.getWriter();
		pw.println("<script type='text/javascript'>alert('未登录或登录已失效！请登录！');location.href = 'NC-JSP/home/login.jsp';</script>");

	} else {
%>
<title>发表帖子</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/SyntaxHighlighter/3.0.83/scripts/shCore.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="ueditor/Topic-ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="http://apps.bdimg.com/libs/ueditor/1.4.3.1/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript" src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	SyntaxHighlighter.all();
	function validToipc() {
	    var topSend = $("#sendAllOrNot").val();
		var topTitle = $("#tTitle").val().replace(/(&nbsp;)|\s|\u00a0/g, '');
		var topType = $("#selectType").val();
		var topCate = $("#selectCate").val();
		var topGrade = $("#tGrade").val();
		var userGrade = <%=user.getUsersGrade().getId()%>
		var allowTopGrade = userGrade *100;
		var nowUserGrade = <c:out value="${sessionScope.userInfo.integral}" />
		if (topTitle == "" || topTitle == null) {
			alert("请填写文章标题!");
			return false;
		}else if(editor.getContent().replace(/(&nbsp;)|\s|\u00a0/g, '')==""||editor.getContent().replace(/(&nbsp;)|\s|\u00a0/g, '')==null){
			alert("文章内容不能为空！");
			return false;
		} else if (editor.getContentLength(true) < 10) {
			alert("文章内容不得少于10个字符！");
			return false;
		} else if (topCate == 0) {
			alert("请选择大版块！");
			return false;
		} else if (topType == 0) {
			alert("请选择小版块！");
			return false;
		}
		else if(topSend == 0){
			alert("请确认是否通知所有人");
			return false;
		}
		else if (topGrade == "" || topGrade ==null) {
			alert("请填写悬赏积分！");
			return false;
		}else if(topGrade <5 || topGrade > allowTopGrade){
			alert("您的等级限制悬赏分范围：5-"+allowTopGrade+" !");
			return false;
		}
		else if(nowUserGrade < topGrade){
		    alert("您的积分不足，请充值后重试");
		    return false;
		}
		$("#content").val(editor.getContent());
		return true;
	};
	function  whenCateChange(){
		var selectedCate=$("#selectCate").val();
		console.log(selectedCate);
        $.ajax({
            type: "post",
            url: "types/getAllTypesByCategoryId?categoryId="+selectedCate,
            dataType: "json",
            success: function showContent(data) {     
                var typeStr = "";
                $("#selectType").empty();//清空列表
                $("#selectType").append("<option value='0'>--请选择小版块--</option>");	
                for(var i=0; i<data.length; i++) {
                    typeStr = data[i];
                    $("#selectType").append("<option value='"+typeStr.id +"' >"+typeStr.name+"</option>");//向清空的列表中增加新值
                }
            }
        });
	};
	function showLen(obj){
   		document.getElementById('span').innerHTML = ' 您还可以输入'+ (50 - obj.value.length) +'个字符';
   		document.getElementById('span').style.color='red';
	}
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
</script>
<style type="text/css">
.background {
	width: 1004px;
	overflow: auto;
	background-color: #F5F5F5;
	margin: 0px auto; /*设置居中  */
}

.smallNav {
	width: 982px;
	height: 24px;
	margin-left: 22px;
	margin-top: 15px;
	float: left;
	font-size: 14px;
	font-family: 微软雅黑;
}

.titleStyle {
	width: 940px;
	height: 40px;
	line-height: 40px;
	margin-left: 22px;
	margin-top: 15px;
	padding-left: 20px;
	background-color: #A2C1DE;
	font-family: 微软雅黑;
	float: left;
	text-align: left;
}

.newStyle {
	width: 960px;
	margin-left: 22px;
	margin-top: 20px;
	font-family: 微软雅黑;
	float: left;
	/* border: 1px solid silver; */
}

.newStyle table {
	border-right: 1px solid #A9CBEE;
	border-bottom: 1px solid #A9CBEE
}

.newStyle table td,.newStyle table th {
	height: 30px;
	line-height: 30px;
	border-left: 1px solid #A9CBEE;
	border-top: 1px solid #A9CBEE;
}

.newStyle select {
	font-family: 微软雅黑;
	border: 1px solid #A9CBEE;
}

.butt {
	background-color: #6699CC;
	width: 120px;
	height: 30px;
	margin-left: 10px;
	margin-top: 15px;
	border: 0;
	color: white;
	font-size: 18px;
	border: 0;
}

.butt:hover {
	background-color: #71AAE3;
	border: 0;
}

.notice {
	margin: 10px 10px 10px 10px;
	padding: 10px 10px 10px 25px;
	border: solid 1px #ccc;
}
</style>
</head>

<body>
	<div class="background">
		<div class="top_nav">
			<jsp:include page="/WEB-INF/views/home/top.jsp"></jsp:include>
		</div>
		<div class="newToic" align="center">
			<div class="smallNav" align="left">
				<div style="float: left;">
					<a href="NC-JSP/home/index.jsp" title="论坛首页"><img
						src="images/homepage_24.png"></img> </a>
				</div>
				<div style="float: left;line-height:24px;">
					&nbsp;&gt;&nbsp;<a>论坛</a>&nbsp;&gt;&nbsp;发帖
				</div>
			</div>
			<form id="newT" method="post" onsubmit="return validToipc()"
				action="topics/postedTopic">
				<div class="titleStyle" align="left">发布帖子</div>
				<div class="newStyle">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="150px" align="center">文章标题</th>
							<td><input type="text" name="topicTitle" id="tTitle"
								onkeyup="showLen(this);"
								style="width: 600px;height: 26px;line-height: 24px;font-size: 14px;padding-left: 6px;"
								maxlength="50" /><span id="span"
								style="color:red;font-size:14px"> 您还可以输入50个字符</span></td>
						</tr>
						<tr>
							<th align="center">文章内容</th>
							<td width="700px" id="help">
								<textarea id="content" style="display: none;"
										  name="tcontent"></textarea>
								<script id="container" type="text/plain"></script>
							</td>
							<script type="text/javascript">
							var editor = UE.getEditor('container');
						</script>
						</tr>
						<tr>
							<th align="center" style="height: 50px">选择版块</th>
							<td>
								<table>
									<tr>
										<td><select
											style="height:26px;width: 200px;font-size:14px"
											id="selectCate" onchange="whenCateChange();">
												<option value="0">--请选择大版块--</option>
												<c:forEach items="${listCate}" var="cate">
													<c:if test="${cate.countTopics!=0}">
													<option value='<c:out value="${cate.id}" />'>
														<c:out value="${cate.namee}" />
													</option>
													</c:if>
												</c:forEach>
										</select>
										</td>
										<td><select
											style="height:26px;width: 150px;font-size:14px"
											id="selectType" name="typeId">
												<option value="0">--请选择小版块--</option>
										</select>
										</td>
										<td><select
												style="height:26px;width: 150px;font-size:14px"
												id="sendAllOrNot" name="ifOrNot">
											<option value="0">是否通知所有人此文章?</option>
											<option value="1">是</option>
											<option value="2">否</option>
										</select>
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<th align="center" style="height: 40px;">帖子分数</th>
							<td>&nbsp;给分<input type="text" id="tGrade"
								name="topicIntegral"
								style="width: 40px;height: 30px;padding-left: 6px;" />&nbsp;(5-<%=user.getUsersGrade().getId() * 100%>)&nbsp;&nbsp;当前可用分:
								<c:out value="${sessionScope.userInfo.integral}" />&nbsp;&nbsp;<span
								style="margin-left: 5px;color:red">结帖后系统返还本帖50%的可用分！</span></td>
						</tr>
						<tr>
							<th style="height: 180px;">&nbsp;</th>
							<td align="left" style="height: 180px;"><input type="submit"
								value="发表帖子" class="butt" onclick="return checkLogin()" /><input
								type="button" value="取消" class="butt"
								onclick="if(window.confirm('你确定要取消吗？')){history.go(-1);};" />
								<ol class="notice">
									<li>这里发言，表示您接受了DS-Java论坛的用户<a href="" target="_blank">行为准则</a>。</li>
									<li>请对您的言行负责，并遵守中华人民共和国有关法律法规,尊重网上道德。</li>
									<li>转载文章请注明出自“DS-Java（www.DSJava.net）”。如是商业用途请联系原作者。</li>
								</ol></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<%
			}
		%>
		<div class="copyRight">
			<jsp:include page="/WEB-INF/views/home/copyRight.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
