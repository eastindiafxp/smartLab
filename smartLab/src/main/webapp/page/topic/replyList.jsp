<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看主题：新手发帖</title>
    <%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/forum.css" />
	<link  type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ckeditor/css/samples/sample.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
	
		$("#pageNos").val(${page.pageNo});
	
		
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &nbsp;&nbsp;查看主题
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--内容显示-->	
<div id="MainArea">
	<div id="PageHead"></div>
	<center>
		<div class="ItemBlock_Title1" style="width: 98%">
			<font class="MenuPoint"> &gt; </font>
			<a href="${pageContext.request.contextPath}/forum/findForumList.action">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="${pageContext.request.contextPath}/forum/findTopicList.action?id=${forum.id }">${forum.name }</a>
			<font class="MenuPoint"> &gt;&gt; </font>
				${title }
			<span style="margin-left:30px;"><a href="${pageContext.request.contextPath}/topic/topicPage?forumId=${forum.id}">
				<img align="middle" src="${pageContext.request.contextPath}/style/blue/images/button/publishNewTopic.png"/></a>
			</span>
		</div>
		
		<div class="ForumPageTableBorder dataContainer" datakey="replyList">
		
			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${title }</b></td>
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
						<a class="detail" href="${pageContext.request.contextPath}/reply/replyPage?topicId=${id}"><img border="0" src="${pageContext.request.contextPath}/style/images/reply.gif" />回复</a>
					</td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>

			<!-- ~~~~~~~~~~~~~~~ 显示主帖 ~~~~~~~~~~~~~~~ -->
			<s:if test="page.pageNo == 1 || page.pageNo == 0">
				<div class="ListArea">
					<table border="0" cellpadding="0" cellspacing="1" width="98%">
						<tr>
							<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
								<!--作者头像-->
								<div class="AuthorPhoto">
									<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
										onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
								</div>
								<!--作者名称-->
								<div class="AuthorName">${author.loginName }</div>
							</td>
						</tr>
						<tr><!-- 文章内容 -->
							<td valign="top" align="center">
								<div class="Content">${content }</div>
							</td>
						</tr>
						<tr><!--显示楼层等信息-->
							<td class="Footer" height="28" align="center" valign="bottom">
								<ul style="margin: 0px; width: 98%;">
									<li style="float: left; line-height:18px;"><font color=#C30000>[楼主]</font>
										${postTime }
									</li>
									<li style="float: right;"><a href="javascript:scroll(0,0)">
										<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
									</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</s:if>
			<!-- ~~~~~~~~~~~~~~~ 显示主帖结束 ~~~~~~~~~~~~~~~ -->


			<!-- ~~~~~~~~~~~~~~~ 显示回复列表 ~~~~~~~~~~~~~~~ -->
			<div class="ListArea template">
				<s:iterator value="replyList" var = "re" status="s">
					<table border="0" cellpadding="0" cellspacing="1" width="98%">
							<tr>
								<td rowspan="3" width="130" class="PhotoArea" align="center" valign="top">
									<!--作者头像-->
									<div class="AuthorPhoto">
										<img border="0" width="110" height="110" src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif" 
											onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/style/images/defaultAvatar.gif';" />
									</div>
									<!--作者名称-->
									<div class="AuthorName">${re.author.loginName }</div>
								</td>
							</tr>
							<tr><!-- 文章内容 -->
								<td valign="top" align="center">
									<div class="Content">${re.content}</div>
								</td>
							</tr>
							<tr><!--显示楼层等信息-->
								<td class="Footer" height="28" align="center" valign="bottom">
									<ul style="margin: 0px; width: 98%;">
										<li style="float: left; line-height:18px;"><font color=#C30000>[<s:property value="(page.pageNo-1)*page.pageSize + #s.count"/>楼]</font>
											${re.postTime }
										</li>
										<li style="float: right;"><a href="javascript:scroll(0,0)">
											<img border="0" src="${pageContext.request.contextPath}/style/images/top.gif" /></a>
										</li>
									</ul>
								</td>
							</tr>
					</table>
				</s:iterator>
			</div>
			<!-- ~~~~~~~~~~~~~~~ 显示回复列表结束 ~~~~~~~~~~~~~~~ -->
		</div>

		<!--分页信息-->
		<%@include file="/page/publicPage/pageView.jsp" %>
		<form id="pageForm" action="findReplyList" method="post">
			<input type="hidden" name="topicId" value="${topic.id }">
		</form>

		<div class="ForumPageTableBorder" style="margin-top: 25px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
					<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>快速回复</b></td>
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine">
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
	</center>
			
	<!--快速回复-->
	<div class="QuictReply">
	<form action="${pageContext.request.contextPath}/reply/saveReply.action?topicId=${topic.id }" method="post">
		<!-- <input type="hidden" name="topicId" value="${topic.id }"> -->
		<div style="padding-left: 3px;">
			<table border="0" cellspacing="1" width="98%" cellpadding="5" class="TableStyle">
				<tr class="Tint" height="200">
					<td valign="top" rowspan="2" class="Deep"><b>内容</b></td>
					<td valign="top" class="no_color_bg">
						<textarea id="reply" name="reply.content" style="width: 95%; height: 300px"></textarea>
					</td>
				</tr>
				<tr height="30" class="Tint">
					<td class="no_color_bg" colspan="2" align="center">
						<input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" style="margin-right:15px;"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
</div>

<div class="Description">
	<script type="text/javascript">
		CKEDITOR.replace( 'reply', {
			uiColor: '#14B8C4'
		});
	</script>
</div>

</body>
</html>

