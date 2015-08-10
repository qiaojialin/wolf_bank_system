package Esb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import Esb.dao.ServerCheckDao;


public class ESBListener implements Runnable {

	private JMSToolsOperate tools=new JMSToolsOperate();
	private Socket s;
	
	public ESBListener(Socket s){
		this.s=s;
	}
	
	public void run() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
			
			//接收从TELLER端发来的消息
			String str;
			String teller2esb="";
			while ((str = br.readLine())!= null) // 判断最后一行不存在，为空结束循环
			{
				teller2esb+=str;
				if(str.equals("</ROOT>"))
					break;
			}
		
			System.out.println("ESB端已经成功的接收到TELLER端的请求消息 "+teller2esb);
						
			//ESB收到消息后解析报文
			Document doc1 = DocumentHelper.parseText(teller2esb);
			Node node1 = doc1.selectSingleNode("ROOT/HEAD/TRAN_CODE"); //获取叶子节点
			System.out.println("teller端发来的服务请求为："+node1.getText());
			
			String messageid = ""+Calendar.getInstance().getTimeInMillis()+ Thread.currentThread().getId();
			System.out.println("messageid:"+messageid);
			if(new ServerCheckDao().getCheck(node1.getText())!=null){
				//将消息放入消息队列E2C中
				tools.sendMsg(teller2esb, "E2C",messageid);
				System.out.println("ESB端已经成功的向消息队列E2C放入消息 "+teller2esb);	
			}			
			
			//从消息队列C2E中取出消息
			String result=tools.receiveMsg("C2E",messageid);
			System.out.println("ESB端已经成功的从消息队列C2E中取出响应消息 "+result);
								
			//将返回消息发往TELLER端
			pw.println(result);
			System.out.println("ESB端已经成功的向TELLER端发送响应消息 "+result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}

	}

}




