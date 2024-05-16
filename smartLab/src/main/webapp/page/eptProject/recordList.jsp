<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>记录列表</title>
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
    	//在对话框中查看实验的详细信息
    	function viewRecordDetail(rId){
    		var url = "${pageContext.request.contextPath}/experiment/toRecordDetail?recordId=" + rId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"记录详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//在对话框中查看溶液的详细信息
    	function viewSoluDetail(soluId){
    		var url = "${pageContext.request.contextPath}/solution/toSoluDetail?solutionId=" + soluId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"溶液详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//点击查询按钮触发的方法
    	function query(){
    		var url = "${pageContext.request.contextPath}/experiment/queryRecordList";
    		queryForm.action = url;
    		queryForm.submit();
    	}
    	
    	//点击修改按钮触发的方法
    	function editRecordPage(id, pageNo){
    		var url = "${pageContext.request.contextPath}/experiment/editRecordPage?recordId=" + id + "&pageNo=" + pageNo;
    		queryForm.action = url;
    		queryForm.submit();
    	}
    	
    	//点击删除按钮触发的方法
    	function deleteRecordById(id){
    		if (window.confirm('删除操作不可恢复，确定要删除吗？')) {
	    		var url = "${pageContext.request.contextPath}/experiment/deleteRecordById?recordId=" + id;
	    		queryForm.action = url;
	    		queryForm.submit();
    		} else {
    			return false;
    		}
    	}
    	
    	//查看样品详细信息
    	function viewSpecimenDetail(spId){
    		var url = "${pageContext.request.contextPath}/specimen/viewSpecimenDetail?specimenId=" + spId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"样品详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//查看被镀材料详细信息
    	function viewSubstrateDetail(medicineId){
    		var url = "${pageContext.request.contextPath}/medicine/viewMedicineDetail2?medicineId=" + medicineId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"被镀材料详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    </script>
</head>
<body>
<form action="queryRecordList.action" id="pageForm" name="queryForm" method="post">
<!-- <div style ="color:red" > -->
<%--     <s:fielderror /> --%>
<!-- </div > -->
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;记录列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="MainArea">
	<input type="hidden" name="experimentId" value="${experimentId }"/>
	<input type="hidden" name="projectId" value="${projectId }"/>
	<table class="common" cellspacing="0" cellpadding="3">
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="projAndExpeManagePage">项目列表</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="findExperimentList?projectId=${projectId }">${project5.projNo }</a>
			<font class="MenuPoint"> &gt; </font>
			${experiment.eptNo }
		</div>
		<tr >
			<td class="title">记录编号：</td>
			<td class="input"><input class="text" type="text" name="recordNo2" value="${recordNo2 }">（模糊）</td>
			<td class="title">记录名称：</td>
			<td class="input"><input class="text" type="text" name="recordName2" value="${recordName2 }">（模糊）</td>
		</tr>
	</table>
	<br>
	<table class="button">
		<tr>
			<td colspan="4" align="center"><input class="button" type="button" id="but1" value="查 询" onclick="query()"></td>
		</tr>
	</table>
	<br>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="9%">记录编号</td>
                <td width="13%">记录名称</td>
                <td width="13%">所用溶液</td>
                <td width="13%">被镀材料及用量</td>
                <td width="6%">pH值</td>
                <td width="6%">反应温度</td>
                <td width="8%">反应耗时</td>
                <td width="10%">结束日期</td>
                <td width="8%">样品标签</td>
                <td width="14%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="recordList">
        	<s:iterator value="allQueryList" var="aql">
	        	<input type="hidden" name="recordIdList" value="${aql.id }"><!-- 要回传至Action的id列表，用于生成Excel文件 -->
        	</s:iterator>
	        <s:iterator value="recordList" var="re">
	        	<s:set name="statusFlag" value="'out'"></s:set>
	            <tr align="center" class="TableDetail1 template">
	                <td>${re.recordNo }</td>
	                <td>${re.name }</td>
	                <td>
	                	<s:iterator value="solutions" var="sol">
	                		<a href="#" onclick="viewSoluDetail('${sol.id }')">${sol.name }</a>;<br/>
	                	</s:iterator>
	                </td>
	                <td><a href="#" onclick="viewSubstrateDetail(${re.substrate.id })">${re.substrate.name }</a>：${re.substrateMass }</td>
	                <td>${re.ph }</td>
	                <td>${re.temperature }</td>
	                <td>${re.reaTime }</td>
	                <td><fmt:formatDate value="${re.endDate}" pattern="yyyy年MM月dd日"/></td>
	                <td>
	                	<s:iterator value="specimens" var="sp">
	                		<a href="#" onclick="viewSpecimenDetail(${sp.id })">${sp.label }</a>
	                	</s:iterator>
	                </td>
	                <td>
	                    <input type="button" class="button" value="详情" onclick="viewRecordDetail('${re.id }')">
	                    <s:if test="#session.loginUser.getLoginName() != 'admin'">
			                <s:if test="#session.loginUser.hasThisPri('记录修改页面')">
			                	<s:iterator value="operaters" var="op">
					                <s:if test="#session.loginUser.getId() == #op.id">
					                	<s:set name="statusFlag" value="'in'"></s:set>
				                    	<input type="button" class="button" value="修改" onclick="editRecordPage('${re.id }', '${page.pageNo }')">
					                </s:if>
			                	</s:iterator>
			                	<s:if test="#statusFlag != 'in'">
			                		<input type="button" class="button1" style="padding-bottom: 22px;" value="修改">
			                	</s:if>
			                </s:if>
			                <s:if test="#session.loginUser.hasThisPri('记录删除')">
			                	<s:iterator value="operaters" var="op">
				                	<s:if test="#session.loginUser.getId() == #op.id">
				                		<s:set name="statusFlag" value="'in'"></s:set>
					                	<input type="button" class="button" value="删除" onclick="deleteRecordById('${re.id }')">
				                	</s:if>
			                	</s:iterator>
			                	<s:if test="#statusFlag != 'in'">
			                		<input type="button" class="button1" style="padding-bottom: 22px;" value="删除">
			                	</s:if>
			                </s:if>
	                    </s:if>
	                    <s:else>
		                    <input type="button" class="button" value="修改" onclick="editRecordPage('${re.id }', '${page.pageNo }')">
		                    <a onClick="return delConfirm()" href="deleteRecordById?recordId=${re.id }">
		                    	<input type="button" class="button" value="删除">
		                    </a>
	                    </s:else>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('记录添加页面')">
	            <a href="toCreateNewRecordPage?experimentId=${experimentId }"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
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
		2、用户只能看到自己同门师兄弟以及自己导师的记录列表信息，并且只对自己实施的记录有修改和删除权限。<br />
		3、点击“详情”，可查看该记录的详细情况。<br />
</div>
</body>
</html>
