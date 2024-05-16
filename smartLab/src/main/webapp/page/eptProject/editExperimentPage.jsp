<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>实验信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//实验编号唯一性验证的公共方法
    	function testEptNoUniq() {
    		$("#showMsg1").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
			$.post('validExperimentNo.action',{'experimentNo':$('#experimentNo').val(), 'projectId':$('#projectId').val()},function(data){

                var dataJSON = JSON.parse(data);
                if (dataJSON.reverse == "true") {
					$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>该编号可以使用！").css('color','green');
					experimentNoFlag = true;
				} else {
					$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>本项目中该编号已被使用！").css('color','red');
					experimentNoFlag = false;
				}
			});
    	}
    	
    	//验证实验编号的非空性和唯一性
	    var experimentNoFlag = false;
    	$(function(){
    		//以下两行定义的两个全局变量在本函数中不使用，而是要在下面的reTestEptNo()函数中使用，放在这里只是为他们找一个容身之处
    		eptNo1 = $('#experimentNo').val().trim();
    		projId1 = $('#projectId').val().trim();
    		
    		var experimentNo1 = $('#experimentNo').val().trim();
    		$('#experimentNo').blur(function(){
	    		var experimentNo2 = $('#experimentNo').val().trim();
	    		if (experimentNo1 == experimentNo2 && experimentNo1 != "") {//获得焦点但并未改变值又失去焦点的情况
	    			$('#showMsg1').html("");
	    			experimentNoFlag = true;
	    		} else {
	    			if (experimentNo2 == "") {
	    				$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>实验编号不能为空！").css('color','red');
	    				experimentNoFlag = false;
	    				return;
	    			}
	    			testEptNoUniq();
	    		}
    		});
    	});
    	
    	//实验名称非空验证
    	var nameFlag = false;
    	$(function(){
    		$('#name').blur(function(){
	    		var name = $('#name').val().trim();
    			if (name == "" || name == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>实验名称不能为空！").css('color','red');
    				nameFlag = false;
    			} else {
    				$('#showMsg2').html("");
    				nameFlag = true;
    			}
    		});
    	});
    	
    	//所属项目非空验证
    	var projectFlag = false;
    	$(function(){
    		$('#projectId').blur(function(){
	    		var projectId = $('#projectId').val().trim();
    			if (projectId == 0 || projectId == null) {
    				$('#showMsg3').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>所属项目不能为空！").css('color','red');
    				projectFlag = false;
    			} else {
    				$('#showMsg3').html("");
    				projectFlag = true;
    			}
    		});
    	});
    	
    	//当项目下拉列表框的值改变时，重新校验实验编号非空性以及是否可用
    	function reTestEptNo() {
	    	var projId2 = $('#projectId').val().trim();
	    	var eptNo2 = $('#experimentNo').val().trim();
    		if (projId1 == projId2 && eptNo1 == eptNo2) {
    			$('#showMsg1').html("");
    			experimentNoFlag = true;
    		} else {
	    		var eptNo = $('#experimentNo').val().trim();
	    		if (eptNo == "" || eptNo == null) {
	    			$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>实验编号不能为空！").css('color','red');
					experimentNoFlag = false;
	    		} else {
	    			testEptNoUniq();
	    		}
    		}
    	}
    	
    	//实验操作者非空验证
    	var operaterFlag = false;
    	$(function(){
    		$('#mainOperateId').blur(function(){
	    		var mainOperateId = $('#mainOperateId').val().trim();
    			if (mainOperateId == 0 || mainOperateId == null) {
    				$('#showMsg4').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>实验操作者不能为空！").css('color','red');
    				operaterFlag = false;
    			} else {
    				$('#showMsg4').html("");
    				operaterFlag = true;
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
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对实验编号、实验名称、实验所属项目、实验操作者做更改也没有使这四个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		
    		var experimentNo = $('#experimentNo').val().trim();
    		var name = $('#name').val().trim();
    		var project = $('#projectId').val().trim();
    		var mainOperate = $('#mainOperateId').val().trim();
    		
    		if (experimentNo != "" && experimentNo != null) {
    			experimentNoFlag = true;
    		}
    		if (name != "" && name != null) {
    			nameFlag = true;
    		}
    		if (project != "" && project != null && project != 0) {
    			projectFlag = true;
    		}
    		if (mainOperate != "" && mainOperate != null && mainOperate != 0) {
    			operaterFlag = true;
    		}
    		
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//实验开始日期非空验证
    			var startDateFlag = false;
    			var startDate = $('#startDate').val().trim();
    			if (startDate == "" || startDate == null) {
    				startDateFlag = false;
    				alert('实验开始日期不能为空！');
    				return false;
    			} else {
    				startDateFlag = true;
    			}
    			
    			//实验结束日期不能早于开始日期
    			var dateFlag = false;
	    		var endDate = $('#endDate').val().trim();
    			if (startDate > endDate && (endDate != null && endDate != "")) {
    				dateFlag = false;
    				alert("实验结束日期不能早于开始日期!");
    				return false;
    			} else {
    				dateFlag = true;
    			}
    			//最终的表单验证
    			var flag = experimentNoFlag && nameFlag && projectFlag && operaterFlag && startDateFlag && dateFlag;
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
            &emsp;&emsp;新建/修改实验信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="etpForm" action="saveOrUpdateEpt.action" method="post">
    	<input type="hidden" value="${experiment.id}" name="experiment.id" id="id"/>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 实验信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>实验编号：</td>
                        <td><input id="experimentNo" type="text" name="experiment.eptNo" class="InputStyle" value="${experiment.eptNo }"/> *</td>
                    	<td>实验名称：</td>
                        <td><input id="name" type="text" name="experiment.name" class="InputStyle" value="${experiment.name }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg1"></div></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">所属项目：</td>
                        <td>
                        	<s:select id="projectId" theme="simple" cssClass="SelectStyle" name="projectId" list="projectList" listKey="id" listValue="projNo" headerKey="0" headerValue="请选择项目" onchange="reTestEptNo()">
                            </s:select> *
                        </td>
                    	<td width="100">主要操作者：</td>
                        <td>
                        	<s:select id="mainOperateId" theme="simple" cssClass="SelectStyle" name="mainOperateId" list="operateList" listKey="id" listValue="loginName" headerKey="0" headerValue="请选择用户">
                            </s:select> *
                        </td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg3"></div></td>
                    	<td><br/></td><td><div id="showMsg4"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td>实验开始日期：</td>
                        <td><input id="startDate" type="text" class="laydate-icon" name="experimentStartDate" class="InputStyle" value="${experiment.startDate }" onclick="laydate(start1)"/> *</td>
                    	<td>实验结束日期：</td>
                        <td><input id="endDate" type="text" class="laydate-icon" name="experimentEndDate" class="InputStyle" value="${experiment.endDate }" onclick="laydate(end)"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td><br/></td></tr>
                	<tr><td width="13%">实验描述：</td>
                        <td><textarea name="experiment.description" class="TextareaStyle">${experiment.description }</textarea></td>
                    </tr>
                	<tr><td width="13%">备注：</td>
                        <td><textarea name="experiment.reverse" class="TextareaStyle">${experiment.reverse }</textarea></td>
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
	1、实验编号要唯一，在填写时系统会自动检测是否可用。<br/>
	2、实验编号、实验名称、实验操作者、实验所属项目、实验开始日期不能为空。<br/>
	3、“所属项目”更改后，系统会重新检测实验编号在更改后的项目中是否重复。<br/>
	4、实验开始日期不能晚于结束日期，填写后提交时系统会自动检测。<br/>
</div>

</body>
</html>
