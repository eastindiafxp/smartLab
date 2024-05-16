<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>论坛列表</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
</head>
<body>
<div id="Title_bar">
	<div id="Title_bar_Head">
		<div id="Title_Head"></div>
		<div id="Title"><!--页面标题-->
			&emsp;&emsp;论坛&nbsp;
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
					<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
					<td width="80" class="ForumPageTableTitle">主题数</td>
					<td width="80" class="ForumPageTableTitle">文章数</td>
					<td width="270" class="ForumPageTableTitle">最后发表的主题</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
				<tr height="3"><td colspan="9"></td></tr>
			
				<!--版面列表-->
				<tbody class="dataContainer" datakey="forumList">
				<s:iterator value="forumList" var="forum">
					<tr height="78" align="center" class="template">
						<td width="3"></td>
						<td width="75" class="ForumPageTableDataLine">
							<img src="${pageContext.request.contextPath}/style/images/forumpage3.gif" />
						</td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li class="ForumPageTopic">
									<a name="${forum.name}" class="ForumPageTopic" href="findTopicList?id=${forum.id }">${forum.name}</a>
								</li>
								<li class="ForumPageTopicMemo">${forum.description}</li>
							</ul>
						</td>
						<td class="ForumPageTableDataLine"><b>${forum.topicCount}</b></td>
						<td class="ForumPageTableDataLine"><b>${forum.articleCount}</b></td>
						<td class="ForumPageTableDataLine">
							<ul class="ForumPageTopicUl">
								<li><font color="#444444">┌ 主题：</font>
									<a class="ForumTitle" href="${pageContext.request.contextPath}/reply/findReplyList.action?topicId=${forum.lastTopic.id}">${forum.lastTopic.title}</a>
								</li>
								<li><font color="#444444">├ 作者：</font> ${forum.lastTopic.author.loginName}</li>
								<li><font color="#444444">└ 时间：</font> ${forum.lastTopic.postTime}</li>
							</ul>
						</td>
						<td width="3"></td>
					</tr>
				</s:iterator>
				</tbody>
				<!-- 版面列表结束 -->
				
				<tr height="3"><td colspan="9"></td></tr>
			</table>
		</div>
	</center>
</div>
<%@include file="/page/publicPage/pageView.jsp" %>
<form id="pageForm" action="findForumList.action" method="post"></form>
</body>
</html>

