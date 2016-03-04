package com.whck.service.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import com.whck.dmo.Dc;
import com.whck.dmo.Device;
import com.whck.service.dc.DcService;
import com.whck.util.CommandResolver;

public class SocketReadThread extends Thread {

	public SocketReadThread(Socket socket, CommandResolver commandResolver, DcService dcService) {
		super();
		this.socket = socket;
		this.resolver = commandResolver;
		this.dcService = dcService;
	}

	private DcService dcService;
	private byte[] command;
	private Socket socket;
	private CommandResolver resolver;
	private Dc dc;

	public byte[] getCommand() {
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
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(socket.getInputStream());
			command = new byte[in.available()];
			in.read(command);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.dc = this.resolver.resolve(command);
		this.dcService.save(dc);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
