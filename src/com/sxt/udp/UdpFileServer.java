package com.sxt.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;



/*
 * �ļ��洢:���ն�
 * Address already in use :Cannot bind ͬһ��Э���¶˿ڲ������ͻ
 * 1��ʹ��DatagramSocket ָ���˿� �������ն�
 * 2��׼������ ��װ��DatagramPacket ����
 * 3������ʽ���հ���receive��DatagramPacket p)
 * 4����������  ���ֽ����黹ԭΪ��Ӧ������
 * byte[] getData()
 *        getLength()
 * 5���ͷ���Դ
 */
public class UdpFileServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {	
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
		IOUtils.byteArrayToFile(datas, "src/copy.png");
		
	
		
//		 * 5���ͷ���Դ
		server.close();
	}

}
