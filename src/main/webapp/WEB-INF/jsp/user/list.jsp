<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<button class="btn btn-default">添加</button>
			<button class="btn btn-default">修改</button>
			<button class="btn btn-default">删除</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th data-options="field:'username'">登录名</th>
							<th data-options="field:'name'">姓名</th>
							<th data-options="field:'phone'">手机</th>
							<th data-options="field:'cname'">公司名</th>
							<th data-options="field:'address'">地址</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${content }" var="user">
							<tr>
								<td>${user.username }</td>
								<td>${user.name }</td>
								<td>${user.phone }</td>
								<td>${user.cname }</td>
								<td>${user.address }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="pp"
					style="background: #efefef; border: 1px solid #ccc; margin: 0px;"></div>
			</div>
		</div>
	</div>
	<script>
		function loadTable() {
			$('#pp')
					.pagination(
							{
								total : "${totalElements}",
								pageSize : "${pageSize}",
								pageNumber : "${pageNumber}" + 1,
								onSelectPage : function(pageNumber, pageSize) {
									pageNumber = pageNumber - 1;
									window.location = "${contextPath}/user/userList.do?pageNumber="
											+ pageNumber
											+ "&pageSize="
											+ pageSize;
								},
								displayMsg : "显示 {from} 到 {to} 在 {total} 之间"
							});
		}
		$(function() {
			loadTable();
		})
	</script>
</body>
</html>