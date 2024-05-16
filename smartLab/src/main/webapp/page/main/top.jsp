<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Top</title>
    <%@include file="/page/publicPage/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/top.css" />
	<script language="Javascript" type="text/javascript"> 
        window.onload = function (){
  			setInterval("timer.value=new Date().toLocaleString()",1000); 
		}
</script>
</head>

<body class="PageBody" style="margin: 0">

	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font style="color:black; font-size:28px; font-family:Arial Black, Arial">江西软件大学实验室管理系统</font>
			<!--<img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" />-->
        </div>
		
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <!-- <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" /> --> <font style="color:gray;">您好，<b>${sessionScope.loginUser.loginName }&nbsp;&nbsp;</b></font>
                <input type="text" id="timer" disabled="false" style="width:158px"/>
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_UserSetup">
            	<a target="right" href="${pageContext.request.contextPath}/set/personSet.action">
					<img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user_setup.gif" /> 个人设置
				</a>
			</div>
			<div id="Head1Right_Time"></div>
		</div>
		
        <div id="Head1Right_SystemButton">
            <a target="_parent" href="${pageContext.request.contextPath}/user/logout.action">
				<img width="20" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout5.png" />注销登录
			</a>
        </div>
		
        <div id="Head1Right_Button">
            <a target="right" href="${pageContext.request.contextPath}/main/desktop.action">
				<img width="80" height="80" alt="显示桌面" src="${pageContext.request.contextPath}/style/blue/images/top/desktop.png" />
			</a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                <li><a target="desktop" href="javascript:void(0)">
						<img border="0" width="11" height="13" src="${pageContext.request.contextPath}/style/images/top/msg.gif" /> 消息
						<span id="msg"></span>
					</a>
				</li>
                <li class="Line"></li>
                <li><a target="desktop" href="javascript:void(0)">
						<img border="0" width="16" height="11" src="${pageContext.request.contextPath}/style/images/top/mail.gif" /> 邮件
						<span id="mail"></span>
					</a>
				</li>
                <li class="Line"></li>
				  <!-- 是否有待审批文档的提示1，数量 -->
                <li><a href="Flow_Formflow/myTaskList.html" target="desktop">
                		<img border="0" width="12" height="14" src="${pageContext.request.contextPath}/style/images/top/wait.gif" /> 
                		待办事项（<span id="wait" class="taskListSize">1</span>）
                	</a>
                </li>
				  
                <!-- 是否有待审批文档的提示2，提示审批 -->
                <li id="messageArea">您有 1 个待审批文档，请及时审批！★★★★★</li>
            </ul>
        </div>
        
		<div id="Head2_FunctionList">
			<marquee style="WIDTH: 93%;" onMouseOver="this.stop()" onMouseOut="this.start()" 
				scrollamount=1 scrolldelay=30 direction=left>
				<b>欢迎使用！</b>
			</marquee>
		</div>
	</div>

</body>
</html>
