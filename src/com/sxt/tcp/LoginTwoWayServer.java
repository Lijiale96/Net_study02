package com.sxt.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ģ���¼ ����
 * ����������
 * 1��ָ���˿�ʹ��ServerSocket����������
 * 2������ʽ�ȴ����� accept
 * 3���������������������
 * 4���ͷ���Դ
 * 
 * 
 */
public class LoginTwoWayServer {
public static void main(String[] args) throws IOException {
	System.out.println("---Server----");
//	* 1��ָ���˿�ʹ��ServerSocket����������
	ServerSocket server = new ServerSocket(8888);
//	 * 2������ʽ�ȴ����� accept
	Socket client = server.accept();
	System.out.println("һ���ͻ��˽���������");
//	 * 3���������������������
	DataInputStream dis = new DataInputStream(client.getInputStream());
	String datas = dis.readUTF();
	String uname ="";
	String upwd ="";
	
//����
	String[] dataArray =datas.split("&");
	for (String info:dataArray) {
		String[] userInfo = info.split("=");
		System.out.println(userInfo[0]+"-->"+userInfo[1]);
		if (userInfo[0].equals("uname")) {
			System.out.println("����û���Ϊ:"+userInfo[1]);
			uname=userInfo[1];
		}
		else if(userInfo[0].equals("upwd")) {
			System.out.println("�������Ϊ:"+userInfo[1]);
			upwd =userInfo[1];
		}
	}
	//���
	DataOutputStream dos = new DataOutputStream(client.getOutputStream());
	//dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);
	if (uname.equals("UJS")&&upwd.equals("Lijiale")) {//�ɹ�
		dos.writeUTF("��¼�ɹ�����ӭ����");
	}else {//ʧ��
		dos.writeUTF("��¼ʧ�ܣ��������");
	}
	dos.flush();
//	 * 4���ͷ���Դ
	dis.close();
	client.close();
	
	server.close();
	
}
}