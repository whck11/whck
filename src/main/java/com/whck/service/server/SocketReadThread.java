package com.whck.service.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader reader = null;
		StringBuilder builder=new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tmp=null;
			while((tmp=reader.readLine())!=null){
				builder.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.command=builder.toString();
		this.dc=resolver.resolve(command);
		List<Device> devices = dc.getDevices();
		for (Device device : devices) {
			device.setState(1);
		}
		this.dcService.save(dc);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
