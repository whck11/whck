package com.whck.service.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.DcDao;
import com.whck.dao.DeviceDao;
import com.whck.dmo.Device;
import com.whck.util.SocketAnalysis;
/**
 * 
 * @author 马健原
 * 2016-2-18
 *套接字服务器的端口为9090
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService, Runnable {

	private ServerSocket server;
	@Autowired
	private SocketAnalysis analysis;
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
			this.server = new ServerSocket(9090);
			while (true) {
				Socket socket = server.accept();
				String tmp = this.analysis.getString(socket);
				Device device = this.analysis.getDevice(tmp);
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
