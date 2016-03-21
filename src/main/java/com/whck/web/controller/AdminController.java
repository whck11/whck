package com.whck.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;
import com.whck.dao.RoleDao;
import com.whck.dao.UserDao;
import com.whck.dmo.Role;
import com.whck.dmo.User;
import com.whck.service.user.UserService;
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
		data = this.userDao.findByUsername(data.getUsername());
		if (data.getPassword().equals(Md5Util.getSecurityCode(oldPassword))) {
			data.setPassword(Md5Util.getSecurityCode(newPassword));
			this.userDao.save(data);
			map.put("result", true);
		} else {
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
		User data = this.userDao.findByUsername(user.getUsername());
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
		user=userDao.findByUsername(user.getUsername());
		this.userDao.delete(user);
		map.put("success", true);
		return map;
	}

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String remarks, String phone, String username, String address, String cname,
			String name, Integer roleId) {
		Map<String, Object> map = new HashMap<>();
		User user = new User();
		user.setUsername(username);
		user.setAddress(address);
		user.setCname(cname);
		user.setName(name);
		user.setPhone(phone);
		user.setRemarks(remarks);
		if (user.getUsername() == null || StringUtil.isAllWhitespace(user.getUsername())) {
			map.put("msg", "用户名不能为空");
			return map;
		}
		if (this.userDao.findByUsername(user.getUsername()) != null) {
			map.put("msg", "该用户名已被使用");
			return map;
		}
		Role role = roleDao.findOne(roleId);
		user.setRegDate(new Date());
		user.setPassword(Md5Util.getSecurityCode("000000"));
		user.setRole(role);
		try {
			this.userDao.save(user);
			map.put("success", true);
			map.put("msg", "初始密码为000000");
		} catch (Exception e) {
			map.put("msg", e.getMessage());
		}

		return map;
	}

	@Autowired
	private RoleDao roleDao;

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
	
	@Autowired
	private UserService userService;

	@RequestMapping("login/page.do")
	public String page() {
		return "login";
	}

	@RequestMapping("login/logOut.do")
	public String logOut(HttpSession session) {
		session.removeAttribute(Keys.LOGIN_SESSION_DATA);
		return "login";
	}

	@RequestMapping("login/error.do")
	public String error() {
		return "error";
	}

	@RequestMapping(value = "login/submit.do", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		try {
			User user = this.userService.login(username, Md5Util.getSecurityCode(password));
			if(user.getRole().getId()>2){
				throw new Exception("您没有管理员权限");
			}
			session.setAttribute(Keys.LOGIN_SESSION_DATA, user);
			session.removeAttribute(Keys.LOGIN_ERROR_MSG);
			return "redirect:/admin/main.do";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute(Keys.LOGIN_ERROR_MSG, e.getMessage());
			return "login";
		}
	}
}
