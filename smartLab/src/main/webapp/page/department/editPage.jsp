<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>部门设置</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <!-- <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> -->
            &emsp;&emsp;部门信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <form action="save.action" method="post">
    	<input type="hidden" value="${department.id}" name="department.id"/>
        <div class="ItemBlock_Title1">信息说明
	        <div CLASS="ItemBlock_Title1">
	        	<img BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 新建/修改部门信息
	        </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">上级部门</td>
                        <td>
                            <s:select theme="simple" cssClass="SelectStyle" name="parentId" list="departmentList" listKey="id" listValue="name" headerKey="0" headerValue="请选择部门">
                            </s:select>
                        </td>
                    </tr>
                    <tr><td>部门名称</td>
                        <td><input type="text" name="department.name" class="InputStyle" value="${department.name }"/> *</td>
                    </tr>
                    <tr><td>职能说明</td>
                        <td><textarea name="department.description" class="TextareaStyle">${department.description }</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
	说明：<br />
	1，上级部门的列表是有层次结构的（树形）。<br/>
	2，如果是修改：上级部门列表中不能显示当前修改的部门及其子孙部门。因为不能选择自已或自已的子部门作为上级部门。<br />
</div>

</body>
</html>
