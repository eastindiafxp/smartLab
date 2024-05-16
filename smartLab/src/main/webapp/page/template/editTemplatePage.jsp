<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>文档模板</title>
	<%@include file="/page/publicPage/head.jsp" %>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &nbsp;&nbsp;&nbsp;文档模板
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="saveTemplate.action" method="post"  enctype="multipart/form-data" >
    	<input type="hidden" name="template.id" value="template.id">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 模板基本信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
				   	<tr>
                        <td>模板名称</td>
                        <td><input type="TEXT" name="template.name" class="InputStyle" />  *</td>
                    </tr>
                    <tr>
                        <td>所用流程</td>
                        <td>
							<s:select theme="simple" name="template.prcDefKey" list="proDefList" listKey="key" listValue="key" cssClass="SelectStyle">
							</s:select> *
						</td>
                    </tr>
                </table>
            </div>
        </div>
		
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 模板文件 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
				   	<tr>
                        <td width="120px;">请选择文件(doc格式)</td>
                        <td><input type="file" name="resource" class="InputStyle" style="width:450px;" /> </td>
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
	1，模板文件是doc扩展名的文件（Word文档）。<br />
	2，如果是添加：必须要选择模板文件。<br />
	3，如果是修改：只是在 需要更新模板文件时 才选择一个文件。
</div>

</body>
</html>
