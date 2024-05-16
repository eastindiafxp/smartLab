<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>样品列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <style type="text/css">
    	#exportData{
    		position:relative;
			top:6px;
			left:20px;
			background-color: #a2fe7e;
    	}
    </style>
    <script type="text/javascript">
    	//在对话框中查看样品的详细信息
    	function viewSpecDetail(specId){
    		var url = "${pageContext.request.contextPath}/specimen/viewSpecimenDetail?specimenId=" + specId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"样品详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//在对话框中查看检测的详细信息
    	function viewTestDetail(testId){
    		var url = "${pageContext.request.contextPath}/test/toTestDetail?testId=" + testId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"检测信息","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
//     	//'#specimenDetectDate1'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
// 		var start1 = {//此处写为start1而非start是因为当写为start时IE浏览器不能正常显示
// 			elem: '#specimenDetectDate1',
// 			format: 'YYYY-MM-DD',
// 			//min: laydate.now(), //设定最小日期为当前日期
// 			//max: '2099-06-16 23:59:59', //最大日期
// 			istime: true,
// 			istoday: false,//此值必须为false此功能才能正常使用
// 			choose: function(datas){
// 				end.min = datas; //开始日选好后，重置结束日的最小日期
// 				end.start = datas //将结束日的初始值设定为开始日
// 			}
// 		}
// 		//'#specimenDetectDate2'标签中的laydate()方法以的end为参数时，使开始日期无法选择比结束日期更晚的日期
// 		var end = {
// 			elem: '#specimenDetectDate2',
// 			format: 'YYYY-MM-DD',
// 			istime: true,
// 			istoday: false,//此值必须为false此功能才能正常使用
// 			choose: function(datas){
// 				start1.max = datas; //结束日选好后，重置开始日的最大日期
// 			}
// 		}
    	
    </script>
</head>
<body>
<form action="querySpecimenList.action" id="pageForm" name="queryForm" method="post">
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;样品列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<table class="common" cellspacing="0" cellpadding="3">
	<tr>
		<td class="title">样品标签：</td>
		<td class="input"><input class="text" type="text" name="specimenLabel" value="${specimenLabel }">（模糊）</td>
		<td></td>
<!-- 		<td class="title">送检标签：</td> -->
<%-- 		<td class="input"><input class="text" type="text" name="specimenDetectLabel" value="${specimenDetectLabel }">（模糊）</td> --%>
	</tr>
<!-- 	<tr> -->
<!-- 		<td class="title">检测日期(起始)：</td> -->
<%-- 		<td class="input"><input id="specimenDetectDate1" type="text" style="width:216px" class="laydate-icon" name="specimenDetectDate1" value="${specimenDetectDate1 }" onclick="laydate(start1)"/></td> --%>
<!-- 		<td class="title">检测日期(截止)：</td> -->
<%-- 		<td class="input"><input id="specimenDetectDate2" type="text" style="width:216px" class="laydate-icon" name="specimenDetectDate2" value="${specimenDetectDate2 }" onclick="laydate(end)"/></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td class="title">检测手段：</td> -->
<%-- 		<td class="input"><input class="text" type="text" name="specimenDetectMean" value="${specimenDetectMean }">（模糊）</td> --%>
<!-- 	</tr> -->
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
                <td width="10%">样品标签</td>
                <td width="15%">送检标签</td>
                <td width="15%">所属记录</td>
                <td width="45%">备注</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="specimenList">
        	<s:iterator value="allQueryList" var="spe">
	        	<input type="hidden" name="specimenIdList" value="${spe.id }"><!-- 要回传至Action的样品id列表，用于生成Excel文件 -->
        	</s:iterator>
	        <s:iterator value="specimenList" var="sp">
	            <tr align="center" class="TableDetail1 template">
	                <td>${sp.label }</td>
	                <td>
		                <s:iterator value="tests" var="t">
		                	<a href="#" onclick="viewTestDetail(${t.id })">${t.label };</a>
		                </s:iterator>
	                </td>
	                <td>${sp.reverse1 }</td>
	                <td><div class="hideText" style="width:40em">${sp.reverse}</div></td>
	                <td>
	                    <a href="#" onclick="viewSpecDetail('${sp.id }')">详情</a>
		                <s:if test="#session.loginUser.hasThisPri('样品修改页面')">
	                    	<a href="editSpecimenPage?specimenId=${sp.id }&pageNo=${page.pageNo }">修改</a>
		                </s:if>
		                <s:if test="#session.loginUser.hasThisPri('样品删除')">
		                	<a onClick="return delConfirm()" href="deleteSpecById?specimenId=${sp.id }">删除</a>
		                </s:if>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('样品添加页面')">
	            <a href="editSpecimenPage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
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
	说明：<br/>
		1、将查询结果导出为Excel文件时，请使用IE浏览器。<br/>
		2、点击“详情”，可查看该样品的详细信息。<br/>
</div>
</body>
</html>
