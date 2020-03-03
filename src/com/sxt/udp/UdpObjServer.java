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
public class UdpObjServer {

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
		
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		//˳����д��һ��
		String msg =ois.readUTF();
		boolean flag = ois.readBoolean();
		int age =ois.readInt();
		char ch =ois.readChar();
		
		System.out.println(flag);
		
		Object str =ois.readObject();
		Object date = ois.readObject();
		Object employee = ois.readObject();
		
		if (str instanceof String) {
			String strObj = (String) str;
			System.out.println(strObj);
		}
		if (date instanceof Date) {
			Date dateObj = (Date) date;
			System.out.println(dateObj);
		}
		if (employee instanceof Employee) {
			Employee empObj = (Employee)employee;
			System.out.println(empObj.getName()+"-->"+empObj.getSalary());
		}
		
//		 * 5���ͷ���Դ
		server.close();
	}

}
