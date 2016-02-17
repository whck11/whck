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
		<form id="ff">
			<div class="row">
				<div class=col-md-5>
					<div class="form-group">
						<label for="zoneId">ID</label> <input name="zoneId"
							class="form-control" value="${zone.zoneId }" readonly="readonly">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label for="zoneName">区域名称</label> <input type="text"
							class="form-control" name="zoneName" placeholder="区域名称"
							value="${zone.zoneName }">
					</div>
					<div class="form-group">
						<label for="area">面积</label> <input type="number"
							class="form-control" name="area" placeholder="面积"
							value="${zone.area }">
					</div>
					<div class="form-group">
						<label for="longitude">经度</label> <input type="text"
							class="form-control" name="longitude" placeholder="经度"
							value="${zone.longitude }">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="latitude">纬度</label> <input type="text"
							class="form-control" name="latitude" placeholder="纬度"
							value="${zone.latitude }">
					</div>
					<div class="form-group">
						<label for="remarks">备注</label> <input type="text"
							class="form-control" name="remarks" placeholder="备注"
							value="${zone.remarks }">
					</div>
					<div class="form-group">
						<label for="username">管理员ID</label> <input type="text"
							class="form-control" name="username" placeholder="管理员ID"
							value="${zone.user.username }">
					</div>

				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-default" id="btnSave">确定</button>
					<button type="button" class="btn btn-default" id="btnBack">取消</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/zone/update.do',
					onSubmit : function() {
						if ($('input[name="zoneName"]').val().trim() == "") {
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
		function bindBack() {
			$('#btnBack').click(function() {
				window.location = contextPath + '/zone/main.do';
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>