<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="index.html">首页</a><span
				class="crumb-step">&gt;</span><span class="crumb-name">分类管理</span>
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
		<form action="admin_douserdel" id="myform" name="myform" method="post">
			<div class="result-title">
				<div class="result-list">
					<a href="#"><i class="icon-font"></i>新增分类</a>
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th>ID</th>
						<th>分类</th>
						<th>操作</th>
					</tr>
					<!-- jstl标签 -->
					<c:forEach var="cate" items="${catelist}">
						<c:if test="${cate.CATE_PARENT_ID == 0}">
							<tr>
								<td>${cate.CATE_ID}</td>
								<td>|-${cate.CATE_NAME}</td>
								<td><a href="">修改</a> <a href="">删除</a></td>
							</tr>
							<c:forEach var="zcate" items="${catelist}">
								<c:if test="${zcate.CATE_PARENT_ID == cate.CATE_ID}">
									<tr>
										<td>${zcate.CATE_ID}</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-${zcate.CATE_NAME}</td>
										<td><a href="">修改</a> <a href="">删除</a></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
						<script>
							function Delete(mess, url) {
								if (confirm(mess)) {
									location.href = url;
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
									var form = document
											.getElementById(formname);
									form.submit();
								}
							}
						</script>

						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
</div>
<!--/main-->
</div>
</body>
</html>