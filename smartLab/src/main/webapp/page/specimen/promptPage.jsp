<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
	<title>操作失败</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/style/blue/Standard.css'>
	<script type="text/javascript">
		function showPrompt(){
			$("#prompt").toggle("fast");
		}
	</script>
</head>
<body>
	<table class="button" cellspacing="0" cellpadding="5">
		<tr>
			<td class="title0">信息提示</td>
		</tr>
	</table>
	<table class="common" cellspacing="0" cellpadding="5">
		<tr>
			<td class="message">
				<img src="${pageContext.request.contextPath}/style/images/imgwarning.png"  align="absmiddle" onclick="showPrompt()" style="cursor:pointer">
				<br>
				删除失败
			</td>
		</tr>
		<tr id="prompt" style="display:inline">
			<td>
				删除失败原因：有一条实验记录信息与该样品相关联。具体信息如下：<br/><br/>
				(1)与该样品关联的记录信息：
				<h4>
					&nbsp;&nbsp;${specimen.reverse1 }
				</h4>
			</td>
		</tr>
	</table>
	<!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
</body>
</html>