<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>配置权限</title>
	<%@include file="/page/publicPage/head.jsp" %>
	<!-- <script type="text/javascript" src="/style/jquery_treeview/lib/jquery.js"></script> 上句已经引入JQuery-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/style/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/jquery_treeview/jquery.treeview.css" />
	<script type="text/javascript">
		$(function(){
			//为所有的复选框绑定单击事件
			$('input[name=privilegeIds]').click(function(){
				//当选中某个权限时，同时选中其下级，取消时效果相似
				$(this).siblings('ul').find('input').attr('checked',this.checked);
				//当选择某个权限时，同时选中其上级权限
				if (this.checked) {
					$(this).parents('li').children('input').attr('checked',true);
				} else {
					//取消某个权限时，若同级别其他权限亦未被选中，则同时取消其上级权限
					var size = $(this).parent('li').siblings('li').children('input:checked').size();
					if (size == 0) {
						$(this).parent().parent().siblings('input').attr('checked',false);
					}
				}
				
			});
		});
	</script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <!-- <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> -->
            &emsp;&emsp;配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="setPrivilege.action" method="post">
    	<input type="hidden" name="roleId" value="${role.id }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${role.name }】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<input type="checkbox" id="cbSelectAll" onClick="$('input[name=privilegeIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData1">
							<tr class="TableDetail11">
								<!-- 显示权限树 -->
								<td>
									<!-- <s:checkboxlist name="privilegeIds" list="privilegeList" listKey="id" listValue="name" ></s:checkboxlist> -->
									<!-- 
									<s:iterator value="privilegeList">
										<input 
											<s:property value="id in privilegeIds?'checked':''" />
											value="${id }" type="checkbox" name="privilegeIds" >${name }<br>
									</s:iterator>
									 -->
									 <ul id="privilegeTree" class="filetree">
										 <s:iterator value="privilegeList">
										 	<li>
										 		<input id="cb_${id }"
													<s:property value="id in privilegeIds?'checked':''" />
													value="${id }" type="checkbox" name="privilegeIds" >
													<label for="cb_${id }">
														<span id="cb_${id }" class="folder">${name }</span>
													</label>
													<ul>
														<s:iterator value="children">
															<li>
																<input id="cb_${id }"
																	<s:property value="id in privilegeIds?'checked':''" />
																	value="${id }" type="checkbox" name="privilegeIds" >
																	<label for="cb_${id }">
																		<span id="cb_${id }" class="folder">${name }</span>
																	</label>
																	<ul>
																		<s:iterator value="children">
																			<li>
																				<input id="cb_${id }"
																					<s:property value="id in privilegeIds?'checked':''" />
																					value="${id }" type="checkbox" name="privilegeIds" >
																					<label for="cb_${id }">
																						<span id="cb_${id }" class="folder">${name }</span>
																					</label>
																			</li>
																		</s:iterator>
																	</ul>
															</li>
														</s:iterator>
													</ul>
										 	</li>
										 </s:iterator>
									 </ul>
								</td>
							</tr>
					</tbody>
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
	1，选中一个权限时：<br />
	&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
	&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
	&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>
<script type="text/javascript">
	$("#privilegeTree").treeview();
</script>
</body>
</html>
