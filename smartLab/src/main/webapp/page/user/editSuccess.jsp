<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript">
	var i = 3;
	var t = window.setInterval("backResult()",1000);
	function backResult(){
		i--;
		document.getElementById("msg").innerHTML = "" + i + "秒后跳转到原查询结果页面";
		if(i == 0){
			window.clearInterval(t);
			fm.action="findUserList.action";
			fm.submit();
		}
	}
	
</script>
</head>
<body>
	<form action="" name="fm" method="POST">
		<input type="hidden" name="pageNo" value="${pageNo }">
		<table class="button" cellspacing="0" cellpadding="5">
			<tr>
				<td class="title0">信息提示</td>
			</tr>
		</table>
		<table class="common" cellspacing="0" cellpadding="5">
			<tr>
				<td class="message"><img src="${pageContext.request.contextPath}/style/images/imgsuccess.png" align="middle"></td>
			</tr>
			<tr>
				<td class="message"><span id="msg"></span></td>
			</tr>
		</table>
	</form>
</body>
</html>
