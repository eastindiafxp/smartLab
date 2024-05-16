<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>项目信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//项目编号非空性与唯一性验证
	    var projNoFlag = false;
    	$(function(){
	    	var projNo1 = $('#projectNo').val().trim();
    		$('#projectNo').blur(function(){
	    		var projNo2 = $('#projectNo').val().trim();
	    		if (projNo1 == projNo2 && projNo1 !="") {
	    			$('#showMsg').html("");
	    			projNoFlag = true;
	    		} else {
	    			if (projNo2 == "") {
	    				$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>项目编号不能为空！").css('color','red');
	    				projNoFlag = false;
	    				return;
	    			}
	    			$("#showMsg").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
	    			$.post('validProjectNo.action',{'projectNo':$(this).val()},function(data){

                        var dataJSON = JSON.parse(data);
	    				if (dataJSON.reverse == "true") {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>该编号可以使用！").css('color','green');
	    					projNoFlag = true;
	    				} else {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>该编号已被使用！").css('color','red');
	    					projNoFlag = false;
	    				}
	    			});
	    		}
    		});
    	});
    	
    	//项目名称非空验证
    	var nameFlag = false;
    	$(function(){
    		$('#name').blur(function(){
	    		var name = $('#name').val().trim();
    			if (name == "" || name == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>项目名称不能为空！").css('color','red');
    				nameFlag = false;
    			} else {
    				$('#showMsg2').html("");
    				nameFlag = true;
    			}
    		});
    	});
    	
    	//'#startDate'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
    	var start1 = {
    		elem: '#startDate',
    		format: 'YYYY-MM-DD',
    		//min: laydate.now(), //设定最小日期为当前日期
    		//max: '2099-06-16 23:59:59', //最大日期
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
    		choose: function(datas){
    			end.min = datas; //开始日选好后，重置结束日的最小日期
    			end.start1 = datas //将结束日的初始值设定为开始日
    		}
		};
    	//'#endDate'标签中的laydate()方法以的end为参数时，使开始日期无法选择比结束日期更晚的日期
    	var end = {
    		elem: '#endDate',
    		format: 'YYYY-MM-DD',
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
    		choose: function(datas){
    			start1.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};

    	//项目申请人非空验证
    	var claimerFlag = false;
    	$(function(){
    		$('#claimer').blur(function(){
	    		var claimer = $('#claimer').val().trim();
    			if (claimer == 0 || claimer == null) {
    				$('#showMsg5').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>项目申请人不能为空！").css('color','red');
    				claimerFlag = false;
    			} else {
    				$('#showMsg5').html("");
    				claimerFlag = true;
    			}
    		});
    	});
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对项目编号、项目名称、项目申请人做更改也没有使这三个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		var projNo = $('#projectNo').val().trim();
    		var name = $('#name').val().trim();
    		var claimer = $('#claimer').val().trim();
    		if (projNo != "" && projNo != null) {
    			projNoFlag = true;
    		}
    		if (name != "" && name != null) {
    			nameFlag = true;
    		}
    		if (claimer != "" && claimer != null && claimer != 0) {
    			claimerFlag = true;
    		}
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//项目启动日期非空验证
    			var startDateFlag = false;
    			var startDate = $('#startDate').val().trim();
    			if (startDate == "" || startDate == null) {
    				startDateFlag = false;
    				alert('项目启动日期不能为空！');
    				return false;
    			} else {
    				startDateFlag = true;
    			}
    			
    			//项目结束日期不能早于启动日期
    			var dateFlag = false;
	    		var endDate = $('#endDate').val().trim();
    			if (startDate > endDate && (endDate != null && endDate != "")) {
    				dateFlag = false;
    				alert("项目结束日期不能早于启动日期!");
    				return false;
    			} else {
    				dateFlag = true;
    			}
    			//最终的表单验证
    			var flag = projNoFlag && nameFlag && startDateFlag && dateFlag && claimerFlag;
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
            &emsp;&emsp;新建/修改项目信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" action="saveOrUpdateProj.action" method="post">
    	<input type="hidden" value="${project3.id}" name="project3.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
    	<input type="hidden" name="claimerId" value="${claimerId }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 项目信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>项目编号：</td>
                        <td><input id="projectNo" type="text" name="project3.projNo" class="InputStyle" value="${project3.projNo }"/> *</td>
                    	<td>项目名称：</td>
                        <td><input id="name" type="text" name="project3.name" class="InputStyle" value="${project3.name }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg"></div></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td>项目启动日期：</td>
                        <td><input id="startDate" type="text" class="laydate-icon" name="project3StartDate" class="InputStyle" value="${project3.startDate }" onclick="laydate(start1)"/> *</td>
                    	<td>项目结束日期：</td>
                        <td><input id="endDate" type="text" class="laydate-icon" name="project3EndDate" class="InputStyle" value="${project3.endDate }" onclick="laydate(end)"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr><td width="100">项目申请人：</td>
                        <td>
<%--                            这里不能自由选择，只有在登录用户是老师的情况下，会自动赋值为当前用户--%>
                        	<s:select id="claimer" theme="simple" cssClass="SelectStyle" name="claimerId" list="claimerList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择申请人" disabled="true">
                            </s:select> *
                            <div id="showMsg5"></div>
                        </td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td><br/></td></tr>
                	<tr><td width="13%">备注：</td>
                        <td><textarea name="project3.reverse" class="TextareaStyle">${project3.reverse }</textarea></td>
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
	1、项目编号要唯一，在填写时系统会自动检测是否可用。<br />
	2、项目编号、项目名称、项目启动日期、项目申请人不能为空。<br />
	3、项目启动日期不能晚于完成日期，填写后提交时系统会自动检测。<br />
</div>

</body>
</html>
