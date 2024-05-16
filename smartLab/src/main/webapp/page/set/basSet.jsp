<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>设置列表</title>
    <%@include file="/page/publicPage/head.jsp" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;系统设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<h2>页面设置：</h2>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">名称</td>
				<td width="200px">设置值</td>
				<td width="200px">是否有效</td>
				<td>备注</td>
				<td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示页面数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="pageSetList">
        	<s:iterator value="pageSetList" var="pSet">
				<tr align="center" class="TableDetail1 template">
					<td>${pSet.id.configName}&nbsp;</td>
					<td>
						<input id="num_${pSet.id.configName }" type="text" size="3" maxlength="2" name="configValue" style="text-align:center;" value="${pSet.id.configValue }"/>&nbsp;
					</td>
					<td>
						<s:if test="#pSet.validStatus == '00'">
							无效
						</s:if>
						<s:else>
							有效
						</s:else>
						<!-- <s:radio theme="simple" list="#{'00':'无效','01':'有效' }" name="pSet.validStatus" value="pSet.validStatus"></s:radio> -->
					</td>
					<td align="left">${pSet.reverse1}&nbsp;</td>
					<td>
						<a href="javascript:location='savePageSet?configName=${pSet.id.configName}&num=' + document.getElementById('num_${pSet.id.configName}').value">确认修改</a>
						<a onClick="return window.confirm('您确定要恢复默认值“10”吗？')" href="resetBasicSet?configName=${pSet.id.configName}">恢复默认值</a>
					</td>
				</tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 表格下方的加粗线 -->
    <div id="TableTail" style="border-bottom: 1px solid #F3F9FD;height: 3px;">
    </div>
</div>

<h2>会话设置：</h2>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="200px">名称</td>
				<td width="200px">设置值</td>
				<td width="200px">是否有效</td>
				<td>备注</td>
				<td width="200px">相关操作</td>
            </tr>
        </thead>

		<!--显示会话session数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="sessionSetList">
        	<s:iterator value="sessionSetList" var="sSet">
				<tr align="center" class="TableDetail1 template">
					<td>${sSet.id.configName}&nbsp;</td>
					<td>
						<input id="num_${sSet.id.configName }" type="text" size="4" maxlength="4" name="configValue" style="text-align:center;" value="${sSet.id.configValue }"/>&nbsp;
					</td>
					<td>
						<s:if test="#sSet.validStatus == '00'">
							无效
						</s:if>
						<s:else>
							有效
						</s:else>
						<!-- <s:radio theme="simple" list="#{'00':'无效','01':'有效' }" name="sSet.validStatus" value="pSet.validStatus"></s:radio> -->
					</td>
					<td align="left">${sSet.reverse1}&nbsp;</td>
					<td>
						<a href="javascript:location='savePageSet?configName=${sSet.id.configName}&num=' + document.getElementById('num_${sSet.id.configName}').value">确认修改</a>
						<a onClick="return window.confirm('您确定要恢复默认值“600秒”吗？')" href="resetBasicSet?configName=${sSet.id.configName}">恢复默认值</a>
					</td>
				</tr>
        	</s:iterator>
        </tbody>
    </table>
    
    <!-- 表格下方的加粗线 -->
    <div id="TableTail" style="border-bottom: 1px solid #F3F9FD;height: 3px;">
    </div>
</div>

</body>
</html>
