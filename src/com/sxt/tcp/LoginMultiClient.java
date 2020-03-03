package com.sxt.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * ģ���¼ ����
 * �����ͻ���
 * 1���������ӣ� ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿�
 * 2���������������������
 * 3���ͷ���Դ
 */

public class LoginMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---Client----");
		// 1���������ӣ� ʹ��Socket�����ͻ���+�������ĵ�ַ�Ͷ˿�
		Socket client = new Socket("localhost",8888);
		// 2���������������������  ���������Ӧ
		new Send(client).send();
		new Receive(client).receive();
		client.close();	
	}
	
//����
	static class Send{
		private Socket client;
		private DataOutputStream dos;
		private BufferedReader console;
		private String msg;
		public Send(Socket client) {
			this.client=client;
			console= new BufferedReader(new InputStreamReader(System.in));
			this.msg = init();
			try {
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private String init(){
			try {
				System.out.print("�������û���:");
				String uname = console.readLine();
				System.out.print("����������:");
				String upwd = console.readLine();
				return "uname="+uname+"&"+"upwd="+upwd;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
			}
		
		public void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
//����
    static class Receive{
    	private Socket client;
    	private DataInputStream dis;
    	public Receive(Socket client){
    		this.client=client;
    		try {
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	public void receive() {
    		String result;
			try {
				result = dis.readUTF();
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    	}
		
	}
}
