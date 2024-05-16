<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录</title>
    <%@include file="/page/publicPage/head.jsp" %>
	<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
    <style type="text/css">

        *{
            margin: 0;
            padding: 0;
        }
        html,body{
            height: 100%;
            width: 100%;
        }
        form{
            height: 100%;
        }

        /*#CenterAreaBg{*/
        /*    height: 100%;*/
        /*    width: 100%;*/
        /*    !*background-image:url("../../style/images/jxsoftware.jpg") ;*!*/
        /*}*/
    </style>
	<script type="text/javascript">
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >
<form method="post" name="actForm" action="${pageContext.request.contextPath}/user/login.action">
    <div id="CenterAreaBg">
        <div id="CenterArea">
<%--            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>--%>
            <div id="LoginInfo">
                <table border=0 cellspacing=0 cellpadding=0 width=100%>
                    <tr>
                        <td width=45 class="Subject"><IMG border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
                        <td><input size="20" class="TextField" type="text" name="user.loginName"/></td><!--  autocomplete="off" -->
                        <td rowspan="2" style="padding-left:10px;"><input type="image" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif"/></td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td><input size="20" class="TextField" type="password" name="user.password"/></td><!--  autocomplete="off" -->
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
			            	<font color="red">
			            		${errorMsg }
			            	</font>
                        </td>
                        
                    </tr>
                </table>
            </div>
            <div id="CopyRight"><a href="javascript:void(0)">&copy; 2024 版权所有[张博洋]</a></div>
        </div>
<%--        <video autoplay loop style="width:100%;">--%>
<%--        	<source src="${pageContext.request.contextPath}/style/images/videos/chang'e No.3.mp4">--%>
<%--        </video>--%>
    </div>
</form>
</body>

</html>

