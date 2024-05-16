<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Bottom</title>
    <%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/statusbar.css" />
</head>
<body style="margin:0"> 

<div id="StatusBar">
    <div id="Online">
    	在线人员：共 1<span class="OnlineUser" id="onlineUserNum"></span> 人
<%--        <span class="OnlineView"><a href="javascript:void(0)">[查看在线名单]</a></span>--%>
    </div>
    <div id="Info">
    	<a class="web" href="https://www.jxuspt.com/" title = "江西软件大学首页" target="_blank">学校首页</a> |
        <a class="web" href="https://tieba.baidu.com/f?fr=home&kw=%E6%B1%9F%E8%A5%BF%E8%BD%AF%E4%BB%B6%E8%81%8C%E4%B8%9A%E6%8A%80%E6%9C%AF%E5%A4%A7%E5%AD%A6" title = "江西软件大学论坛" target="_blank">学校贴吧</a>
    </div>
<%--    <div id="DesktopText">--%>
<%--        <a href="javascript:void(0)"><img border="0" src="${pageContext.request.contextPath}/style/images/top/text.gif"/>便笺</a>--%>
<%--        <span id=TryoutInfo></span>--%>
<%--        <span id="Version">--%>
<%--            <a href="javascript:void(0)">--%>
<%--            	<img border="0" width="11" height="11" src="${pageContext.request.contextPath}/style/images/top/help.gif" />--%>
<%--                <img border="0" width="40" height="11" src="${pageContext.request.contextPath}/style/blue/images/top/version.gif" />--%>
<%--            </a>--%>
<%--        </span>--%>
<%--    </div>--%>
</div>

</body>
</html>
