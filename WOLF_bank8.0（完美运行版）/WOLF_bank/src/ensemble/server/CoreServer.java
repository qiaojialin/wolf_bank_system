package ensemble.server;

import Esb.server.JMSToolsOperate;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;




public class CoreServer extends Thread implements
MessageListener, ExceptionListener {
// ConnectionFactory ：连接工厂，JMS 用它创建连接
ConnectionFactory connectionFactory;
// Connection ：JMS 客户端到JMS Provider 的连接
Connection connection = null;
// Session： 一个发送或接收消息的线程
Session session;
// Destination ：消息的目的地
Destination destination;
//消息接收者
MessageConsumer consumer;

public CoreServer() {
String url = "tcp://localhost:61616";
// 在ActiveMq的console配置的queue的名字
String queue ="E2C";
connectionFactory = new ActiveMQConnectionFactory(
		ActiveMQConnection.DEFAULT_USER,
		ActiveMQConnection.DEFAULT_PASSWORD, url);
// 构造从工厂得到连接对象
try {
	connection = connectionFactory.createConnection();
	connection.setExceptionListener(this);// 异常处理
	connection.start();// 连接启动
	//如果为true，则队列里面的消息没有被取走，继续存在
	session = connection.createSession(false,
			Session.AUTO_ACKNOWLEDGE);
	// 获取session， 
	destination = session.createQueue(queue);
	consumer = session.createConsumer(destination);
} catch (JMSException e) {
	System.err.println("Create fail!");
	e.printStackTrace();
}
}

public void run() {
try {
	consumer.setMessageListener(this);
} catch (JMSException e) {
	System.err
			.println(" MessageListener failed...");
	e.printStackTrace();
}
}

public void onMessage(Message message) {
try {
	if (message instanceof TextMessage) {
		TextMessage txtMsg = (TextMessage) message;
		String msg = txtMsg.getText();
		System.out.println("CoreServer!!!!");
		System.out.println("msg:"+msg);
		String messageid = txtMsg.getStringProperty("MessageID");
	System.out.println("messageid:"+messageid);
		 JMSToolsOperate tools=new JMSToolsOperate();
		 new Thread(new CoreListener(msg,tools,messageid)).start();

	}
} catch (JMSException e) {
	System.err
			.println("The process of getting a message failed...");
	e.printStackTrace();
}
}
// 异步消息异常处理
public void onException(JMSException arg0) {
System.err.println("JMS异常！");
}
//测试程序
public static void main(String[] args) {
CoreServer jrl = new CoreServer();
jrl.start();
System.out.println("Core端已启动");
}
}



//public class CoreServer {
//	
//	public static void main(String[] args){
//		  System.out.println("CORE端已经启动！");
//		  JMSToolsOperate tools=new JMSToolsOperate();
//		  while(true){
//			//从消息队列中取出消息
//				 String message=tools.receiveMsg("E2C","null");
//				
//				 String a[] = message.split("!"); 
//				 new Thread(new CoreListener(a[0],tools,a[1])).start();
//		  }
			 
//	}
//
//}
