<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="index.html">首页</a><span
				class="crumb-step">&gt;</span><span class="crumb-name">用户管理</span>
		</div>
	</div>
	<div class="search-wrap">
		<div class="search-content">
			<form action="/Shop/manage/admin_douserselect" method="get">
				<table class="search-tab">
					<tr>
						<th width="70">姓名:</th>
						<td><input class="common-text" placeholder="关键字"
							name="keywords" value="${param.keywords}" id="" type="text"></td>
						<td><input class="btn btn-primary btn2" name="sub" value="查询"
							type="submit"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="result-wrap">
		<!-- 相对路径admin_douserdel 绝对路径/Shop/manage/admin_douserdel-->
		<form action="admin_douserdel" id="myform" name="myform" method="post">
			<div class="result-title">
				<div class="result-list">
					<a href="admin_useradd.jsp"><i class="icon-font"></i>新增用户</a> 
					<a id="batchDel" href="javascript:delmore('你确定删除这些用户吗？','myform')"><i class="icon-font"></i>批量删除</a>
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th class="tc" width="5%"><input class="allChoose" name="" onclick="selall(this)" type="checkbox"></th>
						<th>ID</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>手机</th>
						<th>操作</th>
					</tr>
					
					<!-- jstl标签 -->
					<c:forEach var="u" items="${userlist}">
						<tr>
							<td class="tc"><input name="id[]" value="${u.USER_ID}" type="checkbox"></td>
							<td>${u.USER_ID}</td>
							<td>${u.USER_NAME}</td>
							<td>${u.USER_SEX=='T'?'男':'女'}</td>
							<td>${u.USER_EMAIL}</td>
							<td>${u.USER_MOBILE}</td>
							<td><a class="link-update" href="admin_touserupdate?id=${u.USER_ID}&cpage=${cpage}">修改</a> 
							
							<!-- 普通用户1 管理员2 -->
							<c:if test="${u.USER_STATUS==1}">
							<a class="link-del" href="javascript:Delete('你确定要删除用户【${u.USER_NAME}】吗？', 'admin_douserdel?id=${u.USER_ID}&cpage=${cpage}')">删除</a>
							</c:if>
							<script>
								function Delete(mess, url) {
									if (confirm(mess)) {
										location.href=url;
									}
								}
								
								function selall(o) {
									var a = document.getElementsByName('id[]');
									for (var i = 0; i < a.length; i++) {
										a[i].checked = o.checked;
									}
								}
								
								function delmore(mess, formname) {
									if (confirm(mess)) {
										var form = document.getElementById(formname);
										form.submit();
									}
								}
							</script> 
							</td>
							
						</tr>
					</c:forEach>
				</table>
				<div class="list-page">
					共${tsum}条记录，当前${cpage}/${tpage}页 
					<a href="admin_douserselect?cp=1${searchParams}">首页</a> 
					<a href="admin_douserselect?cp=${cpage-1<1?1:cpage-1}${searchParams}">上一页</a>
					<!-- 相对路径 -->
					<a href="admin_douserselect?cp=${cpage+1>tpage?tpage:cpage+1}${searchParams}">下一页</a>
					<!-- 绝对路径 -->
					<a href="/Shop/manage/admin_douserselect?cp=${tpage}${searchParams}">尾页</a>
				</div>
			</div>
		</form>
	</div>
</div>
<!--/main-->
</div>
</body>
</html>