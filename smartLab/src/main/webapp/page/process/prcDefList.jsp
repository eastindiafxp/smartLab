<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>审批流程列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript">
    	function showProcessImage(id){
    		var url = "${pageContext.request.contextPath}/process/showProImage.action?id=" + id;
    		window.showModalDialog(url);
    	}
    </script> 
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;流程管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">流程名称</td>
				<td width="80px">最新版本</td>
                <td>说明</td>
                <td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="processDefList">
        	<s:iterator value="lastProList" var="prc">
				<tr class="TableDetail1 template">
						<td align="center">${prc.name}&nbsp;</td>
						<td align="CENTER">${prc.version}&nbsp;</td>
						<td>${prc.description}&nbsp;</td>
						<td align="center">
							<a onClick="return delConfirm()" href="deleteByKey?key=${prc.key }">删除</a>
							<a href="javascript:showProcessImage('${prc.id}')">查看流程图</a>
						</td>
				</tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <table border="0" cellspacing="0" cellpadding="10" align="left">
                <tr>
			        <td><div class="FuncBtn">
                            <div class=FuncBtnHead></div>
                            <div class=FuncBtnMemo>
                            	<a href="prcDeployPage.action">部署流程定义文档</a>
                            </div>
                            <div class=FuncBtnTail></div>
                        </div></td>
                </tr>
			</table>
        </div>
    </div>
</div>

<div class="Description">
	说明：<br />
	1，列表显示的是所有流程定义（不同名称）的最新版本。<br />
	2，删除流程定义时，此名称的所有版本的流程定义都会被删除。<br />
</div>

</body>
</html>

