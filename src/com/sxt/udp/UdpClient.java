package com.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/*
 * 基本流程：
 * 发送端
 * 1、使用DatagramSocket 指定端口 创建发送端
 * 2、准备数据  一定转成字节数组
 * 3、封装成DatagramPacket 包裹，需要指定目的地
 * 4、发送包裹send（DatagramPacket p)*
 * 5、释放资源
 */
public class UdpClient {

	public static void main(String[] args) throws IOException {
		
System.out.println("发送方启动中...");

//1、使用DatagramSocket 指定端口 创建发送端
DatagramSocket client = new DatagramSocket(8888);

//* 2、准备数据  一定转成字节数组
String data ="江阴李家乐！！！南京张雨昕？？？";
byte[] datas = data.getBytes();

//* 3、封装成DatagramPacket 包裹，需要指定目的地
DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
		new InetSocketAddress("localhost",6666));
//* 4、发送包裹send（DatagramPacket p)*
client.send(packet); 
//* 5、释放资源
client.close();
	}
}
