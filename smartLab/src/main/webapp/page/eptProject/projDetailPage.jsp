<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>项目详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;项目详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<form>
	<div id=MainArea>
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> ${project2.projNo }详情 </div> 
        </div>
        <table class="button" cellspacing="0" cellpadding="3">
			<tr>
				<td class="title0">查看${project2.projNo }详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="20%" class="title">项目编号：</td>
				<td>${project2.projNo }</td>
			</tr>
			<tr>
				<td width="20%" class="title">项目名称：</td>
				<td>${project2.name }</td>
			</tr>
			<tr>
				<td width="20%" class="title">项目申请人：</td>
				<td>${project2.claimer.loginName }</td>
			</tr>
			<tr>
				<td width="20%" class="title">项目启动日期：</td>
				<td><fmt:formatDate value="${project2.startDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">项目结束日期：</td>
				<td><fmt:formatDate value="${project2.endDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">内含实验数：</td>
				<td>${fn:length(project2.experiments)}</td>
			</tr>
			<tr>
				<td width="20%" class="title">备注：</td>
				<td>${project2.reverse}</td>
			</tr>
		</table>
		<br>

		<table class="button">
			<tr>
				<td align="center">
					<input type="button" class="button" value="关 闭" onclick="javascript:window.close();" />
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>
