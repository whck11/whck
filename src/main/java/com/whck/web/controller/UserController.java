package com.whck.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.whck.dao.UserDao;
import com.whck.dmo.User;
import com.whck.util.Md5Util;
import com.whck.util.PhoneCodeUtil;
import com.whck.web.keys.Keys;

@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	UserDao userDao;

	@RequestMapping("userList.do")
	public String userList() {
		return "user/list";
	}

	@RequestMapping("addPage.do")
	public String addPage() {
		return "user/add";
	}

	@RequestMapping("updatePage.do")
	public String updatePage(String username, HttpServletRequest request) {
		request.setAttribute("user", this.userDao.findByUsername(username));
		return "user/update";
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(User user) {
		Map<String, Object> map = new HashMap<>();
		User data = this.userDao.findByUsername(user.getUsername());
		data.setAddress(user.getAddress());
		data.setPhone(user.getPhone());
		data.setCname(user.getCname());
		data.setName(user.getName());
		data.setRemarks(user.getRemarks());
		userDao.save(data);
		map.put("result", true);
		return map;
	}

	@RequestMapping("remove.do")
	@ResponseBody
	public Map<String, Object> remove(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.userDao.delete(user.getUsername());
		map.put("success", true);
		return map;
	}

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(User user) {
		Map<String, Object> map = new HashMap<>();
		if (user.getUsername() == null || StringUtil.isAllWhitespace(user.getUsername())) {
			map.put("msg", "邮箱不能为空");
			return map;
		}
		if (this.userDao.findOne(user.getUsername()) != null) {
			map.put("msg", "该邮箱已被使用");
			return map;
		}
		user.setRegDate(new Date());
		user.setPassword(Md5Util.getSecurityCode("000000"));
		this.userDao.save(user);
		map.put("success", true);
		map.put("msg", "初始密码为000000");
		return map;
	}

	@RequestMapping("userListAjax.do")
	@ResponseBody
	public Map<String, Object> userListAjax(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<User> pageList = this.userDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}

	@RequestMapping("findAll.do")
	@ResponseBody
	public List<User> findAll() {
		return this.userDao.findAll();
	}

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
