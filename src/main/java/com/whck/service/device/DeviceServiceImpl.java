package com.whck.service.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.DcDao;
import com.whck.dao.DeviceDao;
import com.whck.dmo.Device;
import com.whck.util.SocketUtil;

/**
 * 
 * @author 马健原 2016-2-18 
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService, Runnable {

	private ServerSocket server;
	@Autowired
	private SocketUtil socketUtil;
	@Autowired
	private DeviceDao deviceDao;

	@PostConstruct
	public void openServerSocket() {
		Executors.newFixedThreadPool(1).execute(this);
	}

	@PreDestroy
	public void closeServerSocket() {
		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Device> findAll() {
		return this.deviceDao.findAll();
	}

	@Autowired
	private DcDao dcDao;

	@Override
	public void run() {
		try {
			this.server = new ServerSocket(SocketUtil.SERVER_SOCKET_PORT);
			while (true) {
				Socket socket = server.accept();
				String tmp = this.socketUtil.readString(socket);
				ObjectMapper mapper = new ObjectMapper();
				Device device = mapper.readValue(tmp, Device.class);
				device.setDc(dcDao.findOne(device.getDc().getId()));
				this.deviceDao.save(device);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

}
