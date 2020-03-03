package com.sxt.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * ��������:���ն�
 * Address already in use :Cannot bind ͬһ��Э���¶˿ڲ������ͻ
 * 1��ʹ��DatagramSocket ָ���˿� �������ն�
 * 2��׼������ ��װ��DatagramPacket ����
 * 3������ʽ���հ���receive��DatagramPacket p)
 * 4����������  ���ֽ����黹ԭΪ��Ӧ������
 * byte[] getData()
 *        getLength()
 * 5���ͷ���Դ
 */
public class UdpTypeServer {

	public static void main(String[] args) throws IOException {	
		System.out.println("���շ�������...");
//		1��ʹ��DatagramSocket ָ���˿� �������ն�
		DatagramSocket server = new DatagramSocket(6666);
//		 * 2��׼������ ��װ��DatagramPacket ����
		byte[] container =new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container,0,container.length);
//		 * 3������ʽ���հ���receive��DatagramPacket p)
		server.receive(packet);//����ʽ
//		 * 4����������  ���ֽ����黹ԭΪ��Ӧ������
//		 * byte[] getData()
//		 *        getLength()
		byte[] datas = packet.getData();
		int len = packet.getLength();
		
		//��ȡ
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		//˳����д��һ��
		String msg =dis.readUTF();
		boolean flag = dis.readBoolean();
		int age =dis.readInt();
		char ch =dis.readChar();
		System.out.println(msg+"-->"+flag);
//		 * 5���ͷ���Դ
		server.close();
	}

}
