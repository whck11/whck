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
						<label for="deviceName">设备名</label> <input type="text"
							class="form-control" id="deviceName" placeholder="设备名">
					</div>
					<div class="form-group">
						<label for="description">描述</label> <input class="form-control"
							id="description" placeholder="描述">
					</div>
					<div class="form-group">
						<label for="ip">IP</label> <input type="text"
							class="form-control" id="ip" placeholder="IP">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="port">端口号</label> <input type="text"
							class="form-control" id="port" placeholder="端口号">
					</div>
					<div class="form-group">
						<label for="ctrlWay">控制方式</label> <input type="number"
							class="form-control" id="ctrlWay" placeholder="控制方式">
					</div>
					<div class="form-group">
						<label for="ctrlMode">控制模式</label> <input type="number"
							class="form-control" id="ctrlMode" placeholder="控制模式">
					</div>
				</div>
				<div class="col-md-5">
					<div class="form-group">
						<label for="dc">数据中心ID</label> <input type="text"
							class="form-control" id="dc" placeholder="数据中心ID">
					</div>
					<div class="form-group">
						<label>状态<input type="radio" name="state" id="state1">运行<input type="radio" name="state">停止</label>
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
			window.location = contextPath + '/device/main.do';
		}
		function add() {
			var state=$('#state1').is(':checked')?1:0;
			var params = {
					deviceName : $('#deviceName').val(),
					description : $('#description').val(),
					ip : $('#ip').val(),
					port : $('#port').val(),
					ctrlWay : $('#ctrlWay').val(),
					ctrlMode : $('#ctrlMode').val(),
					dc:$('#dc').val(),
					state:state
					
			}
			var url = contextPath + '/device/add.do';
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