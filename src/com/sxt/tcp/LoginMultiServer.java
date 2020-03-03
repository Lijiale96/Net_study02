package com.sxt.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 模拟登录 单向
 * 创建服务器
 * 1、指定端口使用ServerSocket创建服务器
 * 2、阻塞式等待连接 accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 * 
 * 
 */
public class LoginMultiServer {
public static void main(String[] args) throws IOException {
	System.out.println("---Server----");
//	* 1、指定端口使用ServerSocket创建服务器
	ServerSocket server = new ServerSocket(8888);
	boolean isRunning = true;
//	 * 2、阻塞式等待连接 accept
	while (isRunning) {
		Socket client = server.accept();
		new Thread(new Channel(client)).start();
	}
	server.close();
}
//一个channel就代表月个客户端
static class Channel implements Runnable{
	private Socket client;
	//输入流
	private DataInputStream dis;
	private DataOutputStream dos;
    public Channel(Socket client) {
    	this.client = client;
		try {
			dis = new DataInputStream(client.getInputStream());
			//输出
			 dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			release();
		}
    }
    
  //接收数据
  	private String receive() {
  		String datas="";
  		try {
  			datas = dis.readUTF();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return datas;
  	}
  	//释放资源
  	private void release() {
//		 * 4、释放资源
		try {
			if (null!=dos) {
				dos.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (null!=dis) {
				dis.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (null!=client) {
				client.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
    //发送数据
  	private void send(String msg) {
  		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("一个客户端建立了连接");
//		 * 3、操作：输入输出流操作
	
		String uname ="";
		String upwd ="";
		
	//分析
		String[] dataArray =receive().split("&");
		for (String info:dataArray) {
			String[] userInfo = info.split("=");
			System.out.println(userInfo[0]+"-->"+userInfo[1]);
			if (userInfo[0].equals("uname")) {
				System.out.println("你的用户名为:"+userInfo[1]);
				uname=userInfo[1];
			}
			else if(userInfo[0].equals("upwd")) {
				System.out.println("你的密码为:"+userInfo[1]);
				upwd =userInfo[1];
			}
		}
		if (uname.equals("UJS")&&upwd.equals("Lijiale")) {//成功
			send("登录成功，欢迎回来");
		}else {//失败
			send("登录失败，密码错误");
		}
		release();
	}
	
}
}
