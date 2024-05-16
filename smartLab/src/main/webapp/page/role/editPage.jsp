<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>岗位设置</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>
<body> 
<input name="pageNo" type="hidden" value="pageNo"/>
<input name="pageNo3" type="hidden" value="pageNo3"/>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <!-- <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> -->
            &emsp;&emsp;岗位设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <form action="saveRole.action" method="post">
        <div class="ItemBlock_Title1"> 信息说明
	        <div CLASS="ItemBlock_Title1">
	        	<img BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 新建/修改岗位信息 
	        </div>
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">岗位名称</td>
                        <td><input type="text" name="role.name" class="InputStyle" value="${role.name }" /> *</td>
                    </tr>
                    <tr>
                        <td>岗位说明</td>
                        <td><textarea name="role.description" class="TextareaStyle">${role.description }</textarea></td>
                    </tr>
                    <tr>
                    	<td><input name="role.id" type="hidden" value="${role.id }"></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input class="save" type="image" src="${pageContext.request.contextPath}/style/images/save.png" value="保存"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

</body>
</html>
