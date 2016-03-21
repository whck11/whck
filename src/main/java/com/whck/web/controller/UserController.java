package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.UserDao;
import com.whck.dmo.User;
import com.whck.util.Md5Util;
import com.whck.util.PhoneCodeUtil;
import com.whck.web.keys.Keys;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserDao userDao;

	@RequestMapping("findByUsername.do")
	@ResponseBody
	public User findByUsername(String username) {
		User user = this.userDao.findByUsername(username);
		return user;
	}

	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Object> login(String username, String password, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User user = this.userDao.findByUsername(username);
		if (user == null) {
			map.put("msg", "登录名不存在");
		} else if (!user.getPassword().equals(Md5Util.getSecurityCode(password))) {
			map.put("msg", "密码不正确");
		} else {
			map.put("success", true);
			map.put("user", user);
		}
		session.setAttribute(Keys.LOGIN_SESSION_DATA, user);
		return map;
	}

	@RequestMapping("sendCode.do")
	@ResponseBody
	public Map<String, Object> sendCode(String phone, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			int code = 100000 + new Random().nextInt(899999);
			String result = PhoneCodeUtil.sendCode(phone, "验证码为：" + code);
			if (result.equals("1")) {
				map.put("success", true);
				map.put("code", code);
				session.setAttribute(Keys.REGISTER_ACTIVE_CODE, code);
			} else {
				map.put("msg", "发送失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@RequestMapping("register.do")
	@ResponseBody
	public Map<String, Object> register(String username, String email, String password, String password2, String phone,
			String code, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		if (!password.equals(password2)) {
			map.put("msg", "两次登陆密码不一致");
			return map;
		}
		if (session.getAttribute(Keys.REGISTER_ACTIVE_CODE) == null) {
			map.put("msg", "请重新获取验证码");
			return map;
		}
		if (!code.equals((String) session.getAttribute(Keys.REGISTER_ACTIVE_CODE))) {
			map.put("msg", "验证码不正确");
			return map;
		}
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(Md5Util.getSecurityCode(password));
		user.setPhone(phone);
		user.setActivateCode(code);
		this.userDao.save(user);
		map.put("success", true);
		return map;
	}

	@RequestMapping("resetPassword.do")
	@ResponseBody
	public Map<String, Object> forgetPassword(String phone, String password, String password2, String code,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		if (!password.equals(password2)) {
			map.put("msg", "两次登陆密码不一致");
			return map;
		}
		if (session.getAttribute(Keys.REGISTER_ACTIVE_CODE) == null) {
			map.put("msg", "请重新获取验证码");
			return map;
		}
		if (!code.equals((String) session.getAttribute(Keys.REGISTER_ACTIVE_CODE))) {
			map.put("msg", "验证码不正确");
			return map;
		}
		User user = this.userDao.findByPhone(phone);
		if (user == null) {
			map.put("msg", "该手机并未注册");
			return map;
		}
		user.setPassword(Md5Util.getSecurityCode(password));
		userDao.save(user);
		map.put("success", true);
		return map;
	}
}
