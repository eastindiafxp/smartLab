<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
	<title>操作失败</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/style/blue/Standard.css'>
	<script type="text/javascript">
		function showError(){
			$("#error").toggle("fast");
		}
	</script>
</head>
<body>
	<table class="button" cellspacing="0" cellpadding="5">
		<tr>
			<td class="title0">异常信息提示</td>
		</tr>
	</table>
	<table class="common" cellspacing="0" cellpadding="5">
		<tr>
			<td class="message">
				<img src="${pageContext.request.contextPath}/style/images/imgwarning.png"  align="absmiddle" onclick="showError()" style="cursor:pointer">
				<br>
				${requestScope.exceptionMessage.message }
			</td>
		</tr>
		<tr id="error" style="display:none">
			<td>
				报错类名：${requestScope.exceptionMessage.className }<br>
				报错信息：${requestScope.exceptionMessage.error }
			</td>
		</tr>
	</table>
</body>
</html>