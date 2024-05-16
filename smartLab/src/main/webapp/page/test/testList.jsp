<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>样品测试清单</title>
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
    	//在对话框中查看检测的详细信息
    	function viewTestDetail(testId){
    		var url = "${pageContext.request.contextPath}/test/toTestDetail?testId=" + testId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"检测信息","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//在对话框中查看样品的详细信息
    	function viewSpecDetail(specId){
    		var url = "${pageContext.request.contextPath}/specimen/viewSpecimenDetail?specimenId=" + specId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"样品信息","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
		//点击修改按钮触发的方法
    	function editTestPage(id, pageNo){
    		var url = "${pageContext.request.contextPath}/test/editTestPage?testId=" + id + "&pageNo=" + pageNo;
    		queryForm.action = url;
    		queryForm.submit();
    	}
    	
    	//点击删除按钮触发的方法
    	function deleteTestById(id){
    		if (window.confirm('删除操作不可恢复，确定要删除吗？')) {
	    		var url = "${pageContext.request.contextPath}/test/deleteTestById?testId=" + id;
	    		queryForm.action = url;
	    		queryForm.submit();
    		} else {
    			return false;
    		}
    	}
		
    	//'#testDate1'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
		var start1 = {//此处写为start1而非start是因为当写为start时IE浏览器不能正常显示
			elem: '#testDate1',
			format: 'YYYY-MM-DD',
			//min: laydate.now(), //设定最小日期为当前日期
			//max: '2099-06-16 23:59:59', //最大日期
			istime: true,
			istoday: false,//此值必须为false此功能才能正常使用
			choose: function(datas){
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		}
		//'#specimenDetectDate2'标签中的laydate()方法以的end为参数时，使开始日期无法选择比结束日期更晚的日期
		var end = {
			elem: '#testDate2',
			format: 'YYYY-MM-DD',
			istime: true,
			istoday: false,//此值必须为false此功能才能正常使用
			choose: function(datas){
				start1.max = datas; //结束日选好后，重置开始日的最大日期
			}
		}
    	
    </script>
</head>
<body>
<form action="queryTestList.action" id="pageForm" name="queryForm" method="post">
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;测试清单
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<table class="common" cellspacing="0" cellpadding="3">
	<tr>
		<td class="title">送检标签：</td>
		<td class="input"><input class="text" type="text" name="testLabel" value="${testLabel }">（模糊）</td>
		<td class="title">样品标签：</td>
		<td class="input"><input class="text" type="text" name="testSpecLabel" value="${testSpecLabel }">（模糊）</td>
	</tr>
	<tr>
		<td class="title">检测日期(起始)：</td>
		<td class="input"><input id="testDate1" type="text" style="width:216px" class="laydate-icon" name="testDate1" value="${testDate1 }" onclick="laydate(start1)"/></td>
		<td class="title">检测日期(截止)：</td>
		<td class="input"><input id="testDate2" type="text" style="width:216px" class="laydate-icon" name="testDate2" value="${testDate2 }" onclick="laydate(end)"/></td>
	</tr>
	<tr>
		<td class="title">检测手段：</td>
		<td class="input"><input class="text" type="text" name="testTestMethod" value="${testTestMethod }">（模糊）</td>
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
                <td width="10%">送检标签</td>
                <td width="10%">样品标签</td>
                <td width="10%">检测日期</td>
                <td width="10%">检测手段</td>
                <td width="25%">检测结果</td>
                <td width="20%">备注</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="testList">
        	<s:iterator value="allQueryList" var="te">
	        	<input type="hidden" name="testIdList" value="${te.id }"><!-- 要回传至Action的样品id列表，用于生成Excel文件 -->
        	</s:iterator>
	        <s:iterator value="testList" var="te">
<%-- 	        	<s:set name="statusFlag" value="'out'"></s:set> --%>
	            <tr align="center" class="TableDetail1 template">
	                <td>${te.label }</td>
	                <td><a href="#" onclick="viewSpecDetail('${te.specimen.id }')">${te.specimen.label }</a></td>
	                <td><fmt:formatDate value="${te.testDate }" pattern="yyyy年MM月dd日"/></td>
	                <td>${te.testMethod }</td>
	                <td><div class="hideText" style="width:23em">${te.testResult }</div></td>
	                <td><div class="hideText" style="width:18em">${te.reverse}</div></td>
	                <td>
	                    <input type="button" class="button" value="详情" onclick="viewTestDetail('${te.id }')">
	                    <s:if test="#session.loginUser.getLoginName() != 'admin'">
			                <s:if test="#session.loginUser.hasThisPri('检测修改页面')">
				                <s:if test="#session.loginUser.getId() == #te.owner.id">
	                    			<input type="button" class="button" value="修改" onclick="editTestPage('${te.id }', '${page.pageNo }')">
				                </s:if>
				                <s:else>
			                    	<input type="button" class="button1" style="padding-bottom: 22px;" value="修改">
				                </s:else>
			                </s:if>
			                <s:if test="#session.loginUser.hasThisPri('检测删除')">
			                	<s:if test="#session.loginUser.getId() == #te.owner.id">
	                    			<input type="button" class="button" value="删除" onclick="deleteTestById('${te.id }')">
			                	</s:if>
			                	<s:else>
			                    	<input type="button" class="button1" style="padding-bottom: 22px;" value="删除">
				                </s:else>
			                </s:if>
	                    </s:if>
	                    <s:else>
	                    	<input type="button" class="button" value="修改" onclick="editTestPage('${te.id }', '${page.pageNo }')">
	                    	<input type="button" class="button" value="删除" onclick="deleteTestById('${te.id }')">
	                    </s:else>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('检测添加页面')">
	            <a href="editTestPage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
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
		2、点击“详情”，可查看该样品检测的详细信息。<br/>
</div>
</body>
</html>
