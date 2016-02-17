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
			<form>
				<div class="col-md-5">
					<div class="form-group">
						<label for="zoneName">区域名称</label> <input type="text"
							class="form-control" id="zoneName" placeholder="区域名称">
					</div>
					<div class="form-group">
						<label for="area">面积</label> <input type="number"
							class="form-control" id="area" placeholder="面积">
					</div>
					<div class="form-group">
						<label for="longitude">经度</label> <input type="text"
							class="form-control" id="longitude" placeholder="经度">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="latitude">纬度</label> <input type="text"
							class="form-control" id="latitude" placeholder="纬度">
					</div>
					<div class="form-group">
						<label for="remarks">备注</label> <input type="text"
							class="form-control" id="remarks" placeholder="备注">
					</div>
					<div class="form-group">
						<label for="username">管理员ID</label> <input type="text"
							class="form-control" id="username" placeholder="管理员ID">
					</div>

				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-default" onclick="add()">确定</button>
					<button type="button" class="btn btn-default" onclick="back()">取消</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		function back() {
			window.location=contextPath+'/zone/main.do';
		}
		function add() {
			var params = {
				zoneName : $('#zoneName').val(),
				area : $('#area').val(),
				longitude : $('#longitude').val(),
				latitude : $('#latitude').val(),
				remarks : $('#remarks').val(),
				username : $('#username').val()
			}
			var url = contextPath + '/zone/add.do';
			$.post(url, params, function(data) {
				if (data.success) {
					alert('添加成功');
				} else {
					alert('添加失败');
				}
			}, 'json');
		}
	</script>
</body>
</html>