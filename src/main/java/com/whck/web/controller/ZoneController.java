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
import com.whck.dao.ZoneDao;
import com.whck.dmo.User;
import com.whck.dmo.Zone;

@RequestMapping("zone")
@Controller
public class ZoneController {

	@RequestMapping("main.do")
	public String main() {
		return "zone/main";
	}

	@Autowired
	private ZoneDao zoneDao;

	@RequestMapping("list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<Zone> pageList = this.zoneDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}

	@RequestMapping("addPage.do")
	public String addPage() {
		return "zone/add";
	}

	@Autowired
	private UserDao userDao;

	@RequestMapping("updatePage.do")
	public String updatePage() {
		return "zone/update";
	}

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String zoneName, Double area, String longitude, String latitude, String remarks,
			String username) {
		Map<String, Object> map = new HashMap<>();
		Zone zone = new Zone();
		zone.setArea(area);
		zone.setLatitude(latitude);
		zone.setLongitude(longitude);
		zone.setRemarks(remarks);
		zone.setZoneName(zoneName);
		if (null != username && !username.trim().equals("")) {
			User user = this.userDao.findByUsername(username);
			zone.setUser(user);
		}
		this.zoneDao.save(zone);
		map.put("success", true);
		return map;
	}
}
