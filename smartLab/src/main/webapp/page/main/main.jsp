<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>主页面</title>
</head>
<frameset rows="100px,*,25px" framespacing="0" border="0">
	<frame name="top" noresize scrolling="no" src="${pageContext.request.contextPath}/main/topPage.action">
	<frameset cols="200px,*" id="resize">
		<frame noresize name="left" scrolling="yes" src="${pageContext.request.contextPath}/main/leftPage.action">
		<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/main/rightPage.action">
	</frameset>
	<frame noresize name="bottom" scrolling="no" src="${pageContext.request.contextPath}/main/bottomPage.action">
</frameset>
</html>