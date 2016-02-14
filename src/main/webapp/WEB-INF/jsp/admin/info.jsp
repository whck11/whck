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
							class="easyui-validatebox" type="text" name="username"
							readonly="readonly" value="${LOGIN_SESSION_DATA.username }" />
					</div>
					<div>
						<label for="name">姓名:</label> <input class="easyui-validatebox"
							type="text" name="name" data-options="required:true"
							value="${LOGIN_SESSION_DATA.name }" />
					</div>
					<div>
						<label for="phone">手机:</label> <input class="easyui-numberbox"
							type="text" name="phone" value="${LOGIN_SESSION_DATA.phone }" />
					</div>
					<div>
						<label for="address">地址:</label> <input class="easyui-validatebox"
							type="text" name="address" value="${LOGIN_SESSION_DATA.address }" />
					</div>
					<div>
						<label for="cname">公司:</label> <input class="easyui-validatebox"
							type="text" name="cname" value="${LOGIN_SESSION_DATA.cname }" />
					</div>
					<div>
						<label for="remarks">备注:</label> <input class="easyui-validatebox"
							type="text" name="remarks" value="${LOGIN_SESSION_DATA.remarks }" />
					</div>
					<div>
						<button class="btn btn-default" type="button" id="btnSave">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/admin/saveInfo.do',
					onSubmit : function() {
						if ($('input[name="username"]').val().trim() == "") {
							return false;
						}
						if ($('input[name="name"]').val().trim() == "") {
							return false;
						}
						return true;
					},
					success : function(data) {
						var object = eval('(' + data + ')');
						if (object.result) {
							alert('保存成功');
						} else {
							alert('保存失败');
						}
					}
				});
				$('#ff').submit();

			});
		}
		$(function() {
			bindSave();
		})
	</script>
</body>
</html>