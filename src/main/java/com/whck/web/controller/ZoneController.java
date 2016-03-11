package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.whck.web.keys.Keys;

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

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String zoneName, Double area, String longitude, String latitude,HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		Zone zone = new Zone();
		zone.setArea(area);
		zone.setLatitude(latitude);
		zone.setLongitude(longitude);
		zone.setZoneName(zoneName);
		User user = (User) session.getAttribute(Keys.LOGIN_SESSION_DATA);
		zone.setUser(user);
		this.zoneDao.save(zone);
		map.put("success", true);
		return map;
	}

	@RequestMapping("updatePage.do")
	public String updatePage(Integer zoneId, HttpServletRequest request) {
		Zone zone = this.zoneDao.findOne(zoneId);
		request.setAttribute("zone", zone);
		return "zone/update";
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(Integer zoneId, String zoneName, String latitude, String longitude, Double area,
			String remarks, String username) {
		Map<String, Object> map = new HashMap<>();
		Zone data = this.zoneDao.findOne(zoneId);
		data.setZoneName(zoneName);
		data.setArea(area);
		data.setLatitude(latitude);
		data.setLongitude(longitude);
		data.setRemarks(remarks);
		if (null != username && username.length() != 0) {
			data.setUser(userDao.findOne(username));
		}
		zoneDao.save(data);
		map.put("result", true);
		return map;
	}

	@RequestMapping("remove.do")
	@ResponseBody
	public Map<String, Object> remove(Zone zone) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.zoneDao.delete(zone.getZoneId());
		map.put("success", true);
		return map;
	}

}
