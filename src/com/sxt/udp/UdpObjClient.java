package com.sxt.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;

/*
 * �������ͣ�
 * ���Ͷ�
 * 1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
 * 2��׼������  һ��ת���ֽ�����
 * 3����װ��DatagramPacket ��������Ҫָ��Ŀ�ĵ�
 * 4�����Ͱ���send��DatagramPacket p)*
 * 5���ͷ���Դ
 */
public class UdpObjClient {

	public static void main(String[] args) throws IOException {
		
System.out.println("���ͷ�������...");

//1��ʹ��DatagramSocket ָ���˿� �������Ͷ�
DatagramSocket client = new DatagramSocket(8888);

//* 2��׼������  һ��ת���ֽ�����

//д��
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));
//������������+����
oos.writeUTF("���������");
oos.writeBoolean(false);
oos.writeInt(18);
oos.writeChar('a');
//����
oos.writeObject("˭������ζ");
oos.writeObject(new Date());
Employee emp = new Employee("����",400);
oos.writeObject(emp);

oos.flush();
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
