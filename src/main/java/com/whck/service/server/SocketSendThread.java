package com.whck.service.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

import com.whck.dmo.Device;
import com.whck.service.dc.CommandResolver;

public class SocketSendThread implements Callable<Boolean> {
	private Device device;

	public SocketSendThread(Device device, CommandResolver resolver) {
		this.resolver = resolver;
		this.device = device;
	}

	private CommandResolver resolver;

	@Override
	public Boolean call() throws Exception {
		Socket socket = null;
		try {
			socket = new Socket(device.getIp(), device.getPort());
			byte[] bs = resolver.deResolve(device.getDc());
			socket.getOutputStream().write(bs);
			socket.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.getOutputStream().close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
