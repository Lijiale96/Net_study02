package com.sxt.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * 基本类型:接收端
 * Address already in use :Cannot bind 同一个协议下端口不允许冲突
 * 1、使用DatagramSocket 指定端口 创建接收端
 * 2、准备容器 封装成DatagramPacket 包裹
 * 3、阻塞式接收包裹receive（DatagramPacket p)
 * 4、分析数据  将字节数组还原为对应的类型
 * byte[] getData()
 *        getLength()
 * 5、释放资源
 */
public class UdpTypeServer {

	public static void main(String[] args) throws IOException {	
		System.out.println("接收方启动中...");
//		1、使用DatagramSocket 指定端口 创建接收端
		DatagramSocket server = new DatagramSocket(6666);
//		 * 2、准备容器 封装成DatagramPacket 包裹
		byte[] container =new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container,0,container.length);
//		 * 3、阻塞式接收包裹receive（DatagramPacket p)
		server.receive(packet);//阻塞式
//		 * 4、分析数据  将字节数组还原为对应的类型
//		 * byte[] getData()
//		 *        getLength()
		byte[] datas = packet.getData();
		int len = packet.getLength();
		
		//读取
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		//顺序与写出一致
		String msg =dis.readUTF();
		boolean flag = dis.readBoolean();
		int age =dis.readInt();
		char ch =dis.readChar();
		System.out.println(msg+"-->"+flag);
//		 * 5、释放资源
		server.close();
	}

}
