package com.wisewater.faced.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;

public class WebSocketClient {

	public String SocketClient(int transCode, String account) {
		Socket socket = null;
		DataInputStream is = null;
		DataOutputStream os = null;
		long mtime = Calendar.getInstance().getTimeInMillis();
		try {
			socket = new Socket("192.168.10.51", 8844);
			System.out.println("建立Socket连接时间：" + (Calendar.getInstance().getTimeInMillis() - mtime));//表示时间的间隔时间
			socket.setSoTimeout(5000);//表示如果连接超时，客户端将强制断开

			os = new DataOutputStream(socket.getOutputStream());//建立输出流

			byte[] stream = ("{\"account_no\":\"" + account + "\"}").getBytes("utf-8");
			byte[] outTransCode = new byte[1];
			if (transCode == 11) {
				outTransCode[0] = (byte) 0x11;
			} else if (transCode == 12) {
				outTransCode[0] = (byte) 0x12;
			} else if (transCode == 13) {
				outTransCode[0] = (byte) 0x13;
			} else {
				outTransCode[0] = (byte) 0x10;
			}
			System.out.println("transCode:" + outTransCode[0]);
			os.write(outTransCode);
			os.writeInt(stream.length);
			System.out.println("dataLength:" + stream.length);
			os.write(stream);
			System.out.println("data:" + new String(stream, "utf-8"));
			os.flush();
			System.out.println("发送数据包时间：" + (Calendar.getInstance().getTimeInMillis() - mtime));//发送数据所用时间
			System.out.println("打开输入流");
			is = new DataInputStream(socket.getInputStream());//建立输入关系
			byte inTransCode = is.readByte();
			System.out.println("transCode:" + inTransCode);
			int dataLength = is.readInt();
			System.out.println("dataLength:" + dataLength);
			byte[] data = new byte[dataLength];
			int offset = 0;
			while (dataLength > offset) {
				offset += is.read(data, offset, dataLength - offset);
			}
			System.out.println("\n结束业务时间：" + (Calendar.getInstance().getTimeInMillis() - mtime));
			String input = new String(data, "utf-8");
			System.out.println(input);
			return input;
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (Exception exc) {
				return null;
			}
			try {
				if (is != null)
					is.close();
			} catch (Exception exc) {
				return null;
			}
			try {
				if (socket != null)
					socket.close();
			} catch (Exception exc) {
				return null;
			}
		}
		return null;
	}

}
