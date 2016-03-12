package com.whck.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whck.dao.DcDao;
import com.whck.dao.SensorDao;
import com.whck.dmo.Dc;
import com.whck.dmo.Sensor;

@Controller
@RequestMapping("sensor")
public class SensorController {
	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String collectPointName, String collectPointUnit, String dc) {
		Map<String, Object> map = new HashMap<>();
		Sensor sensor = new Sensor();
		sensor.setCollectPointName(collectPointName);
		sensor.setCollectPointUnit(collectPointUnit);
		Dc objDc = this.dcDao.findOne(dc);
		sensor.setDc(objDc);
		sensorDao.save(sensor);
		map.put("success", true);
		return map;
	}

	@Autowired
	private DcDao dcDao;
	@Autowired
	private SensorDao sensorDao;
}
