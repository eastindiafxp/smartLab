<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新建/修改样品信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//样品标签非空性与唯一性验证
    	var labelFlag = false;
    	$(function(){
	    	var label1 = $('#label').val().trim();
    		$('#label').blur(function(){
	    		var label2 = $('#label').val().trim();
	    		if (label1 == label2 && label1 !="") {
	    			$('#showMsg').html("");
	    			labelFlag = true;
	    		} else {
	    			if (label2 == "") {
	    				$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>样品标签不能为空！").css('color','red');
	    				labelFlag = false;
	    				return;
	    			}
	    			$("#showMsg").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
	    			$.post('validLabel.action',{'label2':$(this).val()},function(data){
                        var dataJSON = JSON.parse(data);
                        if (dataJSON.reverse == "true") {
	    				// if (data == "true") {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>该标签可以使用！").css('color','green');
	    					labelFlag = true;
	    				} else {
	    					$('#showMsg').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>该样品标签已被使用！").css('color','red');
	    					labelFlag = false;
	    				}
	    			});
	    		}
    		});
    	});
    	
//     	//'#submitDate'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
//     	var start1 = {
//     		elem: '#submitDate',
//     		format: 'YYYY-MM-DD',
//     		//min: laydate.now(), //设定最小日期为当前日期
//     		//max: '2099-06-16 23:59:59', //最大日期
//     		istime: true,
//     		istoday: false,//此值必须为false此功能才能正常使用
//     		choose: function(datas){
//     			end.min = datas; //开始日选好后，重置结束日的最小日期
//     			end.start1 = datas //将结束日的初始值设定为开始日
//     		}
// 		};
//     	//'#detectDate'标签中的laydate()方法以的end为参数时，使生产日期无法选择比失效日期更晚的日期
//     	var end = {
//     		elem: '#detectDate',
//     		format: 'YYYY-MM-DD',
//     		istime: true,
//     		istoday: false,//此值必须为false此功能才能正常使用
//     		choose: function(datas){
//     			start1.max = datas; //结束日选好后，重置开始日的最大日期
//     		}
//     	};
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对样品标签做更改也没有使这个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		var label = $('#label').val().trim();
    		if (label != "" && label != null) {labelFlag = true;}
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			
//     			//检测日期不能早于送检日期
//     			var dateFlag = false;
// 	    		var submitDate = $('#submitDate').val();
// 	    		var detectDate = $('#detectDate').val();
// 	    			if (submitDate > detectDate && (detectDate != null && detectDate != "")) {
// 	    				dateFlag = false;
// 	    				alert("检测日期不能早于送检日期!");
// 	    				return false;
// 	    			} else {
// 	    				dateFlag = true;
// 	    			}
    			
    			//最终的表单验证
    			if (!labelFlag) {alert("样品标签未通过校验！");return false;}
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
            &emsp;&emsp;新建/修改样品信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="specimenForm" action="saveOrUpdateSpecimen.action" method="post">
    	<input type="hidden" name="specimen.id" value="${specimen.id}"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 样品信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">样品标签：</td>
                        <td><input id="label" type="text" name="specimen.label" class="InputStyle" value="${specimen.label }"/> *</td>
<!--                     	<td width="100">送检标签：</td> -->
<%--                         <td><input id="detectLabel" type="text" name="specimen.detectLabel" class="InputStyle" value="${specimen.detectLabel }"/></td> --%>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
<!--                     <tr> -->
<!--                     	<td width="100">送检日期：</td> -->
<%--                         <td><input id="submitDate" placeholder="请输入送检日期" type="text" class="laydate-icon" name="specSubmitDate" class="InputStyle" value="${specimen.submitDate }" onclick="laydate(start1)"/></td> --%>
<!--                     	<td width="100">检测日期：</td> -->
<%--                         <td><input id="detectDate" placeholder="请输入检测日期" type="text" class="laydate-icon" name="specDetectDate" class="InputStyle" value="${specimen.detectDate }" onclick="laydate(end)"/></td> --%>
<!--                     </tr> -->
<!--                     <tr> -->
<!--                     	<td><br/></td><td><div id="showMsg1"></div></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                     	<td><br/></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                     	<td width="100">检测手段：</td> -->
<%--                         <td><input id="detectMean" type="text" name="specimen.detectMean" class="InputStyle" value="${specimen.detectMean }"/></td> --%>
<!--                     </tr> -->
<!--                     <tr> -->
<!--                     	<td><br/></td><td><div id="showMsg1"></div></td> -->
<!--                     </tr> -->
<!--                     <tr> -->
<!--                     	<td><br/></td> -->
<!--                     </tr> -->
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td width="13%">备注：</td>
	                    <td><textarea name="specimen.reverse" class="TextareaStyle">${specimen.reverse }</textarea></td>
                	</tr>
                	 <tr>
                    	<td><br/></td>
                    </tr>
                     <tr>
                    	<td><br/></td>
                    </tr>
<!--                 	<tr> -->
<!-- 						<td colspan="4" style="text-align: center">检测结果：</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td colspan="4" align="center"> -->
<%-- 							<textarea name="specimen.detectResult" style="overflow-y:auto" rows="10" cols="100">${specimen.detectResult}</textarea> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
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
	1、样品标签不能与已有的重复。<br/>
</div>
</body>
</html>
