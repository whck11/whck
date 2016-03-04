package com.whck.service.dc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whck.dao.DcDao;
import com.whck.dao.DeviceDao;
import com.whck.dao.ZoneDao;
import com.whck.dmo.Dc;
import com.whck.dmo.Device;
import com.whck.dmo.Zone;
import com.whck.service.server.SocketSendThread;

@Service
@Transactional
public class DcServiceImpl implements DcService {
	@Autowired
	private ZoneDao zoneDao;

	@Autowired
	private DcDao dcDao;

	@Autowired
	private DeviceDao deviceDao;

	@Override
	public void save(Dc dc) {
		Zone zone = dc.getZone();
		Zone tmp1 = zoneDao.findByZoneName(zone.getZoneName());
		if (tmp1 == null) {
			zoneDao.save(zone);
		}
		dcDao.save(dc);
		List<Device> devices = dc.getDevices();
		for (Device device : devices) {
			Device tmp2 = this.deviceDao.findByDeviceName(device.getDeviceName());
			if (tmp2 == null) {
				deviceDao.save(devices);
			}
		}

	}

	private ExecutorService executor;

	@PostConstruct
	private void init() {
		this.executor = Executors.newCachedThreadPool();
	}

	@PreDestroy
	private void destory() {
		this.executor.shutdown();
	}

	@Autowired
	private CommandResolver resolver;

	@Override
	public Map<Integer, Boolean> sendCommand(Dc dc) throws Exception {
		Map<Integer, Boolean> map = new HashMap<>();
		for (Device d : dc.getDevices()) {
			SocketSendThread socketSendThread = new SocketSendThread(d, resolver);
			Future<Boolean> future = executor.submit(socketSendThread);
			map.put(d.getDeviceId(), future.get());
		}
		return map;
	}

}
