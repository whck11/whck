package com.whck.service.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

import com.whck.dmo.Device;
import com.whck.util.CommandResolver;

public class SocketSendThread implements Callable<Boolean> {
	private Device device;

	public SocketSendThread(Device device, CommandResolver resolver) {
		this.resolver = resolver;
		this.device = device;
	}

	private CommandResolver resolver;
	private String command;

	@Override
	public Boolean call() throws Exception {
		Socket socket = null;
		BufferedWriter writer = null;
		try {
			socket = new Socket(device.getIp(), device.getPort());
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.command=resolver.deResolve(device.getDc());
			writer.write(this.command);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
