function bindLogin() {
	$('#btnLogin').click(function() {
		var username = $('#username').val();
		var password = $('#password').val();
		var params = {
			username : username,
			password : password
		};
		function handler(data) {
			if (data.success) {
				var user = data.user;
				location.href="index.html";
				var remember = $('#remember').is(':checked');
				if (remember) {
					localStorage.setItem('remember', true);
					localStorage.setItem('username', username);
					localStorage.setItem('password', password);
				} else {
					localStorage.removeItem('remember', false);
					localStorage.removeItem('username');
					localStorage.removeItem('password');
				}
			} else {
				alert(data.msg);
			}
		}
		$.post('../user/login.do', params, handler, 'json');
	})
}
function loadLocalStorage() {
	if (localStorage.getItem('remember')) {
		$('#username').val(localStorage.getItem('username'));
		$('#password').val(localStorage.getItem('password'));
	}
}
function bindSendCode1() {
	$('#btn1').click(function() {
		var phone = $('#register_phone').val();
		var params = {
			"phone" : phone
		};
		function handler(data) {
			if (data.success) {
				window.code = data.code;
			} else {
				alert(data.msg);
			}
		}
		$.get('../user/sendCode.do', params, handler, 'json');
	});
}
function bindRegister() {
	$('#btnRegister').click(function() {
		if ($('#isConfirm:checked').val() !== '同意') {
			return;
		}
		var params = {
			username : $('#reg_username').val(),
			email : $('#reg_email').val(),
			password : $('#reg_password').val(),
			password2 : $('#reg_password2').val(),
			phone : $('#reg_phone').val(),
			code : $('#reg_code').val()
		};
		function handler(data) {
			if (data.success) {
				alert('注册成功');
			} else {
				alert(data.msg);
			}
		}
		$.post('../user/register.do', params, handler, 'json');
	});
}
function bindSendCode2() {
	$('#btn2').click(function() {
		var phone = $('#f_phone').val();
		var params = {
			"phone" : phone
		};
		function handler(data) {
			if (data.success) {
				window.code = data.code;
			} else {
				alert(data.msg);
			}
		}
		$.get('../user/sendCode.do', params, handler, 'json');
	});
}
function bindResetPassword() {
	$('#btnForget').click(function() {
		var params = {
			phone : $('#f_phone').val(),
			password : $('#f_new_password').val(),
			password2 : $('#f_new_password2').val(),
			code : $('#f_code').val()
		};
		function handler(data) {
			if (data.success) {
				alert('重置密码成功');
			} else {
				alert(data.msg);
			}
		}
		$.get('../user/resetPassword.do', params, handler, 'json');
	});
}
$(function() {
	loadLocalStorage();
	bindResetPassword();
	bindLogin();
	bindSendCode1();
	bindRegister();
	bindSendCode2();
})