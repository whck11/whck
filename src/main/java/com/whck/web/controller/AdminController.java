package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.UserDao;
import com.whck.dmo.User;
import com.whck.util.Md5Util;
import com.whck.web.keys.Keys;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping(value = "updatePassword.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePassword(String newPassword, String oldPassword, String newPassword2,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User data = (User) session.getAttribute(Keys.LOGIN_SESSION_DATA);
		data=this.userDao.findOne(data.getUsername());
		if(data.getPassword().equals(Md5Util.getSecurityCode(oldPassword))){
			data.setPassword(Md5Util.getSecurityCode(newPassword));
			this.userDao.save(data);
			map.put("result", true);
		}else{
			map.put("msg", "原密码不正确");
		}
		return map;
	}

	@RequestMapping("main.do")
	public String main(HttpSession session) {
		return "main";
	}

	@RequestMapping("info.do")
	public String info(HttpSession session) {
		return "admin/info";
	}

	@Autowired
	UserDao userDao;

	@RequestMapping(value = "saveInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveInfo(User user, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User data = this.userDao.findOne(user.getUsername());
		data.setAddress(user.getAddress());
		data.setPhone(user.getPhone());
		data.setCname(user.getCname());
		data.setName(user.getName());
		data.setRemarks(user.getRemarks());
		userDao.save(data);
		session.setAttribute(Keys.LOGIN_SESSION_DATA, data);
		map.put("result", true);
		return map;
	}
}
