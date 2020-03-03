package com.sxt.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/*
 * �������ͣ�
 * ���Ͷ�
 * 1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
 * 2��׼������  һ��ת���ֽ�����
 * 3����װ��DatagramPacket ��������Ҫָ��Ŀ�ĵ�
 * 4�����Ͱ���send��DatagramPacket p)*
 * 5���ͷ���Դ
 */
public class UdpTypeClient {

	public static void main(String[] args) throws IOException {
		
System.out.println("���ͷ�������...");

//1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
DatagramSocket client = new DatagramSocket(8888);

//* 2��׼������  һ��ת���ֽ�����

//д��
ByteArrayOutputStream baos = new ByteArrayOutputStream();
DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
//������������
dos.writeUTF("���������");
dos.writeBoolean(false);
dos.writeInt(18);
dos.writeChar('a');
dos.flush();
byte[] datas =baos.toByteArray();
//System.out.println(datas.length);
//* 3����װ��DatagramPacket ��������Ҫָ��Ŀ�ĵ�
DatagramPacket packet = new DatagramPacket(datas,0,datas.length,
		new InetSocketAddress("localhost",6666));
//* 4�����Ͱ���send��DatagramPacket p)*
client.send(packet); 
//* 5���ͷ���Դ
client.close();
	}
}
