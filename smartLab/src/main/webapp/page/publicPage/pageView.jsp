<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--分页信息-->
		<div id=PageSelectorBar>
			<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }">
			<input type="hidden" id="pageNo2" name="pageNo2" value="${page.pageNo }">
			<input type="hidden" id="pageNo3" name="pageNo3" value="100">
			<input type="hidden" id="pageCount" value="${page.pageCount }">
			<div id=PageSelectorMemo>
				每页显示：${page.pageSize}条 &nbsp;
				结果总数：${page.resultCount}条
			</div>
			<div id="PageSelectorSelectorArea">
				<input type="button" value="首页" style="width:40px;cursor: pointer;" onclick="gotoPageNum(-2)"/>
				<input type="button" value="上一页" style="width:60px;cursor: pointer;" onclick="gotoPageNum(${page.pageNo-1})"/>
				 &nbsp;第 ${page.pageNo }/${page.pageCount } 页&nbsp;
				<input type="button" value="下一页" style="width:60px;cursor: pointer;" onclick="gotoPageNum(${page.pageNo+1})"/>
				<input type="button" value="尾页" style="width:40px;cursor: pointer;" onclick="gotoPageNum(-1)"/>
				&nbsp;
				<input type="button" value="转到：" style="width:45px;cursor: pointer;" onclick="turnToPage()">
				<input type="text" id="turnToPage" value=${page.pageNo } style="width:30px;height:14px;text-align:center;"> 页
				<!-- 另一种分页显示策略-->
<%--				<a href="javascript:void(0)" title="首页" onclick="gotoPageNum(1)" style="cursor: hand;">--%>
<%--					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/>--%>
<%--				</a>--%>
<%--				<s:iterator begin="page.beginPageNo" end="page.endPageNo" var="s">--%>
<%--					<s:if test="page.pageNo == #s">--%>
<%--						<span class="PageSelectorNum PageSelectorSelected"><s:property value="#s"/></span>--%>
<%--					</s:if>--%>
<%--					<s:else>--%>
<%--						<span class="PageSelectorNum" style="cursor: pointer;" onClick="gotoPageNum(<s:property value="#s"/>);">--%>
<%--							<s:property value="#s"/>--%>
<%--						</span>--%>
<%--					</s:else>--%>
<%--				</s:iterator>--%>
<%--				<a href="javascript:void(0)" title="尾页" onclick="gotoPageNum(${page.pageCount})" style="cursor: hand;">--%>
<%--					<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/>--%>
<%--				</a>--%>
<%--				转到：--%>
<%--				<select id="pageNos" onchange="gotoPageNum(this.value)">--%>
<%--					<s:iterator begin="1" end="page.pageCount" var="s">--%>
<%--						<option value='<s:property value="#s"/>'>--%>
<%--							<s:property value="#s"/>--%>
<%--						</option>--%>
<%--					</s:iterator>--%>
<%--				</select>--%>

			</div>
		</div>
		<script type="text/javascript">
			//当前页码
			var thisPageNo = $("#pageNo").val();
			var pageCount = $("#pageCount").val();
			function gotoPageNum(pageNo) {
				if (pageNo == -2) {
					if (thisPageNo == 1) {
						alert("当前页已是首页！");
						return;
					}
					//动态添加一个隐藏输入框，传递页码信息
					$("#pageForm").append('<input type="hidden" name="pageNo" value="' + 1 +'">');
					$("#pageForm").submit();
				} else if (pageNo == -1) {
					if (thisPageNo == pageCount) {
						alert("当前页已是尾页！");
						return;
					}
					//动态添加一个隐藏输入框，传递页码信息
					$("#pageForm").append('<input type="hidden" name="pageNo" value="' + pageCount +'">');
					$("#pageForm").submit();
				} else {
					if (pageNo == 0) {
						alert("已到首页！");
						return;
					} else if (pageNo > pageCount) {
						alert("已到尾页！");
						return;
					}
					//动态添加一个隐藏输入框，传递页码信息
					$("#pageForm").append('<input type="hidden" name="pageNo" value="' + pageNo +'">');
					$("#pageForm").submit();
				}
				
			}
			
			function turnToPage() {
				var pageNum = $("#turnToPage").val().trim();
				var v = /^[1-9]\d*$/;//正整数的正则表达式
				
				if(pageNum == ""){
					alert('请填写要跳转的页数！');
					return;
				}

				//判断输入字符串格式，输入的字符串只能为正整数。
				if (!v.test(pageNum)){
					alert('跳转页数格式不正确！');
					return;
				}
				
				if(parseInt(pageNum, 10) > parseInt(pageCount, 10)){
					alert('跳转页数过大！');
					return;
				}

				if(parseInt(pageNum, 10) == parseInt(thisPageNo, 10)){
					alert('已是当前页面！');
					return;
				}
				//动态添加一个隐藏输入框，传递页码信息
				$("#pageForm").append('<input type="hidden" name="pageNo" value="' + pageNum +'">');
				$("#pageForm").submit();
			}
		
		</script>
		
		
		