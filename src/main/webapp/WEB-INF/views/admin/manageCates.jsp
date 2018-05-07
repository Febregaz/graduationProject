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

<title>版块管理</title>

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

table {
	word-wrap: break-word;
	word-break: break-all;
	table-layout: fixed;
}

.butt {
	background-color: #6699CC;
	width: 100px;
	height: 30px;
	margin-right: 10px;
	border: 0;
	color: white;
	font-size: 18px;
}

.butt:hover {
	background-color: #71AAE3;
	border: 0;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	function whenCateChange() {
	    console.log($("#categoryID").val());
		var selectedCate = $("#selectCate").val();
		$("#categoryID").val(selectedCate);
		$
				.ajax({
					type : "post",
					url : "types/getAllTypesByCategoryId?categoryId="
							+ selectedCate,
					dataType : "json",
					success : function showContent(data) {
					    console.log(data);
					    /*var sumTopic = 0;
					    for (var i = 0 ; i<data.length ; i++){
							sumTopic+=data[i].typesCategory.countTopics;
						}
						$("#topicCount").text(sumTopic);*/
						cateStr = data[0].typesCategory;
						$("#cateTable").empty();//清空列表
						$("#cateTable")
								.append(
										"<tr  style='background-color: #A2C1DE;height:30px;'><td style='width:60px;'>类型名</td><td style='width: 160px;'><input type='text'  readonly='readonly' disabled='disabled'  value='"
												+ cateStr.namee
												+ "' name='category.name' /></td></tr><tr  style='background-color: #A2C1DE;height:30px;'><td style='width:60px;'>帖子数</td><td><input type='text'  readonly='readonly' disabled='disabled'  value='"
												+ cateStr.countTopics
												+ "' name='category.countTopics' id='topicCount' /></td></tr><tr  style='background-color: #A2C1DE;height:30px;'><td style='width:60px;'>评论数</td><td><input type='text'  readonly='readonly' disabled='disabled'  value='"
												+ cateStr.countComments
												+ "' name='category.countComments' /></td></tr>");
						var typeStr = "";
						$("#typeTable").empty();//清空列表
						for ( var i = 0; i < data.length; i++) {
							typeStr = data[i];
							$("#typeTable")
									.append(
											"<tr style='background-color: #D3D1D1'><td style='width:180px;'>"
													+ typeStr.name
													+ "</td><td style='width:60px;'>"
													+ typeStr.countTopics
													+ "</td><td style='width:60px;'>"
													+ typeStr.countComments
													+ "</td><td style='width:120px;'><a style='cursor:pointer' onclick='showUpdateType("
													+ typeStr.id
													+ ",\""
													+ typeStr.name
													+ "\","
													+ typeStr.countTopics
													+ ","
													+ typeStr.countComments
													+ ",\""
													+ typeStr.typesCategory.id
													+ "\")'>更新</a>&nbsp;&nbsp;&nbsp;<a style='cursor:pointer' onclick='return checkDeleteType("+typeStr.id+","+typeStr.countTopics+")'>删除</a></td></tr>");//向清空的列表中增加新值
						}
					}
				});
	};
	function checkDeleteType(typeId , typeCountTopics) {
		//alert("typeId:"+typeId+"typeCountTopics:"+typeCountTopics);
		if(typeCountTopics!=0){
		    alert("请先将该类型下的所有帖子逻辑删除");
		    return false;
		}
		else{
            if (confirm("此操作将删除该类型，并且该类型下的所有已被逻辑删除帖子将不可恢复，你确认要进行操作吗？")){
                $.ajax({
                    type : "POST",
                    async : false,
                    url : "/types/deleteType?typeId="+typeId,
                    success : function(status) {
                        window.location.reload();
                    }
                });
			}
			return false;
		}
		return false;
	}
	function showUpdateType(typeId, typeName, typeCountTopics,
			typeCountComments, typeCate) {
		$("#updateTypeTable").empty();
		$("#updateTypeTable")
				.append(
						"<tr style='background-color: #A2C1DE;height:30px;'><td width='80px;'>更新小类型</td><td width='120px;'>"
								+ typeName
								+ "</td><td>为：<input type='text' style='height: 30px;font-size: 16px;border: 1px solid red;' id='updateTypeName' name='typeName'></td>"
								+ "<td width='50px;' style='background-color:white;'><input type='submit' value='更新' style='width: 50px;' class='butt' onclick='return checkUpdateType();' /></td>"
								+ "<td width='50px;'  style='background-color:white;'><input type='button' value='取消' style='width: 50px;' class='butt' onclick='return cancelUpdateType();' /></td></tr>"
								+ "<tr><td><input type='text' style='display: none;' name='typeId' value='"+typeId+"'>"
								+ "<input type='text' style='display: none;' name='type.countTopics' value='"+typeCountTopics+"'>"
								+ "<input type='text' style='display: none;' name='type.countComments' value='"+typeCountComments+"'>"
								+ "<input type='text' style='display: none;' name='type.typesCategory.id' value='"+typeCate+"'></td></tr>");

	}
	function checkUpdateType() {
		var typeName = $("#updateTypeName").val();
		var updateTypeName = typeName.replace(/(&nbsp;)|\s|\u00a0/g, '');
		if (updateTypeName == null || updateTypeName == "") {
			alert("不能为空！");
			return false;
		}
		return true;
	}
	function cancelUpdateType() {
		$("#updateTypeTable").empty();
		$("#updateTypeTable")
				.append(
						"<tr style='height:30px;line-height:30px;color:red'><td>更新小类型在这！</td></tr>");
	}
	function checkUpdateCate() {
		var cateName = $("#updateCateName").val();
		var updateCateName = cateName.replace(/(&nbsp;)|\s|\u00a0/g, '');
		if (updateCateName == null || updateCateName == "") {
			alert("不能为空！");
			return false;
		}
		return true;
	}
	function checkAddType() {
		var addTypeCateName = $("#addTypeCateName").val();
		$("#cateId").val(addTypeCateName);

		var typeName = $("#addTypeName").val();
		var addTypeName = typeName.replace(/(&nbsp;)|\s|\u00a0/g, '');
		if (addTypeCateName == 0) {
			alert("大类型不能为空！");
			return false;
		} else if (addTypeName == null || addTypeName == "") {
			alert("小类型名不能为空！");
			return false;
		}
		return true;
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
				版 块 管 理<br />
			</div>
			<%
				String categoryId = request.getParameter("selectedCate");
			%>
			<div align="center"
				style="float: left;width:250px;height:300px;margin-left: 5px;margin-top: 20px;border: 1px solid white;padding-top: 20px;">
				<form action="category/updateCategoryName" method="post">
					<table style='width:230px;'>
						<tr style='background-color: #A2C1DE;height:30px;'>
							<td style='width:60px;'>大类型</td>
							<td style="background-color: white"><select
								name="category.id"
								style="height:26px;width: 160px;font-size:14px" id="selectCate"
								onchange="whenCateChange();">
									<option value="0">-请选择大版块-</option>
									<c:forEach items="${listCate}" var="cate">
										<option value='<c:out value="${cate.id}" />'>
											<c:out value="${cate.namee}" />
										</option>
									</c:forEach>
							</select>
							</td>
						</tr>

					</table>
					<div style="height:100px;">
						<table style='width:220px;' id="cateTable">
						</table>
					</div>
					<table style='width:220px;margin-top: 10px;' cellspacing="0">
						<tr style='background-color: #A2C1DE;height:30px;'>
							<td
								style="width:60px;border-top: 1px solid #2B4A78;border-left: 1px solid #2B4A78;">更新为</td>
							<td
								style="width:160px;border-top: 1px solid #2B4A78;border-right: 1px solid #2B4A78;"><input
								type="text" style="	width: 160px;height: 30px;font-size: 18px;"
								id="updateCateName" name="categoryName"></td>
							<input type="hidden" id="categoryID" name="categoryId">
						</tr>
						<tr>
							<td
								style="border-left: 1px solid #2B4A78;border-bottom: 1px solid #2B4A78;">&nbsp;</td>
							<td
								style="border-right: 1px solid #2B4A78;border-bottom: 1px solid #2B4A78;"><input
								type="submit" value="确认更新" class="butt"
								onclick="return checkUpdateCate();" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div
				style="float: left;width:550px;height:320px;margin-top: 20px;border-top: 1px solid white;border-right: 1px solid #2B4A78;border-bottom: 1px solid #2B4A78;margin-bottom: 10px;">
				<table style='width:550px;'>
					<tr style='background-color: #A2C1DE;height:30px;'>
						<td style='width:180px;'>小类型</td>
						<td style='width:60px;'>帖子数</td>
						<td style='width:60px;'>回复数</td>
						<td style='width:120px;'>管理</td>
					</tr>
				</table>
				<div style="height: 240px;">
					<table style='width:550px;' id="typeTable">
					</table>
				</div>
				<div
					style="width:550px;height:40px;border: 1px solid white;float: left;">
					<form action="types/updateTypeName" method="post">
						<table style='width:550px;' id="updateTypeTable">
							<tr style='height:30px;line-height:30px;color:red'>
								<td>更新小类型在这！</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div align="center"
				style="float: left;width:100%;height:40px;border-top: 1px solid white;">
				<form action="category/addCategory" method="post">
					<table style='width:550px;'>
						<tr style='background-color: #A2C1DE;height:30px;'>
							<td width="200px"
								style="font-size: 24px;margin-top: 10px;color: #4D9EF0;background-color: white;text-align: left;">添
								加 大 类 型</td>
							<td style='width:150px;'>名称</td>
							<td style="background-color: white;width: 200px;"><input
								type="text" style="	width: 200px;height: 30px;font-size: 18px;"
								name="categoryName"></td>
							<td align="center"><input type="submit" value="添加"
								class="butt" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center"
				style="width:100%;float: left;	border-top: 1px solid white;">
				<form action="types/addType" method="post"
					onsubmit="return checkAddType();">
					<div align="center" style="font-size: 22px;">
						添 加 小 类 型<br />
					</div>
					<table style='width:350px;'>
						<tr style='background-color: #A2C1DE;height:30px;'>
							<td style='width:150px;'>大类型</td>
							<td style="background-color: white;width: 200px;"><select
								name="type.typesCategory.id" id="addTypeCateName"
								style="height:26px;width: 200px;font-size:14px">
									<option value="0">-请选择大版块-</option>
									<c:forEach items="${listCate}" var="cate">
										<option value='<c:out value="${cate.id}" />'>
											<c:out value="${cate.namee}" />
										</option>
									</c:forEach>
							</select></td>
							<td></td>
							<input type="hidden" id="cateId" name="cateId" />
						</tr>
						<tr style='background-color: #A2C1DE;height:30px;'>
							<td style='width:150px;'>名称</td>
							<td style="background-color: white;width: 200px;"><input
								id="addTypeName" type="text"
								style="	width: 200px;height: 30px;font-size: 18px;"
								name="typeName"></td>
							<td><input type="submit" value="添加" class="butt" /></td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</div>
</body>
<%
	}
%>
</html>
