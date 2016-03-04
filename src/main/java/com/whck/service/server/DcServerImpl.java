package com.whck.service.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.service.dc.DcService;
import com.whck.util.CommandResolver;

@Service
public class DcServerImpl extends Thread implements DcServer {

	private ServerSocket server;
	@Autowired
	private DcService dcService;

	@Override
	public void run() {
		this.executor = Executors.newCachedThreadPool();
		while (this.isRunning) {
			try {
				Socket socket = server.accept();
				SocketReadThread thread = new SocketReadThread(socket, commandResolver, dcService);
				thread.setDaemon(true);
				executor.execute(thread);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ExecutorService executor;

	public ExecutorService getExecutor() {
		return executor;
	}

	private CommandResolver commandResolver;

	@Override
	@PostConstruct
	public void startServer() {
		try {
			this.server = new ServerSocket(9090);
			this.setDaemon(true);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isRunning = true;

	@Override
	@PreDestroy
	public void stopServer() {
		this.isRunning = false;
		try {
			if (!this.server.isClosed()) {
				this.server.close();
			}
			this.executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
