package com.whck.service.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static final Log log = LogFactory.getLog(DeviceServiceImpl.class);

	private ServerSocket server;
	@Autowired
	private SocketUtil socketUtil;
	@Autowired
	private DeviceDao deviceDao;

	private ExecutorService executor;

	@PostConstruct
	public void openServerSocket() {
		this.executor = Executors.newFixedThreadPool(1);
		executor.execute(this);
	}

	@PreDestroy
	public synchronized void closeServerSocket() {
		try {
			executor.shutdown();
			if (!this.server.isClosed()) {
				this.server.close();
			}
			log.debug("serverSocket是否已经关闭：" + this.server.isClosed());
		} catch (IOException e) {
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
			log.debug("运行ServerSocket服务器,端口为：" + SocketUtil.SERVER_SOCKET_PORT);
			while (!this.server.isClosed()) {
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
		}
	}

}
