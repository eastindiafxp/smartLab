<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>阶段总结信息详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;阶段总结详情
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
				<td class="title0">查看阶段总结详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="25%" class="title">标题：</td>
				<td>${summary.title }</td>
			</tr>
			<tr>
				<td width="25%" class="title">作者：</td>
				<td>${summary.author.loginName }</td>
			</tr>
			<tr>
				<td width="25%" class="title">总结日期：</td>
				<td><fmt:formatDate value="${summary.summaryDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">最后更新日期：</td>
				<td><fmt:formatDate value="${summary.lastUpdateDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">备注：</td>
				<td>${summary.reverse }</td>
			</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td><br/></td>
			</tr>
			<tr>
				<td class="title" colspan="2" style="text-align: center;">总结1内容：</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<textarea style="overflow-y:auto;" rows="18" cols="70" readonly="readonly">${summary.summary1}</textarea>
				</td>
			</tr>
			<tr>
				<td><br/></td>
			</tr>
			<tr>
				<td class="title" colspan="2" style="text-align: center">总结2内容：</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<textarea style="overflow-y:auto" rows="18" cols="70" readonly="readonly">${summary.summary2}</textarea>
				</td>
			</tr>
			<tr>
				<td><br/></td>
			</tr>
			<tr>
				<td class="title" colspan="2" style="text-align: center">总结3内容：</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<textarea style="overflow-y:auto" rows="18" cols="70" readonly="readonly">${summary.summary3}</textarea>
				</td>
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
