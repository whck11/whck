<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
						<label for="zoneId">地区ID:</label> <input class="easyui-validatebox"
							type="text" name="zoneId" 
							value="${zone.zoneId }" readonly="readonly"/>
					</div>
					<div>
						<label for="zoneName">地区名:</label> <input class="easyui-validatebox"
							type="text" name="zoneName" 
							value="${zone.zoneName }" />
					</div>
					<div>
						<label for="area">面积:</label> <input class="easyui-numberbox"
							type="text" name="area" value="${zone.area }" />
					</div>
					<div>
						<label for="longitude">经度:</label> <input class="easyui-validatebox"
							type="text" name="longitude" value="${zone.longitude }" />
					</div>
					<div>
						<label for="latitude">纬度:</label> <input class="easyui-validatebox"
							type="text" name="latitude" value="${zone.latitude }" />
					</div>
					<div>
						<label for="remarks">备注:</label> <input class="easyui-validatebox"
							type="text" name="remarks" value="${zone.remarks }" />
					</div>
					<div>
						<label for="username">用户名:</label> <input class="easyui-validatebox"
							type="text" name="username" value="${zone.user.username}" />
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
		function bindBack(){
			$('#btnBack').click(function(){
				window.location=contextPath+'/zone/main.do'
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>