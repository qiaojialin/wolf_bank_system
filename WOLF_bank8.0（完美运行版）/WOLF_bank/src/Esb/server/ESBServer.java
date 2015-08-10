package Esb.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ESBServer {
	
	private ServerSocket ss=null;
	private int port=8888;
	public ESBServer() throws IOException{
		//启动服务器，并指定端口号
		ss=new ServerSocket(port);
		System.out.println("ESB端服务器已经成功的启动！");
	}
	public void Communite(){
		try {
			//监听客户端的连接请求并接受，如果没有将一直等待下去
			while(true){
				Socket s=ss.accept();
				System.out.println("ESB端已经成功的接收到一个客户端的连接请求！");
				//将接收到的客户端连接交给一个线程处理
				new Thread(new ESBListener(s)).start();
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) throws IOException{
		//启动服务
		new ESBServer().Communite();
	}
}
