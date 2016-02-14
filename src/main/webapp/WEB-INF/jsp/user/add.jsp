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
			<div class="col-md-12">
				<form id="ff" method="post">
					<div>
						<label for="username">登录名:</label> <input
							class="easyui-validatebox" data-options="required:true"
							type="text" name="username" />
					</div>
					<div>
						<label for="name">姓名:</label> <input class="easyui-validatebox"
							type="text" name="name" />
					</div>
					<div>
						<label for="phone">手机:</label> <input class="easyui-numberbox"
							type="text" name="phone" />
					</div>
					<div>
						<label for="address">地址:</label> <input class="easyui-validatebox"
							type="text" name="address" />
					</div>
					<div>
						<label for="cname">公司:</label> <input class="easyui-validatebox"
							type="text" name="cname" />
					</div>
					<div>
						<label for="remarks">备注:</label> <input class="easyui-validatebox"
							type="text" name="remarks" />
					</div>
					<div>
						<button class="btn btn-default" type="button" id="btnSave">保存</button>
						<button class="btn btn-default" type="button" id="btnBack">返回</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/user/add.do',
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
				window.location.href = contextPath + '/user/userList.do'
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>