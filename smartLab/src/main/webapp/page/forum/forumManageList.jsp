<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>论坛管理列表</title>
	<%@include file="/page/publicPage/head.jsp" %>
</head>
  
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;版块管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">版块名称</td>
                <td>版块说明</td>
                <td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="forumList">
        <s:iterator value="forumList" var="fo" status="s">
			<tr align="center" class="TableDetail1 template">
				<td>${fo.name}&nbsp;</td>
				<td align="left">${fo.description}&nbsp;</td>
				<td><a onClick="return delConfirm()" href="deleteById?id=${fo.id }">删除</a>
					<a href="editForumPage?id=${fo.id }">修改</a>
					<s:if test="page.pageNo == 1">
						<s:if test="#s.first">
							<a href=#>上移</a>
						</s:if>
						<s:else>
							<a href="moveUp?id=${fo.id }">上移</a>
						</s:else>
					</s:if>
					<s:else>
						<a href="moveUp?id=${fo.id }">上移</a>
					</s:else>
					<s:if test="page.pageNo == page.pageCount">
						<s:if test="#s.last">
							<a href=#>下移</a>
						</s:if>
						<s:else>
							<a href="moveDown?id=${fo.id }">下移</a>
						</s:else>
					</s:if>
					<s:else>
						<a href="moveDown?id=${fo.id }">下移</a>
					</s:else>
				</td>
			</tr>
        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="createNewForum.action"><img src="../style/images/createNew.png" /></a>
        </div>
    </div>
</div>
<!--分页信息-->
<%@include file="/page/publicPage/pageView.jsp" %>
<!-- 提供分页用的表单 -->
<form id="pageForm" action="findForumManageList" method="post"></form>
<div class="Description">
	说明：<br />
	1，显示的列表按其sortOrder值升序排列。<br />
	2，可以通过上移与下移功能调整顺序。最上面的不能上移，最下面的不能下移。<br />
</div>

</body>
</html>
