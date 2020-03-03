package com.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*��ν�����
 * ���ն�
 * Address alreadly in use :Cannot bind ͬһ��Э���¶˿ڲ������ͻ
 * 1��ʹ��DatagramSocket ָ���˿� �������ն�
 * 2��׼������ ��װ��DatagramPacket ����
 * 3������ʽ���հ���receive��DatagramPacket p)
 * 4����������
 * byte[] getData()
 *        getLength()
 * 5���ͷ���Դ
 */
public class UdpTalkServer {

	public static void main(String[] args) throws IOException {
		
		System.out.println("���շ�������...");
//		1��ʹ��DatagramSocket ָ���˿� �������ն�
		DatagramSocket server = new DatagramSocket(6666);
		
		while (true) {
//		 * 2��׼������ ��װ��DatagramPacket ����
		byte[] container =new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container,0,container.length);
//		 * 3������ʽ���հ���receive��DatagramPacket p)
		server.receive(packet);//����ʽ
//		 * 4����������
//		 * byte[] getData()
//		 *        getLength()
		byte[] datas = packet.getData();
		int len = packet.getLength();
		String data = new String(datas,0,len);
		System.out.println(data);
		if (data.equals("end")) {
			break;
		}
	}
//		 * 5���ͷ���Դ
		server.close();
	}

}
