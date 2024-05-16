<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>修改药品信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//溶液名称非空验证
    	var nameFlag = false;
    	$(function(){
    		$('#name').blur(function(){
	    		var name = $('#name').val().trim();
    			if (name == "" || name == null) {
    				$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>溶液名称不能为空！").css('color','red');
    				nameFlag = false;
    			} else {
    				$('#showMsg1').html("");
    				nameFlag = true;
    			}
    		});
    	});
    	
    	//溶剂非空验证
    	var solventFlag = false;
    	$(function(){
    		$('#solvent').blur(function(){
	    		var solvent = $('#solvent').val().trim();
    			if (solvent == "" || solvent == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>溶剂不能为空！").css('color','red');
    				solventFlag = false;
    			} else {
    				$('#showMsg2').html("");
    				solventFlag = true;
    			}
    		});
    	});
    	
    	//单次用量非空验证
    	var dosageFlag = false;
    	$(function(){
    		$('#dosage').blur(function(){
	    		var dosage = $('#dosage').val().trim();
    			if (dosage == "" || dosage == null) {
    				$('#showMsg3').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>单次用量不能为空！").css('color','red');
    				dosageFlag = false;
    			} else {
    				$('#showMsg3').html("");
    				dosageFlag = true;
    			}
    		});
    	});
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对溶液名称、溶剂、单次用量做更改也没有使这三个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		
    		var name = $('#name').val().trim();
    		var solvent = $('#solvent').val().trim();
    		var dosage = $('#dosage').val().trim();
    		
    		if (name != "" && name != null) {nameFlag = true;}
    		if (solvent != "" && solvent != null) {solventFlag = true;}
    		if (dosage != "" && dosage != null) {dosageFlag = true;}
    		
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//最终的表单验证
    			var flag = nameFlag && solventFlag && dosageFlag;
    			if (flag) {
    				return true;
    			} else {
    				if (nameFlag == false) {alert("溶液名称未通过校验！");}
    				if (solventFlag == false) {alert("溶剂未通过校验！");}
    				if (dosageFlag == false) {alert("单次用量未通过校验！");}
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
            &emsp;&emsp;新建/修改溶液信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="solutionForm" action="saveOrUpdateSolution.action" method="post">
    	<input type="hidden" value="${solution.id}" name="solution.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 溶液信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">溶液名称：</td>
                        <td><input id="name" type="text" name="solution.name" class="InputStyle" value="${solution.name }"/> *</td>
                    	<td width="100">溶剂：</td>
                        <td><input id="solvent" type="text" name="solution.solvent" class="InputStyle" value="${solution.solvent }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg1"></div></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">单次用量：</td>
                        <td><input id="dosage" type="text" name="solution.dosage" class="InputStyle" value="${solution.dosage }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg3"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="13%">溶质：</td>
                        <td>
                        	<s:select id="medicineIdList" name="medicineIdList" multiple="true" size="10" theme="simple" cssClass="SelectStyle" list="medicineList" listKey="id" listValue="name">
                            </s:select>
                        </td>
                        <td width="13%">按住Ctrl键可以多选或取消选择</td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td><br/></td></tr>
                	<tr><td><br/></td></tr>
                	<tr><td width="13%">备注：</td>
	                    <td><textarea name="solution.reverse" class="TextareaStyle">${solution.reverse }</textarea></td>
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
	1、溶液名称可以重复。<br/>
	2、同一种名称的溶液溶剂可以不同，单次用量也可以不同。<br/>
	3、溶质可以为空，当溶质为空时，默认是某一种液体试剂。<br/>
</div>
</body>
</html>
