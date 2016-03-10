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
						<label for="deviceName">设备名</label> <input type="text" value="${device.deviceName }"
							class="form-control" id="deviceName" placeholder="设备名">
					</div>
					<div class="form-group">
						<label for="description">描述</label> <input class="form-control"
							id="description" placeholder="描述" value="${device.description }">
					</div>
					<div class="form-group">
						<label for="ip">IP</label> <input type="text"
							class="form-control" id="ip" placeholder="IP" value="${device.ip }">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="port">端口号</label> <input type="text"
							class="form-control" id="port" placeholder="端口号" value="${device.port }">
					</div>
					<div class="form-group">
						<label for="ctrlWay">控制方式</label> <input type="number"
							class="form-control" id="ctrlWay" placeholder="控制方式" value="${device.ctrlWay }">
					</div>
					<div class="form-group">
						<label for="ctrlMode">控制模式</label> <input type="number"
							class="form-control" id="ctrlMode" placeholder="控制模式" value="${device.ctrlMode }">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="dc">数据中心ID</label> <input type="text"
							class="form-control" id="dc" placeholder="数据中心ID" value="${device.dc.id }">
					</div>
					<div class="form-group">
						<label>状态<input type="radio" name="state" id="state1">运行<input type="radio" name="state">停止</label>
					</div>
				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-default" id="btnSave">确定</button>
					<button type="button" class="btn btn-default" id="btnBack">取消</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/device/update.do',
					onSubmit : function() {
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
				window.location = contextPath + '/device/main.do';
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>