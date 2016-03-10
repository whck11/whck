package com.whck.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.DcDao;
import com.whck.dao.DeviceDao;
import com.whck.dmo.Dc;
import com.whck.dmo.Device;
import com.whck.service.device.DeviceService;

@RequestMapping("device")
@Controller
public class DeviceController {
	@RequestMapping("findAll.do")
	@ResponseBody
	public Map<String, Object> loadDevices() {
		Map<String, Object> map = new HashMap<>();
		List<Device> lists = this.deviceService.findAll();
		map.put("devices", lists);
		return map;
	}

	@Autowired
	private DeviceService deviceService;

	@RequestMapping("main.do")
	public String main() {
		return "device/main";
	}

	@Autowired
	private DeviceDao deviceDao;

	@RequestMapping("list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<Device> pageList = this.deviceDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}

	@RequestMapping("addPage.do")
	public String addPage() {
		return "device/add";
	}

	@Autowired
	private DcDao dcDao;

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String deviceName, String description, String ip, Integer port, Integer ctrlMode,
			Integer ctrlWay, String dc, Integer state) {
		Map<String, Object> map = new HashMap<>();
		Device device = new Device();
		device.setDeviceName(deviceName);
		device.setDescription(description);
		device.setIp(ip);
		device.setPort(port);
		device.setCtrlMode(ctrlMode);
		device.setCtrlWay(ctrlWay);
		Dc objDc = dcDao.findOne(dc);
		device.setDc(objDc);
		device.setState(state);
		this.deviceDao.save(device);
		map.put("success", true);
		return map;
	}

	@RequestMapping("updatePage.do")
	public String updatePage(Integer deviceId, HttpServletRequest request) {
		Device device = deviceDao.findOne(deviceId);
		request.setAttribute("device", device);
		return "device/update";
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update() {
		Map<String, Object> map = new HashMap<>();
		return map;
	}
}
