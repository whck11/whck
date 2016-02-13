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
		style="width: 180px;">
		<div id="aa" class="easyui-accordion"
			style="width: 170px; height: 420px;">
			<div title="用户管理" data-options="iconCls:'icon-man'"
				style="overflow: auto; padding: 10px;">
				<p>
					<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/admin/info.do">账户信息</a>
				</p>
				<p>
					<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/user/userList.do">用户列表</a>
				</p>
			</div>
			<div title="Title2"
				data-options="iconCls:'icon-reload',selected:true"
				style="padding: 10px;"></div>
		</div>
	</div>
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		<iframe id="adminFrm" style="border: 0px;" width="100%"
			height="500px"></iframe>
	</div>
	<script type="text/javascript">
		function setFrm(t) {
			var url = $(t).data('url');
			$('#adminFrm').attr('src', url);
		}
	</script>
</body>
</html>