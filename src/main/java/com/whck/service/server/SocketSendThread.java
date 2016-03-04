package com.whck.service.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
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

	@Override
	public Boolean call() throws Exception {
		Socket socket = null;
		BufferedOutputStream out=null;
		try {
			socket = new Socket(device.getIp(), device.getPort());
			out=new BufferedOutputStream(socket.getOutputStream());
			byte[] bs = resolver.deResolve(device.getDc());
			out.write(bs);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out!=null) {
				out.close();
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
