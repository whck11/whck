package com.whck.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
