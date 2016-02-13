package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.UserDao;
import com.whck.dmo.User;
import com.whck.web.keys.Keys;

@Controller
@RequestMapping("admin")
public class AdminController {

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

	@RequestMapping("saveInfo.do")
	@ResponseBody
	public Map<String, Object> saveInfo(User user,HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User data = this.userDao.findOne(user.getUsername());
		data.setAddress(user.getAddress());
		data.setPhone(user.getPhone());
		data.setCname(user.getCname());
		data.setName(user.getName());
		data.setRemarks(user.getRemarks());
		userDao.save(data);
		session.setAttribute(Keys.LOGIN_SESSION_DATA,data);
		map.put("result", true);
		return map;
	}
}
