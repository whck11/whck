package com.whck.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whck.dao.UserDao;
import com.whck.dmo.User;

@RequestMapping("user")
@Controller
public class UserController {
	@Autowired
	UserDao userDao;

	@RequestMapping("userList.do")
	public String userList(HttpServletRequest request, Integer pageNumber, Integer pageSize) {
		if (pageSize == null) {
			pageSize = 10;
		}
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Page<User> pageList = this.userDao.findAll(new PageRequest(pageNumber, pageSize));
		request.setAttribute("content", pageList.getContent());
		request.setAttribute("totalPages", pageList.getTotalPages());
		request.setAttribute("totalElements", pageList.getTotalElements());
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNumber", pageList.getNumber());
		return "user/list";
	}
}
