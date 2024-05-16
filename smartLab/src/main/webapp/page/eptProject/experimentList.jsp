<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>实验列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript">
    
    	//新建实验信息
    	function toCreateNewEptPage(eptId) {
    		var url = "${pageContext.request.contextPath}/experiment/toCreateNewEptPage?experimentId=" + eptId;
    		queryForm.action = url;
    		queryForm.submit();
    	}
    	
    	//在对话框中查看实验的详细信息
    	function viewEptDetail(eptId){
    		var url = "${pageContext.request.contextPath}/experiment/toEptDetail?experimentId=" + eptId;
			// var newWindow = window.showModalDialog(url,window,"dialogWidth:600px;dialogHeight:400px;status:no;edge:sunken");
			var newWindow = window.open(url,"实验详情","dialogWidth:400px;dialogHeight:300px;status:no;edge:sunken");
    	}
    	
    	//根据id修改实验信息
    	function editExperimentPage(eptId) {
    		var url = "${pageContext.request.contextPath}/experiment/editExperimentPage?experimentId=" + eptId;
    		queryForm.action = url;
    		queryForm.submit();
    	}
    	
    	//根据id删除实验信息
    	function deleteEptById(eptId) {
    		if (window.confirm('此操作将同时删除之下的所有实验记录，且不可恢复，确定要删除吗？')) {
	    		var url = "${pageContext.request.contextPath}/experiment/deleteEptById?experimentId=" + eptId;
	    		queryForm.action = url;
				queryForm.submit();
    		} else {
    			return false;
    		}
    	}
    </script>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;实验列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<form action="querySummaryList.action" id="pageForm" name="queryForm" method="post"></form>
<div id="MainArea">
	<input type="hidden" name="projectId" value="${projectId }"/>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       <div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="projAndExpeManagePage?pageNo=${page.pageNo }">项目列表</a>
			<font class="MenuPoint"> &gt; </font>
			${project4.projNo }
		</div>
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="8%">实验编号</td>
                <td width="15%">实验名称</td>
                <td width="25%">实验描述</td>
                <td width="8%">主要实施者</td>
                <td width="12%">开始日期</td>
                <td width="12%">结束日期</td>
                <td width="6%">记录数</td>
                <td width="14%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="experimentList">
	        <s:iterator value="experimentList" var="expt">
	            <tr align="center" class="TableDetail1 template">
	                <td><a href="findRecordList?experimentId=${expt.id }&projectId=${expt.project.id}">${expt.eptNo }</a></td>
	                <td>${expt.name }</td>
	                <td><div class="hideText" style="width:22em">${expt.description }</div></td>
	                <td>${expt.mainOperate.loginName }</td>
	                <td><fmt:formatDate value="${expt.startDate}" pattern="yyyy年MM月dd日"/></td>
	                <td><fmt:formatDate value="${expt.endDate}" pattern="yyyy年MM月dd日"/></td>
	                <td>${fn:length(expt.records)}</td>
	                <td>
	                    <input type="button" class="button" value="详情" onclick="viewEptDetail('${expt.id }')">
	                    <s:if test="#session.loginUser.getLoginName() != 'admin'">
			                <s:if test="#session.loginUser.hasThisPri('实验修改页面')">
				                <s:if test="#session.loginUser.getId() == #expt.mainOperate.id">
	                    			<input type="button" class="button" value="修改" onclick="editExperimentPage('${expt.id }')">
				                </s:if>
				                <s:else>
			                    	<input type="button" class="button1" style="padding-bottom: 22px;" value="修改">
				                </s:else>
			                </s:if>
			                <s:if test="#session.loginUser.hasThisPri('实验删除')">
			                	<s:if test="#session.loginUser.getId() == #expt.mainOperate.id">
	                    			<input type="button" class="button" value="删除" onclick="deleteEptById('${expt.id }')">
			                	</s:if>
			                	<s:else>
			                    	<input type="button" class="button1" style="padding-bottom: 22px;" value="删除">
				                </s:else>
			                </s:if>
	                    </s:if>
	                    <s:else>
	                    	<input type="button" class="button" value="修改" onclick="editExperimentPage('${expt.id }')">
	                    	<input type="button" class="button" value="删除" onclick="deleteEptById('${expt.id }')">
	                    </s:else>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('实验添加页面')">
	            <a href="#" onclick="toCreateNewEptPage(0)"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
	        </s:if>
        </div>
    </div>
</div>
<div id="Description">
	说明：<br />
		1、用户只能看到自己同门师兄弟以及自己导师的实验列表信息，并且只对自己实施的实验有修改和删除权限。<br />
		2、点击实验编号，可进入该实验相应具体记录列表。<br />
		3、点击“详情”，可查看该实验的详细情况。<br />
		4、删除实验时，将同时删除此实验旗下所有的记录信息。<br />
</div>
</body>
</html>
