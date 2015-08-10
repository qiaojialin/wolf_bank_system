package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun6_ClientRegisterDao {
	private Connection conn = null;

	public int clientregister(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//esb收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/clientid");
		String clientid=node1.getText();
		System.out.println("clientid:"+clientid);
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/clientname");
		String clientname=node2.getText();
		System.out.println("clientname:"+clientname);
		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/city");
		String city=node3.getText();
		System.out.println("city:"+city);
		Node node4 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/address");
		String address=node4.getText();
		System.out.println("address:"+address);
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/country");
		String country=node5.getText();
		System.out.println("country:"+country);
		Node node6 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/job");
		String job=node6.getText();
		System.out.println("job:"+job);
		
		try {
			cs = conn.prepareCall("{call sp_clientregister(?,?,?,?,?,?)}");
			cs.setString(1, clientid);
			cs.setString(2, clientname);
			cs.setString(3, city);
			cs.setString(4, address);
			cs.setString(5, country);
			cs.setString(6, job);
			cs.execute();
			n = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return n;
		} finally {
			try {
				cs.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		return 0;
	}
}
