package com.whck.service.server;

import java.io.IOException;
import java.io.PrintWriter;
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
		PrintWriter writer=null;
		try {
			socket = new Socket(device.getIp(), device.getPort());
			String str = resolver.deResolve(device.getDc());
			writer=new PrintWriter(socket.getOutputStream());
			writer.print(str);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer!=null) {
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
