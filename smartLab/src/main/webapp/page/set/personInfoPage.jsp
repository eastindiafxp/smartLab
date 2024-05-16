<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript">
    
    	//真实姓名非空验证
    	var rNameFlag = false;
    	$(function(){
    		$('#realName').blur(function(){
	    		var realName = $('#realName').val();
    			if (realName == "") {
    				$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>真实姓名不能为空！").css('color','red');
    				rNameFlag = false;
    				return;
    			} else {
    				$('#showMsg').html("");
    				rNameFlag = true;
    			}
    		});
    	});
    	
    	/*当点击修改按钮进入修改页面，但是并未对真实姓名做更改也没有使这个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		var realName = $('#realName').val();
    		if (realName!= "") {
    			rNameFlag = true;
    		}
    	});
    	
    	//浏览器可能会记住密码，此方法使得窗口加载时使密码框只显示一个空格，而不是记住的密码
    	//window.onload = function(){
    		//document.getElementById('oldPwd').value = '';
    	//}
    	
    	//显示或隐藏修改密码操作区
    	var isHidePwdFlag = true;//当密码区处于隐藏状态时，不对密码进行校验和更新
    	function showPwd(){
    		var showBtn = $('#showBtn'); 
    		var showBtnVal = showBtn.val(); 
    		var pwdDiv = $('#pwdDiv');
    		//pwdDiv.style.display==""?"none":"";
    		if (showBtnVal == "显示密码区") {
    			//showBtn.value = "隐藏密码区";
    			document.getElementById("showBtn").value="隐藏密码区";
    			document.getElementById("pwdDiv").style.display = "";
    			isHidePwdFlag = false;
    		} else {
    			//showBtn.value = "显示密码区";
    			document.getElementById("showBtn").value="显示密码区";
    			document.getElementById("pwdDiv").style.display = "none";
    			isHidePwdFlag = true;
    		}
    	}
    	
    	//新旧密码验证
    	var oldPwdFlag = false;
    	var newPwdFlag = false;
    	$(function(){
    		//旧密码非空验证、正确性验证
    		$('#oldPwd').blur(function(){
	    		var oldPwd = $('#oldPwd').val();
    			if (oldPwd == "") {
    				$('#oldPwdShowMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>密码不能为空！").css('color','red');
    				oldPwdFlag = false;
    			} else {
    				$.post('validOldPwd.action',{'oldPwd':$('#oldPwd').val()},function(data){
                        var dataJSON = JSON.parse(data);
                        if (dataJSON.reverse == "true") {
        				// if (data == "true") {
        					$('#oldPwdShowMsg').html("");
        					oldPwdFlag = true;
        				} else {
        					$('#oldPwdShowMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>密码错误！").css('color','red');
        					oldPwdFlag = false;
        				}
        			});
    			}
    		});
    		
    		//新密码验证
    		//新密码非空性验证
    		$('#newPwd').blur(function(){
	    		var newPwd = $('#newPwd').val();
    			if (newPwd == null || newPwd == "") {
	    			$('#newPwdShowMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>新密码不能为空！").css('color','red');
	    			newPwdFlag = false;
    			} else {
	    			$('#newPwdShowMsg').html("");
    			}
    		});
    		//新密码一致性验证
	    	$('#newPwd2').blur(function(){
	    		var newPwd = $('#newPwd').val();
	    		var newPwd2 = $('#newPwd2').val();
	    		if (newPwd != null || newPwd != "") {
		    		if (newPwd != newPwd2) {
		    			$('#newPwdShowMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>两次输入的密码不一致！").css('color','red');
		    			newPwdFlag = false;
		    		} else {
		    			$('#newPwdShowMsg2').html("");
		    			newPwdFlag = true;
		    		}
	    		}
	    	});
    		
    	});
    	
    	//表单验证
    	function validTable(){
    		//根据是否需要验证密码区的信息而判断最终的表单验证方案
    		if (isHidePwdFlag) {
				var flag = rNameFlag;
			} else {
				var flag = rNameFlag && oldPwdFlag && newPwdFlag;
			}
			
			if (flag) {
				var url = "${pageContext.request.contextPath}/set/savePInfo.action?isModifyPwd=" + !isHidePwdFlag;
				pInfoForm.action = url;
				pInfoForm.submit();
				return true;
			} else {
				alert("表单数据未通过检测，请完善资料！");
				return false;
			}
    	}
    	
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="pInfoForm" method="post">
    	<input type="hidden" value="${user.id}" name="user.id" id="id"/>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门：</td>
                        <td>
                        	<s:select theme="simple" cssClass="SelectStyle" name="departmentId" list="departmentList" listKey="id" listValue="name" headerKey="0" headerValue="请选择部门" disabled="true"></s:select>（不可更改）
                        </td>
                    </tr>
                    <tr><td>登录名：</td>
                        <td><input id="loginName" type="text" name="user.loginName" class="InputStyle" value="${user.loginName }" disabled="true"/> （不可更改）
						</td>
                    </tr>
                    <tr><td>姓名：</td>
                        <td><input id="realName" type="text" name="user.realName" class="InputStyle" value="${user.realName }" autocomplete="off"/>&nbsp;*
                        	<div id="showMsg"></div>
                        </td>
                    </tr>
                    <tr><td width="100">导师</td>
                        <td>
	                        <s:select theme="simple" cssClass="SelectStyle" name="supervisorId" list="supervisorList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择导师" disabled="true"></s:select> 
	                         （不可更改）
                        </td>
                    </tr>
					<tr><td>性别：</td>
						<td>
                        	<s:radio theme="simple" name="user.gender" list="#{'0':'女','1':'男','2':'其他','3':'未知' }" value="user.gender"></s:radio>
						</td>
                    </tr>
					<tr><td>联系电话：</td>
                        <td><input id="phone" type="text" name="user.phone" class="InputStyle" value="${user.phone }" autocomplete="off"/></td>
                    </tr>
                    <tr><td>E-mail：</td>
                        <td><input id="email" type="text" name="user.email" class="InputStyle" value="${user.email }" autocomplete="off"/></td>
                    </tr>
                    <tr><td>备注：</td>
                        <td><textarea name="user.description" class="TextareaStyle">${user.description }</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改登录密码 
				<tr>
                	<td><input id="showBtn" name="showBtn" type="button" value="显示密码区" onclick="showPwd()">&nbsp;（当密码区处于隐藏状态时，不对密码进行校验和更新）</td>
                </tr>
        	</div>
        </div>
        
        <div style="display:none;" class="ItemBlockBorder" id="pwdDiv" name="pwdDiv">
        	<div class="ItemBlock">
        		<table cellpadding="0" cellspacing="0" class="mainForm">
			    	<tr><td>旧密码：</td>
			        	<td><input id="oldPwd" type="password" name="oldPwd" class="InputStyle" autocomplete="off"/>&nbsp;*
							<span id="oldPwdShowMsg"></span>
						</td>
					</tr>
			        <tr><td>新密码：</td>
			        	<td><input id="newPwd" type="password" name="newPwd" class="InputStyle" autocomplete="off"/>&nbsp;*
			        		<span id="newPwdShowMsg"></span>
			            </td>
			        </tr>
			        <tr><td width="37px">再次输入新密码：</td>
			        	<td width="1px"><input id="newPwd2" type="password" name="newPwd2" class="InputStyle" autocomplete="off"/>&nbsp;*
			            	<span id="newPwdShowMsg2"></span>
			            </td>
			    	</tr>
				</table>
           	</div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位：</td>
                        <td>
                        	<s:select  name="roleIdList" multiple="true" size="10" theme="simple" cssClass="SelectStyle" list="roleList" listKey="id" listValue="name" disabled="true">
                            </s:select> （不可更改）
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png" onclick="validTable()"/>
        </div>
    </form>
</div>

<div class="Description">
	说明：<br />
	1、若要修改所属部门、登录名、导师、岗位这四项，请联系各自实验室管理员。<br/>
	2、“*”为必填项。<br/>
	3、当密码区处于隐藏状态时，不对密码进行校验和更新。<br/>
</div>

</body>
</html>

