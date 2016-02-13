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
		<div>
			<a href="javascript:void(0)" id="updatePassword">修改密码</a>
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
		<iframe id="adminFrm" style="border: 0px;" width="100%" height="450px"></iframe>
	</div>
	<div id="win">
		<form id="ff" method="post">
			<div>
				<label for="oldPassword">原密码:</label> <input
					class="easyui-validatebox" type="password" name="oldPassword" />
			</div>
			<div>
				<label for="newPassword">新密码:</label> <input
					class="easyui-validatebox" type="password" name="newPassword" />
			</div>
			<div>
				<label for="newPassword2">确认密码:</label> <input
					class="easyui-validatebox" type="password" name="newPassword2" />
			</div>
			<div>
				<input type="button" value="确认" onclick="updatePassword()">
				<input type="button" value="取消" onclick="cancel()">
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function updatePassword() {
			var oldPassword = $('input[name=oldPassword]').val().trim();
			if (!oldPassword) {
				alert('原密码不能为空');
				return false;
			}
			var newPassword = $('input[name=newPassword]').val().trim();
			if (!newPassword) {
				alert('新密码不能为空');
				return false;
			}
			var newPassword2 = $('input[name=newPassword2]').val().trim();
			if (!newPassword2) {
				alert('确认密码不能为空');
				return false;
			}
			if (newPassword !== newPassword2) {
				alert('两次密码输入不一致');
				return false;
			}
			var url = contextPath + "/admin/updatePassword.do";
			var params = {
				oldPassword : oldPassword,
				newPassword : newPassword,
				newPassword2 : newPassword2
			};
			$.post(url, params, function(data) {
				if (data.result) {
					alert("修改成功");
				} else {
					alert(data.msg);
				}
			}, 'json');
		}

		function cancel() {
			$('#win').window('close');
		}
		function setFrm(t) {
			var url = $(t).data('url');
			$('#adminFrm').attr('src', url);
		}
		function loadUpdatePasswordWin() {
			$('#win').window({
				width : 600,
				height : 400,
				modal : true,
				closed : true,
				collapsible : false,
				minimizable : false,
				maximizable : false,
				title : "修改密码"
			});
			$('#updatePassword').click(function() {
				$('#win').window('open');
			});
		}
		$(function() {
			loadUpdatePasswordWin();
		})
	</script>
</body>
</html>