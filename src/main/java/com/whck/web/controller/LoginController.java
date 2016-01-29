package com.whck.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whck.dmo.User;
import com.whck.service.user.UserService;
import com.whck.web.keys.Keys;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("page.do")
	public String page() {
		return "login";
	}

	@RequestMapping(value="submit.do",method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		try {
			User user = this.userService.login(username, password);
			session.setAttribute(Keys.LOGIN_SESSION_DATA, user);
			session.removeAttribute(Keys.LOGIN_ERROR_MSG);
			return "welcome";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute(Keys.LOGIN_ERROR_MSG, e.getMessage());
			return "login";
		}
	}
}
