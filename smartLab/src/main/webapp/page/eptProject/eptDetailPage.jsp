<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>实验详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;实验详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<form>
	<div id=MainArea>
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> ${experiment.eptNo }详情 </div> 
        </div>
        <table class="button" cellspacing="0" cellpadding="3">
			<tr>
				<td class="title0">查看${experiment.eptNo }详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="20%" class="title">实验编号：</td>
				<td>${experiment.eptNo }</td>
			</tr>
			<tr>
				<td width="20%" class="title">实验名称：</td>
				<td>${experiment.name }</td>
			</tr>
			<tr>
				<td width="20%" class="title">实验所属项目：</td>
				<td>${experiment.project.projNo }</td>
			</tr>
			<tr>
				<td width="20%" class="title">主要操作者：</td>
				<td>${experiment.mainOperate.loginName }</td>
			</tr>
			<tr>
				<td width="20%" class="title">实验开始日期：</td>
				<td><fmt:formatDate value="${experiment.startDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">实验结束日期：</td>
				<td><fmt:formatDate value="${experiment.endDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">内含记录数：</td>
				<td>${fn:length(experiment.records)}</td>
			</tr>
			<tr>
				<td width="20%" class="title">描述：</td>
				<td>${experiment.description}</td>
			</tr>
			<tr>
				<td width="20%" class="title">备注：</td>
				<td>${experiment.reverse}</td>
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
