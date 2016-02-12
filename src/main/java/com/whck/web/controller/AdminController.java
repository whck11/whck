package com.whck.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whck.dmo.User;
import com.whck.web.keys.Keys;

@Controller
@RequestMapping("admin")
public class AdminController {
	@RequestMapping("main.do")
	public String main(HttpSession session) {
		User user = (User) session.getAttribute(Keys.LOGIN_SESSION_DATA);
		if (user == null || !user.getIsAdmin()) {
			return "login";
		} else {
			return "main";
		}
	}
	
}
