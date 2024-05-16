<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>检测信息详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;样品检测详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<form>
	<div id=MainArea>
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> ${specimen.label }详情 </div> 
        </div>
        <table class="button" cellspacing="0" cellpadding="3">
			<tr>
				<td class="title0">查看${test.label }详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="25%" class="title">送检标签：</td>
				<td>${test.specimen.label }</td>
			</tr>
			<tr>
				<td width="25%" class="title">样品标签：</td>
				<td>${test.label }</td>
			</tr>
			<tr>
				<td width="25%" class="title">送检日期：</td>
				<td><fmt:formatDate value="${test.submitDate }" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">检测日期：</td>
				<td><fmt:formatDate value="${test.testDate }" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">检测手段：</td>
				<td>${test.testMethod }</td>
			</tr>
			<tr>
				<td width="25%" class="title">检测结果：</td>
				<td>${test.testResult }</td>
			</tr>
			<tr>
				<td width="25%" class="title">记录者：</td>
				<td>${test.owner.loginName }</td>
			</tr>
			<tr>
				<td width="25%" class="title">备注：</td>
				<td>${test.reverse }</td>
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
