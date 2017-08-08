/**
 * 
 */
package com.wisewater.faced.util;

import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.wisewater.wechatpublic.util.DateUtils;


/**
 * @author XingXinglvcha
 * 2016年6月15日 下午1:49:42
 */
public class SocketClient extends Thread{

	// 客户读到的信息
		private String messageRead = "";
		// 客户写入的信息
		//private static String messageWrite = "";
		// 读取线程是否停止
	 	private boolean isStopped = false;
	 	// 读取字符串信息是否暂停
	 	private boolean isStringPaused = false;
	 	
	 	private static final int SERVER_PORT = 8082;//端口号 
	    private static final String SERVER_IP = "10.10.1.8";//连接IP
	    private static final String ENCONDING = "gbk";//字符集
	    
	    // 客户 一个客户端只有一个客户
	 	private Socket cSocket;
	    private SocketClient client;
	 	 // 客户的读写流
	 	private PrintWriter out;
	 	private DataInputStream input;
		
	 	/**
	 	 * 建立Socket连接
	 	 * @param messageWrite 发送的内容
	 	 * @param acx 异步上下文
	 	 * @param action 完成后执行的动作
	 	 */
	 	public SocketClient(String messageWrite) {
	 		 // 建立客户端
	    	try {
				cSocket = new Socket(SERVER_IP, SERVER_PORT);
				out = new PrintWriter(new OutputStreamWriter(cSocket.getOutputStream(), ENCONDING), true);
	            input = new DataInputStream(cSocket.getInputStream());  
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
			start();
	     	System.out.println("Client ready"); 
	     	//messageWrite = msg;
	     	this.send(messageWrite);
			
		}
	 	public SocketClient() {
	 		
	 	}
		
		/**
		 * 重写线程run来读取
		 */
		public void run() {
			// 判断是否停止
			while (!isStopped) {
				// 是否暂停客户端读取字符串线程
				// 可以设置一个时间，。。。超时断开
				while (!isStringPaused) {
					byte[] buffer = null;
				    try {
						buffer = new byte[input.available()];
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    if(buffer.length != 0){
					    //System.out.println("length="+buffer.length);
					    // 读取缓冲区
					    try {
							input.read(buffer);
						} catch (IOException e) {
							close();
							e.printStackTrace();
							break;
						}
					    // 转换字符串
					    messageRead = new String(buffer);
					    
						try {
							FileWriter f = new FileWriter("D:\\logs\\szwater\\szwater"+DateUtils.getDateTime("yyyy-MM")+".txt", true);
							f.write("\r\n"+DateUtils.getDateTime()+"接收:"+messageRead);
							f.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    
					    System.out.println("receive content:" + messageRead);
					    close();
					    return;
					}
				}

			}
		}

		public void send(String message) {
			try {
				//bous.write(message.getBytes());
				getOut().println(message);
				try {
					FileWriter f = new FileWriter("D:\\logs\\szwater\\szwater"+DateUtils.getDateTime("yyyy-MM")+".txt", true);
					f.write("\r\n"+DateUtils.getDateTime()+"发送:"+message);
					f.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Client send message:" + message);
				getOut().flush();
			} catch (Exception e) {
				e.printStackTrace();
				close();
			}
		}

		/**
		 * 关闭socket的方法
		 */
		public void close() {
			// 线程停止
			isStringPaused = true;
			isStopped = true;

			try {
				// 读写流关闭
				input.close();
				out.close();
				// socket关闭
				cSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Client closed");
			return;
		}
		
		public PrintWriter getOut() {
			return out;
		}

		public void setOut(PrintWriter out) {
			this.out = out;
		}

		public SocketClient getClient() {
			return client;
		}

		public void setClient(SocketClient SocketClient) {
			this.client = SocketClient;
		}

		public String getMessageRead() throws InterruptedException {
			while (messageRead == "") {
				sleep(1000);
			}
			return messageRead;
		}

		public void setMessageRead(String messageRead) {
			this.messageRead = messageRead;
		}
}
