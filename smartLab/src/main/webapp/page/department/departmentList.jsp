<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>部门列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div class="left"></div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">部门名称</td>
				<td width="200px">上级部门名称</td>
				<td>职能说明</td>
				<td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        	<s:iterator value="departmentList" var="de">
				<tr align="center" class="TableDetail1 template">
					<td><a href="findChildrenDepartList?parentId=${de.id }">${de.name}</a>&nbsp;</td>
					<td>${de.parent.name}&nbsp;</td>
					<td class="description">${de.description}&nbsp;</td>
					<td>
						<s:if test="#session.loginUser.hasThisPri('部门删除')">
							<a onClick="return window.confirm('这将删除本部门及所有的下级部门，您确定要删除吗？')" href="deleteById?id=${de.id }">删除</a>
						</s:if>
						<s:if test="#session.loginUser.hasThisPri('部门修改页面')">
							<a href="editDepartmentPage?id=${de.id}">修改</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
	<div id="TableTail">
		<div id="TableTail_inside">
    		<s:if test="#session.loginUser.hasThisPri('部门添加页面')">
	            <a href="createNewDepart?parentId=${parentId }"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></a>
    		</s:if>
	        <a href="lastLevel?id=${parentId }"><img src="${pageContext.request.contextPath}/style/blue/images/button/ReturnToPrevLevel2.png"></a>
		</div>
	</div>
    <div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
</div>
</div>
<div class="right"></div>
<!--说明-->	


</body>
</html>
