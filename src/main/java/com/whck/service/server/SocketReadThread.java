package com.whck.service.server;

import java.net.Socket;
import java.util.List;

import com.whck.dmo.Dc;
import com.whck.dmo.Device;
import com.whck.service.dc.CommandResolver;
import com.whck.service.dc.DcService;
import com.whck.util.SocketUtil;

public class SocketReadThread extends Thread {

	public SocketReadThread(Socket socket, CommandResolver commandResolver, DcService dcService) {
		super();
		this.socket = socket;
		this.resolver = commandResolver;
		this.dcService = dcService;
	}

	private DcService dcService;
	private String command;
	private Socket socket;
	private CommandResolver resolver;
	private Dc dc;

	public String getCommand() {
		return command;
	}

	public Dc getDc() {
		return dc;
	}

	@Override
	public void run() {
		if (this.socket == null || !socket.isConnected()) {
			return;
		}
		List<Device> devices = dc.getDevices();
		for (Device device : devices) {
			device.setState(1);
		}
		this.command = SocketUtil.readString(socket);
		this.dc = this.resolver.resolve(command);
		this.dcService.save(dc);
	}
}
