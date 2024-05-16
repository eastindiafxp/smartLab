<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>导航菜单</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<script language="JavaScript" src="${pageContext.request.contextPath}/script/menu.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
	<style type="text/css">
		body.menuBody {
			background: url(${pageContext.request.contextPath}/style/images/menu_bg12.png) no-repeat;
		}
	</style>
</head>
<body style="margin: 0" class="menuBody">
<div id="Menu">
    <ul class="MenuLevel1">
    	<s:iterator value="#application.topPriList">
    		<!-- 从session中获取用户根据用户岗位获得用户权限，并判断与当前循环的权限是否一致 -->
    		<s:if test="#session.loginUser.hasThisPri(name)">
		        <li class="level1">
		            <div onClick="menuClick(this);" class="level1Style">&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/${id }.gif" class="Icon" /> ${name }</div>
		            <ul style="display: none;" class="MenuLevel2">
<%--						${children}--%>
		            	<s:iterator value="children">
			            	<s:if test="#session.loginUser.hasThisPri(name)">
				                <li class="level2">
				                    <div class="level2Style">
				                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/icon.png" />
				                    	<a target="right" href="${pageContext.request.contextPath}/${url }.action"> ${name }</a>
				                    </div>
				                </li>
				            </s:if>
		                </s:iterator>
		            </ul>
		        </li>
    		</s:if>
        </s:iterator>
        
        <!-- 公共显示部分start -->
    	<li class="level1">
    		<div onClick="menuClick(this);" class="level1Style">&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/FUNC20076.gif" class="Icon" /> 实用工具</div>
    		<ul style="display: none;" class="MenuLevel2">
				<li class="level2">
					<div class="level2Style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/icon.png" /><a target="_blank" href="https://www.jxuspt.com/"> 学校首页</a></div>
				</li>
                <li class="level2">
                    <div class="level2Style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/icon.png" /><a target="_blank" href="http://www.cnki.net/"> 中国知网</a></div>
                </li>
                <li class="level2">
                    <div class="level2Style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/icon.png" /><a target="_blank" href="http://qq.ip138.com/train/"> 火车时刻</a></div>
                </li>
                <li class="level2">
                    <div class="level2Style">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/style/images/MenuIcon/icon.png" /><a target="_blank" href="http://www.ip138.com/post/"> 邮编/区号</a></div>
                </li>
    		</ul>
    	</li>
        <!-- 公共显示部分end -->
    </ul>
</div>
</body>
</html>
