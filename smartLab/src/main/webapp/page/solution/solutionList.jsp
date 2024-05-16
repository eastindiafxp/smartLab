<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>溶液列表</title>
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
    	//在对话框中查看溶液的详细信息
    	function viewSoluDetail(soluId){
    		var url = "${pageContext.request.contextPath}/solution/toSoluDetail?solutionId=" + soluId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"溶液详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//在对话框中查看溶质的详细信息
    	function viewMedicineDetail(meId){
    		var url = "${pageContext.request.contextPath}/medicine/viewMedicineDetail2?medicineId=" + meId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"溶质详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    </script>
</head>
<body>
<form action="querySolutionList.action" id="pageForm" name="queryForm" method="post">
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;溶液列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<table class="common" cellspacing="0" cellpadding="3">
	<tr >
		<td class="title">溶液名称：</td>
		<td class="input"><input class="text" type="text" name="solutionName" value="${solutionName }">（模糊）</td>
		<td class="title">溶剂：</td>
		<td class="input"><input class="text" type="text" name="solutionSolvent" value="${solutionSolvent }">（模糊）</td>
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
                <td width="17%">溶液名称</td>
                <td width="24%">溶质及浓度</td>
                <td width="10%">溶剂</td>
                <td width="17%">使用剂量</td>
                <td width="17%">备注</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="solutionList">
        	<s:iterator value="allQueryList" var="aql">
        		<input type="hidden" name="solutionIdList" value="${aql.id }">
        	</s:iterator>
	        <s:iterator value="solutionList" var="so">
	            <tr align="center" class="TableDetail1 template">
	                <td>${so.name }</td>
	                <td>
	                	<s:iterator value="medicines" var="me">
	                		<a href="#" onclick="viewMedicineDetail(${me.id })">${me.name }</a>：${me.concentration }；<br/>
	                	</s:iterator>
	                </td>
	                <td>${so.solvent }</td>
	                <td>${so.dosage }</td>
	                <td><div class="hideText" style="width:15em">${so.reverse}</div></td>
	                <td>
	                    <a href="#" onclick="viewSoluDetail('${so.id }')">详情</a>
		                <s:if test="#session.loginUser.hasThisPri('溶液修改页面')">
	                    	<a href="editSolutionPage?solutionId=${so.id }&pageNo=${page.pageNo }">修改</a>
		                </s:if>
		                <s:if test="#session.loginUser.hasThisPri('溶液删除')">
		                	<a onClick="return delConfirm()" href="deleteSoluById?solutionId=${so.id }">删除</a>
		                </s:if>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('溶液添加页面')">
	            <a href="editSolutionPage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
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
		2、点击“详情”，可查看该溶液的详细信息。<br />
</div>
</body>
</html>
