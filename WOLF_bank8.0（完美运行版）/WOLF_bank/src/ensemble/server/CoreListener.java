package ensemble.server;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import ensemble.dao.*;
import Esb.server.JMSToolsOperate;


public class CoreListener implements Runnable{
	
	private String esb2core;
	private JMSToolsOperate tools;
	private String messageid;
	
	public CoreListener(String s,JMSToolsOperate tools,String messageid)
	{
		this.esb2core=s;
		this.tools=tools;
		this.messageid=messageid;
		
	}
	
	public void run() {
		 System.out.println("CORE端已经成功的从消息队列E2C中取出消息 "+esb2core);				 
			
		 System.out.println("messageid:"+messageid);
		 //对消息进行处理
		 try{
		 //ensemble收到消息后解析报文
		 Document doc1 = DocumentHelper.parseText(esb2core);
		 Node node1 = doc1.selectSingleNode("ROOT/HEAD/TRAN_CODE"); //获取叶子节点
		 System.out.println("esb端发来的服务请求为："+node1.getText());

		 String core2esb="";
		 
		 //客户注册
		 if(node1.getText().equals("clientregister"))
		 {
			 if(new fun6_ClientRegisterDao().clientregister(esb2core)==0)					 						 	
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
		 
		 //贷款开立
		 else if(node1.getText().equals("loancreate"))
		 {
			 String ensemble2esb=null;
			 if((ensemble2esb=new fun7_LoanCreateDao().loancreate(esb2core))!=null)
				core2esb=ensemble2esb;
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		//查询账户
		 else if(node1.getText().equals("checkaccount"))
		 {
			 String ensemble2esb=null;
			 if((ensemble2esb=new fun8_CheckAccountDao().checkaccount(esb2core))!=null)
				core2esb=ensemble2esb;
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		 //贷款发放
		 else if(node1.getText().equals("drawdown"))
		 {
			 if(new fun9_DrawdownDao().drawdown(esb2core)==0)
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		//贷款发放结算
		 else if(node1.getText().equals("settlepay"))
		 {
			 if(new fun10_SettlePayDao().settlepay(esb2core)==0)
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		 //通知单
		 else if(node1.getText().equals("invoice"))
		 {
			 String ensemble2esb=null;
			 if((ensemble2esb=new fun11_InvoiceDao().invoice(esb2core))!=null)
				core2esb=ensemble2esb;
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		 //贷款回收
		 else if(node1.getText().equals("loanreceipt"))
		 {
			 if(new fun12_LoanReceiptDao().loanreceipt(esb2core)==0)					 						 	
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
		 
		 	
		 
		 //贷款回收结算
		 else if(node1.getText().equals("receiptsettle"))
		 {
			
			 if(new fun13_ReceiptSettleDao().receiptsettle(esb2core)==0)
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
				 
		 
		 //账户开立
		 else if(node1.getText().equals("acctcreate"))
		 {

			 if((new fun14_AcctCreateDao().acctcreate(esb2core))==0)
				core2esb="success";
			 else						 	
				core2esb="failure"; 
		 }
		 
		 
		 
		tools.sendMsg(core2esb, "C2E",messageid);
		System.out.println("CORE端已经成功的向消息队列C2E中发送处理后的信息 "+core2esb);
		 
		
		}catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
