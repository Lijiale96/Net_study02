package com.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/*
 * �������̣�
 * ���Ͷ�
 * 1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
 * 2��׼������  һ��ת���ֽ�����
 * 3����װ��DatagramPacket ��������Ҫָ��Ŀ�ĵ�
 * 4�����Ͱ���send��DatagramPacket p)*
 * 5���ͷ���Դ
 */
public class UdpClient {

	public static void main(String[] args) throws IOException {
		
System.out.println("���ͷ�������...");

//1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
DatagramSocket client = new DatagramSocket(8888);

//* 2��׼������  һ��ת���ֽ�����
String data ="��������֣������Ͼ�����꿣�����";
byte[] datas = data.getBytes();

//* 3����װ��DatagramPacket ��������Ҫָ��Ŀ�ĵ�
DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
		new InetSocketAddress("localhost",6666));
//* 4�����Ͱ���send��DatagramPacket p)*
client.send(packet); 
//* 5���ͷ���Դ
client.close();
	}
}