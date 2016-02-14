package com.whck.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.whck.dao.UserDao;
import com.whck.dmo.User;

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
		request.setAttribute("user", this.userDao.findOne(username));
		return "user/update";
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(User user) {
		Map<String, Object> map = new HashMap<>();
		User data = this.userDao.findOne(user.getUsername());
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
		user.setPassword("000000");
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
}
