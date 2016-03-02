package com.whck.service.device;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.DeviceDao;
import com.whck.dmo.Device;

/**
 * 
 * @author 马健原 2016-2-18
 */
@Service
@Transactional
public class DeviceServiceImpl  implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	
	@Override
	public List<Device> findAll() {
		return this.deviceDao.findAll();
	}



}
