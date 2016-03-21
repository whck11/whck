package com.whck.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.PublishMenuDao;
import com.whck.dao.PublishResourceDao;
import com.whck.dmo.PublishMenu;
import com.whck.dmo.PublishResource;
import com.whck.dmo.User;
import com.whck.web.keys.Keys;

@Controller
public class PublishController {
	@RequestMapping("admin/publish/addMenu.do")
	@ResponseBody
	public Map<String, Object> addMenu(PublishMenu menu) {
		Map<String, Object> map = new HashMap<>();
		this.publishMenuDao.save(menu);
		map.put("success", true);
		return map;
	}

	@RequestMapping("admin/publish/updateMenu.do")
	@ResponseBody
	public Map<String, Object> updateMenu(PublishMenu menu) {
		Map<String, Object> map = new HashMap<>();
		PublishMenu data = this.publishMenuDao.findOne(menu.getId());
		if (data == null) {
			map.put("msg", "菜单不存在");
		} else {
			this.publishMenuDao.save(menu);
			map.put("success", true);
		}
		return map;
	}

	@RequestMapping("admin/publish/deleteMenu.do")
	@ResponseBody
	public Map<String, Object> deleteMenu(PublishMenu menu) {
		Map<String, Object> map = new HashMap<>();
		this.publishMenuDao.delete(menu);
		map.put("success", true);
		return map;
	}

	@RequestMapping("publish/findMenu.do")
	@ResponseBody
	public Map<String, Object> findMenu() {
		Map<String, Object> map = new HashMap<>();
		List<PublishMenu> list = this.publishMenuDao.findAll();
		map.put("list", list);
		map.put("success", true);
		return map;
	}

	@RequestMapping("admin/publish/addResource.do")
	@ResponseBody
	public Map<String, Object> addResource(PublishResource resource, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		resource.setCreateBy((User) session.getAttribute(Keys.LOGIN_SESSION_DATA));
		resource.setUpdateDate(new Date());
		this.publishResourceDao.save(resource);
		map.put("success", true);
		return map;
	}

	@RequestMapping("admin/publish/updateResource.do")
	@ResponseBody
	public Map<String, Object> updateResource(PublishResource resource) {
		Map<String, Object> map = new HashMap<>();
		PublishResource data = this.publishResourceDao.findOne(resource.getId());
		if (data == null) {
			map.put("msg", "发布信息不存在");
		} else {
			resource.setUpdateDate(new Date());
			this.publishResourceDao.save(resource);
			map.put("success", true);
		}
		return map;
	}

	@RequestMapping("admin/publish/deleteResource.do")
	@ResponseBody
	public Map<String, Object> deleteResource(PublishResource resource) {
		Map<String, Object> map = new HashMap<>();
		this.publishResourceDao.delete(resource);
		map.put("success", true);
		return map;
	}

	@RequestMapping("findResources.do")
	@ResponseBody
	public Map<String, Object> findResourcesByMenu(PublishMenu menu) {
		Map<String, Object> map = new HashMap<>();
		List<PublishResource> list = this.publishResourceDao.findByMenu_Id(menu.getId());
		map.put("list", list);
		map.put("success", true);
		return map;
	}

	@Autowired
	private PublishMenuDao publishMenuDao;
	@Autowired
	private PublishResourceDao publishResourceDao;
}
