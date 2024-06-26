<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript">
    
    	//登录名称唯一性验证
	    var lNameFlag = false;
    	$(function(){
	    	var name1 = $('#loginName').val();
    		$('#loginName').blur(function(){
	    		var name2 = $('#loginName').val();
	    		if (name1.trim() == name2.trim() && name1.trim() !="") {
	    			lNameFlag = true;
	    		} else {
	    			if (name2.trim() == "") {
	    				$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>登录名不能为空！").css('color','red');
	    				return;
	    			}
	    			$("#showMsg").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
	    			$.post('validLoginName.action',{'loginName':$(this).val()},function(data){
                        var dataJSON = JSON.parse(data);
	    				if (dataJSON.description == "true") {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>登录名可以使用！").css('color','green');
	    					lNameFlag = true;
	    				} else {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>登录名已被使用！").css('color','red');
	    					lNameFlag = false;
	    					return;
	    				}
	    			});
	    		}
    		});
    	});
    	
    	//真实姓名非空验证
    	var rNameFlag = false;
    	$(function(){
    		$('#realName').blur(function(){
	    		var realName = $('#realName').val();
    			if (realName == "") {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>真实姓名不能为空！").css('color','red');
    				rNameFlag = false;
    				return;
    			} else {
    				$('#showMsg2').html("");
    				rNameFlag = true;
    			}
    		});
    	});
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对登录名和真实姓名做更改也没有使这两个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		var loginName = $('#loginName').val();
    		var realName = $('#realName').val();
    		if (loginName != "" && realName!= "") {
    			lNameFlag = true;
    			rNameFlag = true;
    		}
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			var flag = lNameFlag && rNameFlag;
    			if (flag) {
    				return true;
    			} else {
    				alert("表单数据未通过检测，请完善资料！");
    				return false;
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
            &emsp;&emsp;用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" action="register.action" method="post">
    	<input type="hidden" value="${user.id}" name="user.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        	<s:if test="#session.loginUser.getLoginName() == 'admin'">
	                        	<s:select theme="simple" cssClass="SelectStyle" name="departmentId" list="departmentList" listKey="id" listValue="name" headerKey="0" headerValue="请选择部门"></s:select> 
                        	</s:if>
                        	<s:else>
	                        	<s:select theme="simple" cssClass="SelectStyle" name="departmentId" list="departmentList" listKey="id" listValue="name" headerKey="0" headerValue="请选择部门" disabled="true"></s:select> 
                        	</s:else>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><input id="loginName" type="text" name="user.loginName" class="InputStyle" value="${user.loginName }"/> *
							<div id="showMsg"></div>
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><input id="realName" type="text" name="user.realName" class="InputStyle" value="${user.realName }"/> *
                        <div id="showMsg2"></div>
                        </td>
                    </tr>
                     <tr><td width="100">导师</td>
                        <td>
                        	<s:if test="#session.loginUser.getLoginName() == 'admin'">
	                        	<s:select theme="simple" cssClass="SelectStyle" name="supervisorId" list="supervisorList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择导师"></s:select> 
                        	</s:if>
                        	<s:else>
	                        	<s:select theme="simple" cssClass="SelectStyle" name="supervisorId" list="supervisorList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择导师" disabled="true"></s:select> 
                        	</s:else>
                        </td>
                    </tr>
					<tr><td>性别</td>
						<td>
                        	<s:radio theme="simple" name="user.gender" list="#{'0':'女','1':'男','2':'其他','3':'未知' }" value="user.gender"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><input id="phone" type="text" name="user.phone" class="InputStyle" value="${user.phone }"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><input id="email" type="text" name="user.email" class="InputStyle" value="${user.email }"/></td>
                    </tr>
                    <tr><td>备注</td>
                        <td><textarea name="user.description" class="TextareaStyle">${user.description }</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>
                        	<s:select name="roleIdList" multiple="true" size="10" theme="simple" cssClass="SelectStyle" list="roleList" listKey="id" listValue="name">
                            </s:select>
                                   按住Ctrl键可以多选或取消选择
                        </td>
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
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，新建用户后，会自动指定默认的头像。用户可以使用“个人设置→个人信息”功能修改自已的头像<br />
	6，修改用户信息时，登录名不可修改。
</div>

</body>
</html>
