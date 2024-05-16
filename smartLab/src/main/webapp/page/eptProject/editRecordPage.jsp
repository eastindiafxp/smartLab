<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>记录信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//实验编号唯一性验证的公共方法
    	function testRecordNoUniq() {
    		$("#showMsg1").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
			$.post('validRecordNo.action',{'recordNo':$('#recordNo').val(), 'experimentId':$('#experimentId').val()},function(data){

                var dataJSON = JSON.parse(data);
                console.log("============================")
                console.log(dataJSON.reverse)
                console.log(dataJSON)
                if (dataJSON.reverse == "true") {
				// if (data == "true") {
					$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>该编号可以使用！").css('color','green');
					recordNoFlag = true;
				} else {
					$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>本实验中该编号已被使用！").css('color','red');
					recordNoFlag = false;
				}
			});
    	}
    	
    	//验证记录编号的非空性和唯一性
	    var recordNoFlag = false;
    	$(function(){
    		//以下两行定义的两个全局变量在本函数中不使用，而是要在下面的reTestEptNo()函数中使用，放在这里只是为他们找一个容身之处
    		recordNo11 = $('#recordNo').val().trim();//为避免冲突，起名recordNo11
    		experimentId1 = $('#experimentId').val().trim();
    		
    		var recordNo1 = $('#recordNo').val().trim();
    		$('#recordNo').blur(function(){
	    		var recordNo2 = $('#recordNo').val().trim();
	    		if (recordNo1 == recordNo2 && recordNo1 != "") {//获得焦点但并未改变值又失去焦点的情况
	    			$('#showMsg1').html("");
	    			recordNoFlag = true;
	    		} else {//获得焦点并且改变了值
	    			if (recordNo2 == "") {//非空验证
	    				$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>记录编号不能为空！").css('color','red');
	    				recordNoFlag = false;
	    				return;
	    			}
	    			testRecordNoUniq();//唯一性验证
	    		}
    		});
    	});
    	
    	//记录名称非空验证
    	var nameFlag = false;
    	$(function(){
    		$('#name').blur(function(){
	    		var name = $('#name').val().trim();
    			if (name == "" || name == null) {
    				$('#showMsg2').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>记录名称不能为空！").css('color','red');
    				nameFlag = false;
    			} else {
    				$('#showMsg2').html("");
    				nameFlag = true;
    			}
    		});
    	});
    	
    	//所属实验非空验证
    	var experimentFlag = false;
    	$(function(){
    		$('#experimentId').blur(function(){
	    		var experimentId = $('#experimentId').val().trim();
    			if (experimentId == 0 || experimentId == null) {
    				$('#showMsg3').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>所属实验不能为空！").css('color','red');
    				experimentFlag = false;
    			} else {
    				$('#showMsg3').html("");
    				experimentFlag = true;
    			}
    		});
    	});
    	
    	//当实验下拉列表框的值改变时，重新校验实验编号非空性以及是否可用
    	function reTestRecordNoUniq() {
	    	var recordNo2 = $('#recordNo').val().trim();
	    	var experimentId2 = $('#experimentId').val().trim();
    		if (experimentId1 == experimentId2 && recordNo11 == recordNo2) {
    			$('#showMsg1').html("");
    			recordNoFlag = true;
    		} else {
	    		if (recordNo2 == "" || recordNo2 == null) {
	    			$('#showMsg1').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>记录编号不能为空！").css('color','red');
	    			recordNoFlag = false;
	    		} else {
	    			testRecordNoUniq();
	    		}
    		}
    	}
    	
    	//被镀材料非空验证
    	var substrateFlag = false;
    	$(function(){
    		$('#substrateId').blur(function(){
	    		var substrateId = $('#substrateId').val().trim();
    			if (substrateId == 0 || substrateId == null) {
    				$('#showMsg4').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>被镀材料不能为空！").css('color','red');
    				substrateFlag = false;
    			} else {
    				$('#showMsg4').html("");
    				substrateFlag = true;
    			}
    		});
    	});
    	
    	//被镀材料用量非空验证
    	var substrateMassFlag = false;
    	$(function(){
    		$('#substrateMass').blur(function(){
	    		var substrateMass = $('#substrateMass').val().trim();
    			if (substrateMass == "" || substrateMass == null) {
    				$('#showMsg5').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>被镀材料用量不能为空！").css('color','red');
    				substrateMassFlag = false;
    			} else {
    				$('#showMsg5').html("");
    				substrateMassFlag = true;
    			}
    		});
    	});
    	
    	//镀层非空验证
    	var cladLayerFlag = false;
    	$(function(){
    		$('#cladLayer').blur(function(){
	    		var cladLayer = $('#cladLayer').val().trim();
    			if (cladLayer == "" || cladLayer == null) {
    				$('#showMsg6').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>镀层不能为空！").css('color','red');
    				cladLayerFlag = false;
    			} else {
    				$('#showMsg6').html("");
    				cladLayerFlag = true;
    			}
    		});
    	});
    	
    	//ph值非空验证
    	var phFlag = false;
    	$(function(){
    		$('#ph').blur(function(){
	    		var ph = $('#ph').val().trim();
    			if (ph == "" || ph == null) {
    				$('#showMsg7').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>ph值不能为空！").css('color','red');
    				phFlag = false;
    			} else {
    				$('#showMsg7').html("");
    				phFlag = true;
    			}
    		});
    	});
    	
    	//反应温度非空验证
    	var temperatureFlag = false;
    	$(function(){
    		$('#temperature').blur(function(){
	    		var temperature = $('#temperature').val().trim();
    			if (temperature == "" || temperature == null) {
    				$('#showMsg8').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>温度值不能为空！").css('color','red');
    				temperatureFlag = false;
    			} else {
    				$('#showMsg8').html("");
    				temperatureFlag = true;
    			}
    		});
    	});
    	
    	//反应时间非空验证
    	var reaTimeFlag = false;
    	$(function(){
    		$('#reaTime').blur(function(){
	    		var reaTime = $('#reaTime').val().trim();
    			if (reaTime == "" || reaTime == null) {
    				$('#showMsg9').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>反应时间不能为空！").css('color','red');
    				reaTimeFlag = false;
    			} else {
    				$('#showMsg9').html("");
    				reaTimeFlag = true;
    			}
    		});
    	});
    	
    	//样品标签非空性与可用性验证
    	var specimenFlag = false;
    	$(function(){
	    	var specimenId1 = $('#specimenId').val();
    		$('#specimenId').blur(function(){
	    		var specimenId2 = $('#specimenId').val();
    			if (specimenId2 == 0 || specimenId2 == null) {
    				$('#showMsg10').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>样品标签不能为空！").css('color','red');
    				specimenFlag = false;
    			} else if (specimenId1 == specimenId2 && specimenId2 != 0 && specimenId2 != null) {
    				$('#showMsg10').html("");
					specimenFlag = true;
    			} else {
    				$("#showMsg10").html("<img src='${pageContext.request.contextPath}/style/images/window_loading.gif'/>正在检测中...").css('color','red');
    				$.post('validSpecimenId.action',{'specimenId':$('#specimenId').val()},function(data){

                        var dataJSON = JSON.parse(data);
                        if (dataJSON.reverse == "true") {
    					// if (data == "true") {
    						$('#showMsg10').html("<img src='${pageContext.request.contextPath}/style/images/right.gif'/>该样品可以使用！").css('color','green');
    						specimenFlag = true;
    					} else {
    						$('#showMsg10').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>该样品已与其他记录做关联，请重新选择！").css('color','red');
    						specimenFlag = false;
    					}
    				});
    			}
    		});
    	});
    	
    	//'#startDate'标签中的laydate()方法以的start1为参数时，使结束日期无法选择比开始日期更早的日期
    	var start1 = {
    		elem: '#startDate',
    		format: 'YYYY-MM-DD hh:mm:ss',
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
    		format: 'YYYY-MM-DD hh:mm:ss',
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
    		choose: function(datas){
    			start1.max = datas; //结束日选好后，重置开始日的最大日期
    		}
    	};
    	
    	//所用溶液非空验证
    	var solutionFlag = false;
    	$(function(){
    		$('#solutionIdList').blur(function(){
	    		var solutionIdList = $('#solutionIdList').val();
    			if (solutionIdList == 0 || solutionIdList == null) {
    				$('#showMsg11').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>所用溶液不能为空！").css('color','red');
    				solutionFlag = false;
    			} else {
    				$('#showMsg11').html("");
    				solutionFlag = true;
    			}
    		});
    	});
    	
    	//具体操作者非空验证
    	var operaterFlag = false;
    	$(function(){
    		$('#operaterIdList').blur(function(){
	    		var operaterIdList = $('#operaterIdList').val();
    			if (operaterIdList == 0 || operaterIdList == null) {
    				$('#showMsg12').html("<img src='${pageContext.request.contextPath}/style/images/wrong.gif'/>具体操作者不能为空！").css('color','red');
    				operaterFlag = false;
    			} else {
    				$('#showMsg12').html("");
    				operaterFlag = true;
    			}
    		});
    	});
    	
    	/*
    	 *当点击修改按钮进入修改页面，但是并未对记录编号、记录名称、记录所属实验、被镀材料、被镀材料用量、
    	 *镀层、ph值、反应温度、反应时间、样品标签、所用溶液、具体操作者做更改也没有使这十二个标签获得过焦点时，
    	 *点击保存按钮，这种情况下应该允许数据存入数据库
    	 */
    	$(function(){
    		
    		var recordNo = $('#recordNo').val().trim();
    		var name = $('#name').val().trim();
    		var experimentId = $('#experimentId').val().trim();
    		var substrateId = $('#substrateId').val().trim();
    		var substrateMass = $('#substrateMass').val().trim();
    		var cladLayer = $('#cladLayer').val().trim();
    		var ph = $('#ph').val().trim();
    		var temperature = $('#temperature').val().trim();
    		var reaTime = $('#reaTime').val().trim();
    		var specimenId = $('#specimenId').val();
    		var solutionIdList = $('#solutionIdList').val();
    		var operaterIdList = $('#operaterIdList').val();
    		
    		if (recordNo != "" && recordNo != null) {recordNoFlag = true;}
    		if (name != "" && name != null) {nameFlag = true;}
    		if (experimentId != 0 && experimentId != null) {experimentFlag = true;}
    		if (substrateId != 0 && substrateId != null) {substrateFlag = true;}
    		if (substrateMass != "" && substrateMass != null) {substrateMassFlag = true;}
    		if (cladLayer != "" && cladLayer != null) {cladLayerFlag = true;}
    		if (ph != "" && ph != null ) {phFlag = true;}
    		if (temperature != "" && temperature != null) {temperatureFlag = true;}
    		if (reaTime != "" && reaTime != null) {reaTimeFlag = true;}
    		if (specimenId != 0 && specimenId != null) {specimenFlag = true;}
    		if (solutionIdList != 0 && solutionIdList != null) {solutionFlag = true;}
    		if (operaterIdList != 0 && operaterIdList != null) {operaterFlag = true;}
    			
    	});
    	
    	//表单验证
    	$(function(){
    		$('form').submit(function(){
    			//实验开始日期非空验证
    			var startDateFlag = false;
    			var startDate = $('#startDate').val().trim();
    			if (startDate == "" || startDate == null) {
    				startDateFlag = false;
    				alert('记录开始日期不能为空！');
    				return false;
    			} else {
    				startDateFlag = true;
    			}
    			
    			//实验结束日期不能早于开始日期
    			var dateFlag = false;
	    		var endDate = $('#endDate').val().trim();
    			if (startDate > endDate && (endDate != null && endDate != "")) {
    				dateFlag = false;
    				alert("记录结束日期不能早于开始日期!");
    				return false;
    			} else {
    				dateFlag = true;
    			}
    			//最终的表单验证
    			var flag = recordNoFlag && nameFlag && experimentFlag && substrateFlag && substrateMassFlag &&
    			cladLayerFlag && phFlag && temperatureFlag && reaTimeFlag && specimenFlag && solutionFlag &&
    			operaterFlag && startDateFlag && dateFlag;
    			if (flag) {
    				return true;
    			} else {
    				if (recordNoFlag == false) {alert("记录编号未通过校验！");}
    				if (nameFlag == false) {alert("记录名称未通过校验！");}
    				if (experimentFlag == false) {alert("记录所属实验未通过校验！");}
    				if (substrateFlag == false) {alert("被镀材料未通过校验！");}
    				if (substrateMassFlag == false) {alert("被镀材料用量未通过校验！");}
    				if (cladLayerFlag == false) {alert("镀层未通过校验！");}
    				if (phFlag == false) {alert("pH值未通过校验！");}
    				if (temperatureFlag == false) {alert("温度值未通过校验！");}
    				if (reaTimeFlag == false) {alert("反应时间未通过校验！");}
    				if (specimenFlag == false) {alert("样品未通过校验！");}
    				if (solutionFlag == false) {alert("所用溶液未通过校验！");}
    				if (operaterFlag == false) {alert("操作者未通过校验！");}
    				if (startDateFlag == false) {alert("开始日期未通过校验！");}
    				if (dateFlag == false) {alert("记录结束日期不能早于开始日期！");}
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
            &emsp;&emsp;新建/修改记录信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="recordForm" action="saveOrUpdateRecord.action" method="post">
    	<input type="hidden" value="${record.id}" name="record.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 记录信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td>记录编号：</td>
                        <td><input id="recordNo" type="text" name="record.recordNo" class="InputStyle" value="${record.recordNo }"/> *</td>
                    	<td>记录名称：</td>
                        <td><input id="name" type="text" name="record.name" class="InputStyle" value="${record.name }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg1"></div></td>
                    	<td><br/></td><td><div id="showMsg2"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">所属实验：</td>
                        <td>
                        	<s:select id="experimentId" theme="simple" cssClass="SelectStyle" name="experimentId" list="experimentList" listKey="id" listValue="eptNo" headerKey="0" headerValue="请选择实验" onchange="reTestRecordNoUniq()">
                            </s:select> *
                        </td>
                    	<td width="100">被镀材料：</td>
                        <td>
                        	<s:select id="substrateId" theme="simple" cssClass="SelectStyle" name="substrateId" list="substrateList" listKey="id" listValue="name" headerKey="0" headerValue="请选择被镀材料">
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
                    	<td width="100">被镀材料用量：</td>
                        <td><input id="substrateMass" type="text" name="record.substrateMass" class="InputStyle" value="${record.substrateMass }"/> *</td>
                    	<td width="100">镀层：</td>
                        <td><input id="cladLayer" type="text" name="record.cladLayer" class="InputStyle" value="${record.cladLayer }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg5"></div></td>
                    	<td><br/></td><td><div id="showMsg6"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">ph值：</td>
		                <td><input id="ph" type="text" name="record.ph" class="InputStyle" value="${record.ph }"/> *</td>
                    	<td width="100">反应温度：</td>
                        <td><input id="temperature" type="text" name="record.temperature" class="InputStyle" value="${record.temperature }"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg7"></div></td>
                    	<td><br/></td><td><div id="showMsg8"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td width="100">反应时间：</td>
                        <td><input id="reaTime" type="text" name="record.reaTime" class="InputStyle" value="${record.reaTime }"/> *</td>
                    	<td width="100">样品标签：</td>
                         <td>
                        	<s:select id="specimenId" theme="simple" cssClass="SelectStyle" name="specimenId" list="specimenList" listKey="id" listValue="label" headerKey="0" headerValue="请选择样品">
                            </s:select> *
                        </td>
                    </tr>
                    <tr>
                    	<td><br/></td><td><div id="showMsg9"></div></td>
                    	<td><br/></td><td><div id="showMsg10"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td>开始日期：</td>
                        <td><input id="startDate" type="text" class="laydate-icon" name="recordStartDate" class="InputStyle" value="${record.startDate }" onclick="laydate(start1)"/> *</td>
                    	<td>结束日期：</td>
                        <td><input id="endDate" type="text" class="laydate-icon" name="recordEndDate" class="InputStyle" value="${record.endDate }" onclick="laydate(end)"/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td><br/></td></tr>
                    <tr>
						<td width="13%">所用溶液：</td>
                        <td>
                        	<s:select id="solutionIdList" name="solutionIdList" multiple="true" size="10" theme="simple" cssClass="SelectStyle" list="solutionList" listKey="id" listValue="name">
                            </s:select> *
                        </td>
                        
						<td width="13%">具体操作者：</td>
                        <td>
                        	<s:select id="operaterIdList" name="operaterIdList" multiple="true" size="10" theme="simple" cssClass="SelectStyle" list="operaterList" listKey="id" listValue="loginName">
                            </s:select> *
                        </td>
                    </tr>
                    <tr>
                    	<td></td>
                    	<td>
                    		按住Ctrl键可以多选或取消选择
                    	</td>
                    	<td></td>
                    	<td>
                    		按住Ctrl键可以多选或取消选择
                    	</td>
                    </tr>
                     <tr>
                    	<td><br/></td><td><div id="showMsg11"></div></td>
                    	<td><br/></td><td><div id="showMsg12"></div></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td><br/></td></tr>
                	<tr><td width="13%">操作过程：</td>
                    	<td><textarea name="record.process" class="TextareaStyle">${record.process }</textarea></td>
                	</tr>
                	<tr><td><br/></td></tr>
                	<tr><td width="13%">备注：</td>
	                    <td><textarea name="record.reverse" class="TextareaStyle">${record.reverse }</textarea></td>
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
