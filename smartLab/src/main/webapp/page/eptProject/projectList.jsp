<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>项目列表</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<script type="text/javascript">
		//在对话框中查看项目详细信息
		function viewProjDetail(projectId){
			var url = "${pageContext.request.contextPath}/experiment/toProjDetail?projectId=" + projectId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"项目详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
		}
	</script>
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title"><!--页面标题-->
			&emsp;&emsp;项目列表
		</div>
		<div id="Title_End"></div>
	</div>
</div>
<div id="MainArea">
	<center>
		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="20%" colspan="3" class="ForumPageTableTitleLeft">项目编号</td>
					<td width="20%" class="ForumPageTableTitle">项目启动日期</td>
					<td width="20%" class="ForumPageTableTitle">项目结束日期</td>
					<td width="20%" class="ForumPageTableTitle">简略情况</td>
					<td width="7%" colspan="3" class="ForumPageTableTitle">操作</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr height="3"><td colspan="9"></td></tr>
			
				<!--版面列表-->
				<tbody class="dataContainer" datakey="projectList">
				<s:iterator value="projectList" var="project">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine"><!-- style="background-color:#ffff80;" -->
							<img src="${pageContext.request.contextPath}/style/images/project9.png" />
						</td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li class="ForumPageTopic">
									<a name="${project.projNo}" class="ForumPageTopic" href="findExperimentList?projectId=${project.id }">${project.projNo}</a>
								</li>
								<li class="ForumPageTopicMemo">${project.name}</li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><b><fmt:formatDate value="${project.startDate}" pattern="yyyy年MM月dd日"/></b></td>
						<td class="ForumPageTableDataLine"><b><fmt:formatDate value="${project.endDate}" pattern="yyyy年MM月dd日"/></b></td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li><font color="#444444">┌ 内含实验数：</font> ${fn:length(project.experiments)}</li>
								<li><font color="#444444">├ 项目申请人：</font> ${project.claimer.loginName}</li>
								<li><div class="hideText" style="width:15em"><font color="#444444">└ 备注：</font>${project.reverse}</div></li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><a href="#" onclick="viewProjDetail('${project.id }')">详情</td></a>
						<s:if test="#session.loginUser.hasThisPri('项目修改页面')">
							<td class="ForumPageTableDataLine"><a href="toEditProjectPage?projectId=${project.id }&pageNo=${page.pageNo }">修改</td></a>
						</s:if>
						<s:if test="#session.loginUser.hasThisPri('项目删除')">
							<td class="ForumPageTableDataLine"><a onClick="return delConfirm('此操作会同时删除该项目中所有相关的实验数据，请谨慎操作！')" href="deleteProjectById?projectId=${project.id }">删除</td></a>
						</s:if>
					</tr>
				</s:iterator>
				</tbody>
				<!-- 版面列表结束 -->
				<tr height="3"><td colspan="9"></td></tr>
			</table>
			<!-- 其他功能超链接 -->
			<div id="TableTail" align="left">
				<div id="TableTail_inside">
		    		<s:if test="#session.loginUser.hasThisPri('项目添加页面')">
			            <a href="toCreateNewProjPage.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
		    		</s:if>
				</div>
			</div>
			<%@include file="/page/publicPage/pageView.jsp" %>
			<!-- 提供分页用的表单 -->
			<form id="pageForm" action="projAndExpeManagePage" method="post"></form>
		    <div id="Description">
				说明：<br />
				1、用户只能看到自己导师申请的项目，并且只有导师才对项目有增、删、改、查的权限，学生用户只能查看。<br />
				2、点击项目编号，可进入该项目相应的实验实施列表。<br />
				3、点击“详情”，可查看该项目的详细情况。<br />
				4、删除项目时，将同时删除此项目旗下所有的实验信息以及每个实验的记录信息。<br />
			</div>
		</div>
	</center>
</div>
</body>
</html>

