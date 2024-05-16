<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>记录详情</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>

<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;记录详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<form>
	<div id=MainArea>
        <div class="ItemBlock_Title1"><!-- 信息说明 -->
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> ${record.recordNo }详情 </div> 
        </div>
        <table class="button" cellspacing="0" cellpadding="3">
			<tr>
				<td class="title0">查看${record.recordNo }详细信息</td>
			</tr>
		</table>
		<table width="100%" border="1px" bgcolor="#ffffb7" cellspacing="0" cellpadding="3" style="word-wrap: break-word; word-break: break-all">
			<tr>
				<td width="20%" class="title">记录编号：</td>
				<td>${record.recordNo }</td>
			</tr>
			<tr>
				<td width="20%" class="title">记录名称：</td>
				<td>${record.name }</td>
			</tr>
			<tr>
				<td width="20%" class="title">记录所属实验：</td>
				<td>${record.experiment.name }</td>
			</tr>
			<tr>
				<td width="20%" class="title">被镀材料及用量：</td>
				<td>${record.substrate.name }，${record.substrateMass }</td>
			</tr>
			<tr>
				<td width="20%" class="title">镀层：</td>
				<td>${record.cladLayer }</td>
			</tr>
			<tr>
				<td width="20%" class="title">pH值：</td>
				<td>${record.ph }</td>
			</tr>
			<tr>
				<td width="20%" class="title">反应温度：</td>
				<td>${record.temperature }</td>
			</tr>
			<tr>
				<td width="20%" class="title">反应时间：</td>
				<td>${record.reaTime }</td>
			</tr>
			<tr>
				<td width="20%" class="title">操作过程：</td>
				<td>${record.process }</td>
			</tr>
			<tr>
				<td width="20%" class="title">开始日期：</td>
				<td><fmt:formatDate value="${record.startDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">结束日期：</td>
				<td><fmt:formatDate value="${record.endDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			</tr>
			<tr>
				<td width="20%" class="title">样品标签：</td>
				<td>
					<s:iterator value="record.specimens" var="spec">
						${spec.label}&nbsp;
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td width="20%" class="title">所用溶液：</td>
				<td>
		        	<s:iterator value="record.solutions" var="s">
		            	${s.name}&nbsp;
		      		</s:iterator>
		    	</td>
			</tr>
			<tr>
				<td width="20%" class="title">具体操作者：</td>
				<td>
		        	<s:iterator value="record.operaters" var="o">
		            	${o.loginName}&nbsp;
		      		</s:iterator>
		    	</td>
			</tr>
			<tr>
				<td width="20%" class="title">备注：</td>
				<td>${record.reverse}</td>
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
