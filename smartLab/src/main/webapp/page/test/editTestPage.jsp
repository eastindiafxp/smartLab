<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新建/修改检测信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//送检标签非空性验证
    	var labelFlag = false;
    	$(function(){
    		$('#label').blur(function(){
	    		var label = $('#label').val().trim();
	    		if (label != "" && label != null) {
	    			$('#showMsg').html("");
	    			labelFlag = true;
	    		} else {
	    			$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>送检标签不能为空！").css('color','red');
	    			labelFlag = false;
	    			return;
	    		}
    		});
    	});
    	
    	//样品标签非空性
    	var specimenFlag = false;
    	$(function(){
    		$('#specimenId').blur(function(){
	    		var specimenId = $('#specimenId').val();
    			if (specimenId == 0 || specimenId == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>样品标签不能为空！").css('color','red');
    				specimenFlag = false;
    			} else {
    				$('#showMsg2').html("");
					specimenFlag = true;
    			}
    		});
    	});
    	
    	//检测手段非空性验证
    	var testMethodFlag = false;
    	$(function(){
    		$('#testMethod').blur(function(){
	    		var testMethod = $('#testMethod').val();
    			if (testMethod == 0 || testMethod == null) {
    				$('#showMsg4').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>检测手段不能为空！").css('color','red');
    				testMethodFlag = false;
    			} else {
    				$('#showMsg4').html("");
    				testMethodFlag = true;
    			}
    		});
    	});
    	
    	//'#submitDate'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
    	var start1 = {
    		elem: '#submitDate',
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
    	//'#detectDate'标签中的laydate()方法以的end为参数时，使生产日期无法选择比失效日期更晚的日期
    	var end = {
    		elem: '#testDate',
    		format: 'YYYY-MM-DD',
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
    		choose: function(datas){
    			start1.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对送检标签、样品标签、检测手段做更改也没有使这些标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		var label = $('#label').val().trim();
    		var specimenId = $('#specimenId').val();
    		var testMethod = $('#testMethod').val();
    		
    		if (label != "" && label != null) {labelFlag = true;}
    		if (specimenId != "" && specimenId != null && specimenId != 0) {specimenFlag = true;}
    		if (testMethod != "" && testMethod != null) {testMethodFlag = true;}
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//检测日期非空验证
    			var testDateFlag = false;
    			var testDate = $('#testDate').val().trim();
    			if (testDate == "" || testDate == null) {
    				testDateFlag = false;
    				alert('检测日期不能为空！');
    				return false;
    			} else {
    				testDateFlag = true;
    			}
    			//检测日期不能早于送检日期
    			var dateFlag = false;
	    		var submitDate = $('#submitDate').val();
	    		var testDate = $('#testDate').val();
	    			if (submitDate > testDate && (testDate != null && testDate != "")) {
	    				dateFlag = false;
	    				alert("检测日期不能早于送检日期!");
	    				return false;
	    			} else {
	    				dateFlag = true;
	    			}
    			
    			//最终的表单验证
    			if (!labelFlag) {alert("送检标签未通过校验！");return false;}
    			if (!specimenFlag) {alert("样品标签未通过校验！");return false;}
    			if (!testMethodFlag) {alert("检测手段未通过校验!");return false;}
    			if (!dateFlag) {alert("检测日期不能早于送检日期!");return false;}
    			return true;
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
            &emsp;&emsp;新建/修改检测信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="testForm" action="saveOrUpdateTest.action" method="post">
    	<input type="hidden" name="test.id" value="${test.id}"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 检测信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">送检标签：</td>
                        <td><input id="label" type="text" name="test.label" class="InputStyle" value="${test.label }"/> *</td>
                    	<td width="100">样品标签：</td>
                        <td>
                        	<s:select id="specimenId" theme="simple" cssClass="SelectStyle" name="specimenId" list="specimenList" listKey="id" listValue="label" headerKey="0" headerValue="请选择样品">
                            </s:select> *
                        </td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg"></div></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">送检日期：</td>
                        <td><input id="submitDate" placeholder="请输入送检日期" type="text" class="laydate-icon" name="testSubmitDate" class="InputStyle" value="${test.submitDate }" onclick="laydate(start1)"/></td>
                    	<td width="100">检测日期：</td>
                        <td><input id="testDate" placeholder="请输入检测日期" type="text" class="laydate-icon" name="testTestDate" class="InputStyle" value="${test.testDate }" onclick="laydate(end)"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg3"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">检测手段：</td>
                        <td><input id="testMethod" type="text" name="test.testMethod" class="InputStyle" value="${test.testMethod }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg4"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td width="13%">备注：</td>
	                    <td><textarea name="test.reverse" class="TextareaStyle">${test.reverse }</textarea></td>
                	</tr>
                	 <tr>
                    	<td><br/></td>
                    </tr>
                     <tr>
                    	<td><br/></td>
                    </tr>
                	<tr>
						<td colspan="4" style="text-align: center">检测结果：</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<textarea name="test.testResult" style="overflow-y:auto" rows="10" cols="100">${test.testResult}</textarea>
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
<!-- <div class="Description"> -->
<!-- 	说明：<br /> -->
<!-- 	1、样品标签不能与已有的重复。<br/> -->
<!-- </div> -->
</body>
</html>
