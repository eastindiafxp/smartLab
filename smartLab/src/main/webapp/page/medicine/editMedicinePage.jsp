<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新建/修改药品信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//药品名称非空验证
    	var nameFlag = false;
    	$(function(){
    		$('#name').blur(function(){
	    		var name = $('#name').val().trim();
    			if (name == "" || name == null) {
    				$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>药品名称不能为空！").css('color','red');
    				nameFlag = false;
    			} else {
    				$('#showMsg1').html("");
    				nameFlag = true;
    			}
    		});
    	});
    	
    	//药品存放位置非空验证
    	var positionFlag = false;
    	$(function(){
    		$('#position').blur(function(){
	    		var position = $('#position').val().trim();
    			if (position == "" || position == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>药品存放位置不能为空！").css('color','red');
    				positionFlag = false;
    			} else {
    				$('#showMsg2').html("");
    				positionFlag = true;
    			}
    		});
    	});
    	
    	//'#manuDate'标签中的laydate()方法以的start1为参数时，使失效日期无法选择比生产日期更早的日期
    	var start1 = {
    		elem: '#manuDate',
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
    	//'#expireDate'标签中的laydate()方法以的end为参数时，使生产日期无法选择比失效日期更晚的日期
    	var end = {
    		elem: '#expireDate',
    		format: 'YYYY-MM-DD',
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
    		choose: function(datas){
    			start1.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对药品名称、药品存放位置做更改也没有使这两个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		
    		var name = $('#name').val().trim();
    		var position = $('#position').val().trim();
    		
    		if (name != "" && name != null) {nameFlag = true;}
    		if (position != "" && position != null) {positionFlag = true;}
    			
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//生产日期非空验证
    			//var manuDateFlag = false;
    			var manuDate = $('#manuDate').val();
    			//if (manuDate == "" || manuDate == null) {
    				//manuDateFlag = false;
    				//alert('生产日期不能为空！');
    				//return false;
    			//} else {
    				//manuDateFlag = true;
    			//}
    			
    			//失效日期不能早于生产日期，且不能为空
    			//var expireDateFlag = false;
    			var dateFlag = false;
	    		//var expireDate = $('#expireDate').val();
	    		//if (expireDate == null || expireDate == "") {
	    			//expireDateFlag = false;
	    			//alert("药品失效日期必须填写！");
	    			//return false;
	    		//} else {
	    			expireDateFlag = true;
	    			if (manuDate > expireDate) {
	    				dateFlag = false;
	    				alert("药品失效日期不能早于生产日期!");
	    				return false;
	    			} else {
	    				dateFlag = true;
	    			}
	    		//}
    			//最终的表单验证
    			var flag = nameFlag && positionFlag && expireDateFlag && dateFlag;
    			if (flag) {
    				return true;
    			} else {
    				if (nameFlag == false) {alert("药品名称未通过校验！");return false;}
    				if (positionFlag == false) {alert("药品存储位置未通过校验！");return false;}
    				//if (manuDateFlag == false) {alert("生产日期未通过校验！");return false;}
    				if (expireDateFlag == false) {alert("药品失效日期未通过校验！");return false;}
    				if (dateFlag == false) {alert("药品失效日期不能早于生产日期！");return false;}
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
            &emsp;&emsp;新建/修改药品信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="medicineForm" action="saveOrUpdateMedicine.action" method="post">
    	<input type="hidden" value="${medicine.id}" name="medicine.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 药品信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">药品名称：</td>
                        <td><input id="name" type="text" name="medicine.name" class="InputStyle" value="${medicine.name }"/> *</td>
                    	<td width="100">化学式：</td>
                        <td><input id="chemFormula" type="text" name="medicine.chemFormula" class="InputStyle" value="${medicine.chemFormula }"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg1"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">生产商：</td>
                        <td><input id="manufacturer" type="text" name="medicine.manufacturer" class="InputStyle" value="${medicine.manufacturer }"/></td>
                        <td width="100">存放位置：</td>
                        <td><input id="position" type="text" name="medicine.position" class="InputStyle" value="${medicine.position }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">生产日期：</td>
                        <td><input id="manuDate" type="text" class="laydate-icon" name="medicineManuDate" class="InputStyle" value="${medicine.manuDate }" onclick="laydate(start1)"/></td>
                    	<td width="100">失效日期：</td>
                        <td><input id="expireDate" type="text" class="laydate-icon" name="medicineExpireDate" class="InputStyle" value="${medicine.expireDate }" onclick="laydate(end)"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td><td></td>
                    	<td><br/></td><td></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">纯度/浓度：</td>
		                <td><input id="concentration" type="text" name="medicine.concentration" class="InputStyle" value="${medicine.concentration }"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td><td></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td width="13%">备注：</td>
	                    <td><textarea name="medicine.reverse" class="TextareaStyle">${medicine.reverse }</textarea></td>
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
	1、药品名称可以重复。<br/>
	2、为管理和使用方便，药品存放位置必须填写。<br/>
</div>
</body>
</html>
