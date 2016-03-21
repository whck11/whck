<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form id="ff" method="post">
				<div class="col-md-5">
					<div class="form-group">
						<label for="username">登录名:</label> <input
							class="easyui-validatebox form-control" type="text"
							name="username" />
					</div>
					<div class="form-group">
						<label for="name">姓名:</label> <input
							class="easyui-validatebox form-control" type="text" name="name"
							data-options="required:true" />
					</div>
					<div class="form-group">
						<label for="phone">手机:</label> <input class="form-control"
							type="number" name="phone" />
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="address">地址:</label> <input class="form-control"
							type="text" name="address" />
					</div>
					<div class="form-group">
						<label for="cname">公司:</label> <input class="form-control"
							type="text" name="cname" />
					</div>
					<div class="form-group">
						<label for="remarks">备注:</label> <input class="form-control"
							type="text" name="remarks" />
					</div>

				</div>
				<div class="col-md-5">
					<div>
						<label for="role"></label><select name="roleId" id="sRole">
							<option value=0>请选择权限</option>
							<option value="3">普通用户</option>
							<option value="2">管理员</option>
						</select>
					</div>
					<div class="col-md-12">
						<button class="btn btn-default" type="button" id="btnSave">保存</button>
						<button class="btn btn-default" type="button" id="btnBack">返回</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/admin/add.do',
					onSubmit : function() {
						if ($('input[name="username"]').val().trim() == "") {
							return false;
						}
						return true;
					},
					success : function(data) {
						var object = eval('(' + data + ')');
						if (object.success) {
							alert('添加成功，' + object.msg);
						} else {
							alert('添加失败，' + object.msg);
						}
					}
				});
				$('#ff').submit();
			});
		}
		function bindBack() {
			$('#btnBack').bind('click', function() {
				window.location.href = contextPath + '/admin/userList.do'
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>