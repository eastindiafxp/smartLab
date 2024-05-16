<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="12%">登录名</td>
                <td width="12%">姓名</td>
<%--                <td width="12%">导师</td>--%>
                <td width="15%">所属部门</td>
                <td width="15%">岗位</td>
                <td width="19%">备注</td>
                <td width="15%">相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
	        <s:iterator value="userList" var="u">
	            <tr align="center" class="TableDetail1 template">
	                <td>${u.loginName}&nbsp;</td>
	                <td>${u.realName}&nbsp;</td>
<%--	                <td>${u.supervisor.loginName}&nbsp;</td>--%>
	                <td>${u.department.name}&nbsp;</td>
	                <td>
	                	<s:iterator value="roles" var="r">
	                		${r.name}&nbsp;
	                	</s:iterator>
	                </td>
	                <td class="description">${u.description}&nbsp;</td>
	                <td>
		                <s:if test="#session.loginUser.hasThisPri('用户删除')">
		                	<a onClick="return delConfirm('删除用户时会一并删除该用户所有的实验数据，请谨慎操作！')" href="delete?uid=${u.id }">删除</a>
		                </s:if>
		                <s:if test="#session.loginUser.hasThisPri('用户修改页面')">
	                    	<a href="editUserPage?uid=${u.id }">修改</a>
		                </s:if>
		                <s:if test="#session.loginUser.hasThisPri('用户初始化密码')">
							<a href="initPwd?uid=${u.id }" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</a>
		                </s:if>
	                </td>
	            </tr>
	        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
	        <s:if test="#session.loginUser.hasThisPri('用户添加页面')">
	            <a href="registerPage"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
	        </s:if>
        </div>
    </div>
</div>
<%@include file="/page/publicPage/pageView.jsp" %>
<!-- 提供分页用的表单 -->
<form id="pageForm" action="findUserList" method="post"></form>
</body>
</html>
