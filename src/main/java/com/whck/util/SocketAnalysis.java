package com.whck.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.stereotype.Component;

import com.whck.dmo.Dc;
import com.whck.dmo.Device;

@Component
public class SocketAnalysis {

	public Device getDevice(String str) {
		Device device = new Device();
		int index = str.indexOf("<IP>");
		if (index >= 0) {
			String ip = str.substring(index + 4, str.indexOf("</IP>"));
			device.setIp(ip);
		}
		index = str.indexOf("<DEVICENAME>");
		if (index >= 0) {
			String deviceName = str.substring(index + 12, str.indexOf("</DEVICENAME>"));
			device.setDeviceName(deviceName);
		}
		index = str.indexOf("<DC>");
		if (index >= 0) {
			String id = str.substring(index + 4, str.indexOf("</DC>"));
			Dc dc = new Dc();
			dc.setId(id);
			device.setDc(dc);
		}
		return device;
	}

	public String getString(Socket socket) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = this.getReader(socket);
		String msg = null;
		try {
			while ((msg = reader.readLine()) != null) {
				builder.append(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public BufferedReader getReader(Socket socket) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	public PrintWriter getWriter(Socket socket) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return printWriter;
	}
}
