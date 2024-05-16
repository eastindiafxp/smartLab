<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>药品信息详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;药品详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<form>
	<div id=MainArea>
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> ${medicine.name }详情 </div> 
        </div>
        <table class="button" cellspacing="0" cellpadding="3">
			<tr>
				<td class="title0">查看${medicine.name }详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="25%" class="title">药品名称：</td>
				<td>${medicine.name }</td>
			</tr>
			<tr>
				<td width="25%" class="title">化学式：</td>
				<td>${medicine.chemFormula }</td>
			</tr>
			<tr>
				<td width="25%" class="title">生产商：</td>
				<td>${medicine.manufacturer }</td>
			</tr>
			<tr>
				<td width="25%" class="title">生产日期：</td>
				<td><fmt:formatDate value="${medicine.manuDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">失效日期：</td>
				<td><fmt:formatDate value="${medicine.expireDate}" pattern="yyyy年MM月dd日"/></td>
			</tr>
			<tr>
				<td width="25%" class="title">存放位置：</td>
				<td>${medicine.position }</td>
			</tr>
			<tr>
				<td width="25%" class="title">纯度/浓度：</td>
				<td>${medicine.concentration}</td>
			</tr>
			<tr>
				<td width="25%" class="title">用到此材料的记录数：</td>
				<td>${fn:length(medicine.records)}</td>
			</tr>
			<tr>
				<td width="25%" class="title">用到此药品的溶液：</td>
				<td>
					<s:iterator value="medicine.solutions" var="s">
						${s.name}; 
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td width="25%" class="title">记录者：</td>
				<td>${medicine.owner.loginName}</td>
			</tr>
			<tr>
				<td width="25%" class="title">备注：</td>
				<td>${medicine.reverse}</td>
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
