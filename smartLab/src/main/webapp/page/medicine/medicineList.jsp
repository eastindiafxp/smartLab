<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>药品列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <style type="text/css">
    	#exportData{
    		position:relative;
			top:6px;
			left:20px;
			background-color: #a2fe7e;
    	}
    </style>
    <script type="text/javascript">
    	//在对话框中查看药品的详细信息
    	function viewMediDetail(mediId){
    		var url = "${pageContext.request.contextPath}/medicine/toMediDetail?medicineId=" + mediId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"药品详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    </script>
</head>
<body>
<form action="queryMedicineList.action" id="pageForm" name="queryForm" method="post">
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;药品列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<table class="common" cellspacing="0" cellpadding="3">
	<tr >
		<td class="title">药品名称：</td>
		<td class="input"><input class="text" type="text" name="medicineName" value="${medicineName }">（模糊）</td>
		<td class="title">化学式：</td>
		<td class="input"><input class="text" type="text" name="medicineFormula" value="${medicineFormula }">（模糊）</td>
	</tr>
</table>
<br>
<table class="button">
	<tr>
		<td colspan="4" align="center"><input class="button" type="submit" id="but1" value="查 询"></td>
	</tr>
</table>
<br>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="11%">药品名称</td>
                <td width="11%">化学式</td>
                <td width="11%">生产商</td>
                <td width="11%">生产日期</td>
                <td width="11%">失效日期</td>
                <td width="11%">存放位置</td>
                <td width="11%">纯度/浓度</td>
                <td width="11%">备注</td>
                <td width="12%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="medicineList">
        	<s:iterator value="allQueryList" var="aql">
	        	<input type="hidden" name="medicineIdList" value="${aql.id }"><!-- 要回传至Action的id列表，用于生成Excel文件 -->
        	</s:iterator>
	        <s:iterator value="medicineList" var="me">
	            <tr align="center" class="TableDetail1 template">
	                <td>${me.name }</td>
	                <td>${me.chemFormula }</td>
	                <td>${me.manufacturer }</td>
	                <td><fmt:formatDate value="${me.manuDate}" pattern="yyyy年MM月dd日"/></td>
	                <td><fmt:formatDate value="${me.expireDate}" pattern="yyyy年MM月dd日"/></td>
	                <td>${me.position }</td>
	                <td>${me.concentration}</td>
	                <td><div class="hideText" style="width:10em">${me.reverse}</div></td>
	                <td>
	                	<a href="#" onclick="viewMediDetail('${me.id }')">详情</a>
		                <s:if test="#session.loginUser.hasThisPri('药品修改页面')">
	                    	<a href="editMedicinePage?medicineId=${me.id }&pageNo=${page.pageNo }">修改</a>
		                </s:if>
		                <s:if test="#session.loginUser.hasThisPri('药品删除')">
		                	<a onClick="return delConfirm()" href="deleteMediById?medicineId=${me.id }">删除</a>
		                </s:if>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('药品添加页面')">
	            <a href="editMedicinePage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
	        </s:if>
        </div>
    </div>
</div>
</form>
<!-- 提供分页的页面 -->
<%@include file="/page/publicPage/pageView.jsp" %>
<div>
	<input type="button" id="exportData" name="exportData" style=" cursor: pointer;" onclick="browseFolder()" value="导出为Excel">
</div>
<div id="Description">
	说明：<br />
		1、将查询结果导出为Excel文件时，请使用IE浏览器。<br/>
		2、点击“详情”，可查看该药品的详细信息。<br />
		3、用户只能看到自己输入的药品信息。
</div>
</body>
</html>
