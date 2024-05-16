<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>jQuery挂钟插件</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/clock/css/style.css">
	<script src="${pageContext.request.contextPath}/clock/js/prefixfree.min.js"></script>
</head>
<body>
<div class="support"></div>
<div class="clock">
	<div class="numbers">
		<ul>
			<li>12</li>
			<li>3</li>
			<li>6</li>
			<li>9</li>
		</ul>
	</div>
	<div class="clockwise">
		<div class="center"></div>
		<div class="hand-second" id="analogsecond"></div>
		<div class="hand-minute" id="analogminute"></div>
		<div class="hand-hour" id="analoghour"></div>
	</div>
</div>
<script src='${pageContext.request.contextPath}/clock/js/jquery.min.js'></script>
<script src="${pageContext.request.contextPath}/clock/js/index.js"></script>
</body>
</html>
<!--下面是无用代码-->
<a href="http://www.bootstrapmb.com" style="display:none">bootstrapmb</a>

