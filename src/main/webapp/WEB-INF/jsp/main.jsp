<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>
<title>万宏物联网服务后台管理系统</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" style="height: 100px;">
		<div>欢迎您：${LOGIN_SESSION_DATA.name}</div>
		<div>
			<a href="${contextPath }/login/logOut.do">注销</a>
		</div>
	</div>
	<div data-options="region:'south',split:true" style="height: 100px;"></div>
	<div data-options="region:'west',title:'后台管理',split:true"
		style="width: 180px;"></div>
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;"></div>
</body>
</html>