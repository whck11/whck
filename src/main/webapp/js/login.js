function bindLogin(){
	$('#btnLogin').click(function(){
		var username=$('#username').val();
		var password=$('#password').val();
		var params={"username":username,"password":password};
		function handler(data){
			if(data.success){
				var user=data.user;
				alert("欢迎您"+user.name);
			}else{
				alert(data.msg);
			}
		}
		$.post('/whck/user/login.do',params,handler,'json');
	})
}
$(function(){
	bindLogin();
})