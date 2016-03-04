package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whck.dmo.Dc;
import com.whck.service.dc.DcService;

@RequestMapping("dc")
@Controller
public class DcController {
	@RequestMapping("sendCommand")
	public Map<String, Object> sendCommand(Dc dc) {
		Map<String, Object> map = new HashMap<>();
		try {
			Map<Integer, Boolean> result = dcService.sendCommand(dc);
			dcService.save(dc);
			map.put("success", true);
			map.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@Autowired
	private DcService dcService;
}
