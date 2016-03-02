package com.whck.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author 马健原 2016-2-18
 *
 */
public class SocketUtil {

	public static final int SERVER_SOCKET_PORT = 9090;

	/**
	 * 
	 * @param socket
	 *            获取输入流中的字符串
	 * @return
	 */
	public static String readString(Socket socket) {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = getReader(socket);
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

	/**
	 * 
	 * @param socket
	 *            获取套接字的输入流
	 * @return
	 */
	public static BufferedReader getReader(Socket socket) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	/**
	 * 
	 * @param socket获取套接字的输出流
	 * @return
	 */
	public static PrintWriter getWriter(Socket socket) {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return printWriter;
	}
}
