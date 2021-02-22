<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="admin_menu.jsp"%>
<!--/sidebar-->
<div class="main-wrap">
	<div class="crumb-wrap">
		<div class="crumb-list">
			<i class="icon-font"></i><a href="index.html">首页</a><span
				class="crumb-step">&gt;</span><span class="crumb-name">产品管理</span>
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
					<a href="admin_toproductadd"><i class="icon-font"></i>新增产品</a>
				</div>
			</div>
			<div class="result-content">
				<table class="result-tab" width="60%">
					<tr>
						<th>ID</th>
						<th>商品名称</th>
						<th>操作</th>
					</tr>
					<!-- jstl标签 -->
					<c:forEach var="p" items="${plist}">
						<tr>
						<td>${p.PRODUCT_ID}</td>
						<td><img src="../images/product/${p.PRODUCT_FILENAME}" width="80" height="90">${p.PRODUCT_NAME}</td>
						<td><a href="">修改</a> <a href="">删除</a></td>
						</tr>
						<script>
							function catedel(id) {
								if (confirm("确定要删除这个分类吗？")) {
									location.href = "admin_docatedel?id=" + id;
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