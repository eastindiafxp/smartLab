<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>岗位列表</title>
	<%@include file="/page/publicPage/head.jsp" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div class="left"></div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">岗位名称</td>
                <td >岗位说明</td>
                <td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        	<s:iterator value="roleList" var="ro" >
				<tr align="center"  class="TableDetail1 template">
					<td>${ro.name}&nbsp;</td>
					<td class="description">${ro.description}&nbsp;</td>
					<td>
						<s:if test="#session.loginUser.hasThisPri('岗位删除')">
							<a onClick="return window.confirm('确定删除当前记录吗？')" href="deleteById?id=${ro.id}&pageNo2=${page.pageNo}">删除</a>
<%--							<!-- <s:a onClick="return window.confirm('确定删除当前记录吗？')" action="deleteById?id=%{ro.id}" namespace="/role" >删除</s:a> -->--%>
						</s:if>
						<s:if test="#session.loginUser.hasThisPri('岗位修改页面')">
							<a href="editRolePage?id=${ro.id}&pageNo3=${page.pageNo}">修改</a>
<%--							<!-- <s:a action="editRolePage?id=%{id}" namespace="/role" >修改</s:a> -->--%>
						</s:if>
						<s:if test="#session.loginUser.hasThisPri('设置权限')">
							<a href="setPrivilegePage?id=${ro.id}">设置权限</a>
<%--							<!-- <s:a action="setPrivilege?id=%{id}" namespace="/role">设置权限</s:a> -->--%>
						</s:if>
					</td>
				</tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('岗位添加页面')">
	            <a href="createNewRole.action"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
	        </s:if>
        </div>
    </div>
</div>
<div class="right"></div>
<%@include file="/page/publicPage/pageView.jsp" %>
<!-- 提供分页用的表单 -->
<form id="pageForm" action="findRoleList" method="post"></form>
</body>
</html>
