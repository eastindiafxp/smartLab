<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新建/修改阶段总结信息</title>
    <%@include file="/page/publicPage/head.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/laydate-v1.1/laydate/laydate.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    	//'#summaryDate'标签中的laydate()方法以start1为参数
    	var start1 = {
    		elem: '#summaryDate',
    		format: 'YYYY-MM-DD hh:mm:ss',
    		//min: laydate.now(), //设定最小日期为当前日期
    		//max: '2099-06-16 23:59:59', //最大日期
    		istime: true,
    		istoday: false,//此值必须为false此功能才能正常使用
		}
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            &emsp;&emsp;新建/修改阶段总结信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form id="form" name="summaryForm" action="saveOrUpdateSummary.action" method="post">
    	<input type="hidden" value="${summary.id}" name="summary.id" id="id"/>
    	<input type="hidden" name="pageNo" value="${pageNo }">
    	<input type="hidden" name="summaryList2" value="${summaryList1 }">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 阶段总结信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">标题：</td>
                        <td><input id="title" type="text"maxlength="30" name="summary.title" class="InputStyle" value="${summary.title }"/></td>
                    	<td width="100">总结日期：</td>
                        <td><input id="summaryDate" placeholder="请输入日期" type="text" class="laydate-icon" name="sumDate" class="InputStyle" value="${summary.summaryDate }" onclick="laydate(start1)"/> *</td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                </table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr><td width="100">备注：</td>
	                    <td><textarea name="summary.reverse" class="TextareaStyle">${summary.reverse }</textarea></td>
                	</tr>
                	<tr>
                    	<td><br/></td>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
				</table>
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<tr>
						<td colspan="4" style="text-align: center">总结1内容：</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<textarea name="summary.summary1" style="overflow-y:auto" rows="18" cols="100">${summary.summary1}</textarea>
						</td>
					</tr>
					<tr>
                    	<td><br/></td>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                	<tr>
						<td colspan="4" style="text-align: center">总结2内容：</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<textarea name="summary.summary2" style="overflow-y:auto" rows="18" cols="100">${summary.summary2}</textarea>
						</td>
					</tr>
					<tr>
                    	<td><br/></td>
                    	<td><br/></td>
                    </tr>
                    <tr>
                    	<td><br/></td>
                    </tr>
                	<tr>
						<td colspan="4" style="text-align: center">总结3内容：</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<textarea name="summary.summary3" style="overflow-y:auto" rows="18" cols="100">${summary.summary3}</textarea>
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
</body>
</html>
