<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@include file="/page/publicPage/head.jsp" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &nbsp;&nbsp;版块设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <form action="savaForum.action" method="post">
    <input name="forum.id" type="hidden" />
        <div class="ItemBlock_Title1"></div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">版块名称</td>
                        <td><input type="text" name="forum.name" class="InputStyle" value="${forum.name }" /> *</td>
                    </tr>
                    <tr>
                        <td>版块说明</td>
                        <td><textarea name="forum.description" class="TextareaStyle">${forum.description }</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="../style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="../style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
	说明：<br />
	1，新添加的版块默认显示在最下面。<br />
</div>

</body>
</html>

