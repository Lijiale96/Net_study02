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
public class LoginMultiServer {
public static void main(String[] args) throws IOException {
	System.out.println("---Server----");
//	* 1��ָ���˿�ʹ��ServerSocket����������
	ServerSocket server = new ServerSocket(8888);
	boolean isRunning = true;
//	 * 2������ʽ�ȴ����� accept
	while (isRunning) {
		Socket client = server.accept();
		new Thread(new Channel(client)).start();
	}
	server.close();
}
//һ��channel�ʹ����¸��ͻ���
static class Channel implements Runnable{
	private Socket client;
	//������
	private DataInputStream dis;
	private DataOutputStream dos;
    public Channel(Socket client) {
    	this.client = client;
		try {
			dis = new DataInputStream(client.getInputStream());
			//���
			 dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			release();
		}
    }
    
  //��������
  	private String receive() {
  		String datas="";
  		try {
  			datas = dis.readUTF();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return datas;
  	}
  	//�ͷ���Դ
  	private void release() {
//		 * 4���ͷ���Դ
		try {
			if (null!=dos) {
				dos.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (null!=dis) {
				dis.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (null!=client) {
				client.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
    //��������
  	private void send(String msg) {
  		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("һ���ͻ��˽���������");
//		 * 3���������������������
	
		String uname ="";
		String upwd ="";
		
	//����
		String[] dataArray =receive().split("&");
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
		if (uname.equals("UJS")&&upwd.equals("Lijiale")) {//�ɹ�
			send("��¼�ɹ�����ӭ����");
		}else {//ʧ��
			send("��¼ʧ�ܣ��������");
		}
		release();
	}
	
}
}
