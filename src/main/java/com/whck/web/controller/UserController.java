package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("userListAjax.do")
	@ResponseBody
	public Map<String, Object> userListAjax(HttpServletRequest request,int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<User> pageList = this.userDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}
}
