<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>【${forum.name }】中的主题列表</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;${forum.name }
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="findForumList.action">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			${forum.name }
			<span style="margin-left:30px;"><a href="${pageContext.request.contextPath}/topic/topicPage?forumId=${forum.id}">
				<img align="middle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/></a>
			</span>
		</div>
		<div class="ForumPageTableBorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!--表头-->
				<tr align="center" valign="middle">
					<td width="3" class="ForumPageTableTitleLeft">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
					<td width="50" class="ForumPageTableTitle"><!--状态/图标-->&nbsp;</td>
					<td class="ForumPageTableTitle">主题</td>
					<td width="130" class="ForumPageTableTitle">作者</td>
					<td width="100" class="ForumPageTableTitle">回复数</td>
					<td width="130" class="ForumPageTableTitle">最后回复</td>
					<td width="3" class="ForumPageTableTitleRight">
						<img border="0" width="1" height="1" src="${pageContext.request.contextPath}/style/images/blank.gif" />
					</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
				<tr height=3><td colspan=8></td></tr>
					
				<!--主题列表-->
				<tbody class="dataContainer" datakey="topicList">
					<s:iterator value="topicList" var="topic">
						<tr height="35" id="d0" class="template">
							<td></td>
							<td class="ForumTopicPageDataLine" align="center"><img src="${pageContext.request.contextPath}/style/images/topicType_${topic.topicType}.gif" /></td>
							<td class="Topic"><a class="Default" href="${pageContext.request.contextPath}/reply/findReplyList?topicId=${topic.id }">${topic.title}</a></td>
							<td class="ForumTopicPageDataLine" align="center">
								<span class="Author">${topic.author.loginName }</span><br>
								<span class="CreateTime">${topic.postTime }</span>
								<!-- <ul class="ForumPageTopicUl">
									<li class="Author">${topic.author.loginName }</li>
									<li class="CreateTime">
										${topic.postTime }
										<s:date name="topic.postTime" format="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul> -->
							</td>
							<td class="ForumTopicPageDataLine Reply" align="center"><b>${topic.replyCount}</b></td>
							<td class="ForumTopicPageDataLine" align="center">
								<span class="Author">${topic.lastReply.author.loginName }</span><br>
								<span class="CreateTime">${topic.lastReply.postTime}</span>
								<!-- <ul class="ForumPageTopicUl">
									<li class="Author">${topic.lastReply.author.loginName }</li>
									<li class="CreateTime">
										${topic.lastReply.postTime}
										<s:date name="topic.lastReply.postTime" format="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul> -->
							</td>
							<td></td>
						</tr>
					</s:iterator>
					</tbody>
					<!--主题列表结束-->	
					<tr height="3"><td colspan="9"></td></tr>
				
			</table>
			
			<!--其他操作-->
			<form id="pageForm" action="findTopicList" method="post">
				<input type="hidden" name="forum.id" value="${forum.id }" >
				<div id="TableTail">
					<div id="TableTail_inside">
						<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
							<tr valign=bottom>
								<td></td>
								<td><select id="viewType" name="viewType">
										<option value="0">全部主题</option>
										<option value="1">全部精华贴</option>
										<!--
										<option value="2">当天的主题</option>
										<option value="3">本周的主题</option>
										<option value="4">本月的主题</option>
										-->
									</select>
									<select id="orderBy" name="orderBy">
										<option value="0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
										<option value="1">按最后更新时间排序</option>
										<option value="2">按主题发表时间排序</option>
										<option value="3">按回复数量排序</option>
									</select>
									<select id="asc" name="asc">
										<option value="false">降序</option>
										<option value="true">升序</option>
									</select>
									<!-- <input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="middle"/> -->
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div align="center">
					<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="middle"/>
				</div>
			</form>
		</div>
	</center>
</div>

<!--分页信息-->
<%@include file="/page/publicPage/pageView.jsp" %>

<div class="Description">
	说明：<br />
	1，主题默认按最后更新的时间降序排列。最后更新时间是指主题最后回复的时间，如果没有回复，就是主题发表的时间。<br />
	2，帖子有普通、置顶、精华之分。置顶贴始终显示在最上面，精华贴用不同的图标标示。<br />
</div>
<script type="text/javascript">
		$("#viewType").val(${viewType});
		$("#orderBy").val(${orderBy});
		$("#asc").val('${asc}');
	</script>
</body>
</html>

