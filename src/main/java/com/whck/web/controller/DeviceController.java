package com.whck.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dmo.Device;
import com.whck.service.device.DeviceService;

@RequestMapping("public/device")
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
}
