<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>阶段总结列表</title>
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
	  //'#startDate'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
		var start1 = {//此处写为start1而非start是因为当写为start时IE浏览器不能正常显示
			elem: '#summaryStartDate',
			format: 'YYYY-MM-DD hh:mm:ss',
			//min: laydate.now(), //设定最小日期为当前日期
			//max: '2099-06-16 23:59:59', //最大日期
			istime: true,
			istoday: false,//此值必须为false此功能才能正常使用
			choose: function(datas){
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		}
		//'#endDate'标签中的laydate()方法以的end为参数时，使开始日期无法选择比结束日期更晚的日期
		var end = {
			elem: '#summaryEndDate',
			format: 'YYYY-MM-DD hh:mm:ss',
			istime: true,
			istoday: false,//此值必须为false此功能才能正常使用
			choose: function(datas){
				start1.max = datas; //结束日选好后，重置开始日的最大日期
			}
		}
	  
    	//在对话框中查看总结的详细信息
    	function viewSummaryDetail(summaryId){
    		var url = "${pageContext.request.contextPath}/summary/viewSummaryDetail?summaryId=" + summaryId;
			var newWindow = window.open(url,"阶段总结详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//根据id修改总结
    	function editSummaryPage(id,pageNo) {
    		var url = "${pageContext.request.contextPath}/summary/editSummaryPage?summaryId=" + id + "&pageNo=" + pageNo;
    		queryForm.action = url;
			queryForm.submit();
    	}
    	
    	//根据id删除总结
    	function deleteSummaryById(id) {
    		if (window.confirm('删除操作不可恢复，确定要删除吗？')) {
	    		var url = "${pageContext.request.contextPath}/summary/deleteSummaryById?summaryId=" + id;
	    		queryForm.action = url;
				queryForm.submit();
    		} else {
    			return false;
    		}
    	}
    </script>
</head>
<body>
<form action="querySummaryList.action" id="pageForm" name="queryForm" method="post">
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;总结列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<table class="common" cellspacing="0" cellpadding="3">
	<tr >
		<td class="title">标题：</td>
		<td class="input"><input class="text" type="text" name="summaryTitle" value="${summaryTitle }">（模糊）</td>
		<td class="title">作者：</td>
		<td class="input">
			<s:select id="summaryAuthorId" theme="simple" cssClass="input" style="width:242px;height:25px;" name="summaryAuthorId" list="summaryAuthorList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择作者"></s:select>
		</td>
	</tr>
	<tr>
		<td class="title">总结日期(起始)：</td>
		<td class="input">
			<input id="summaryStartDate" type="text" style="width:216px" class="laydate-icon" name="summaryStartDate" value="${summaryStartDate }" onclick="laydate(start1)"/>
		</td>
		<td class="title">总结日期(截止)：</td>
		<td class="input">
			<input id="summaryEndDate" type="text" style="width:217px" class="laydate-icon" name="summaryEndDate" value="${summaryEndDate }" onclick="laydate(end)"/>
		</td>
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
                <td width="14%">标题</td>
                <td width="7%">作者</td>
                <td width="14%">总结日期</td>
                <td width="41%">总结1</td>
                <td width="10%">备注</td>
                <td width="16%">相关操作</td>
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="summaryList1">
	        <s:iterator value="allQueryList" var="aql">
	        	<input type="hidden" name="summaryIdList" value="${aql.id }"><!-- 要回传至Action的总结id列表，用于生成Excel文件 -->
	        </s:iterator>
	        <s:iterator value="summaryList1" var="su">
	            <tr align="center" class="TableDetail1 template">
<%--	                <td>${su }</td>--%>
	                <td>${su.title }</td>
	                <td>${su.author.loginName }</td>
	                <td><fmt:formatDate value="${su.summaryDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
	                <td><div class="hideText">${su.summary1 }</div></td>
	                <td>${su.reverse}</td>
	                <td>
	                	<input type="button" class="button" value="详情" onclick="viewSummaryDetail('${su.id }')">
		                <s:if test="#session.loginUser.getLoginName() != 'admin'">
				        	<s:if test="#session.loginUser.getId() == #su.author.id">
	                    		<input type="button" class="button" value="修改" onclick="editSummaryPage('${su.id }', '${page.pageNo }')">
	                    		<input type="button" class="button" value="删除" onclick="deleteSummaryById('${su.id }')">
				            </s:if>
				            <s:else>
			                    <input type="button" class="button1" value="修改">
			                    <input type="button" class="button1" value="删除">
				            </s:else>
			                <%-- <s:if test="#session.loginUser.getId() == #su.author.id">
	                    		<input type="button" class="button" value="删除" onclick="deleteSummaryById('${su.id }')">
			                </s:if>
			                <s:else>
			                    <input type="button" class="button1" value="删除">
				            </s:else> --%>
	                    </s:if>
	                    <s:else>
	                    	<input type="button" class="button" value="修改" onclick="editSummaryPage('${su.id }')">
	                    	<input type="button" class="button" value="删除" onclick="deleteSummaryById('${su.id }')">
	                    </s:else>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	    	<a href="editSummaryPage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
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
		1、点击“详情”，可查看该总结的详细信息。<br/>
		2、将查询结果导出为Excel文件时，请使用IE浏览器。<br/>
		3、特别注意的是，由于安全方面的问题，你还需要如下设置才能正常导出Excel文件，否者会出现"没有权限"的问题：<br/>
			&emsp;*首先，设置可信任站点（例如本地的可以为：http://localhost）；<br/>
			&emsp;*其次，在可信任站点安全级别自定义设置中设置下面的选项："对没有标记为安全的ActiveX控件进行初始化和脚本运行"----"启用"。 <br/>
</div>
</body>
</html>
