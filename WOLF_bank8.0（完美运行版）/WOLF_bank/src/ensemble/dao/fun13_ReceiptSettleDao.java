package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun13_ReceiptSettleDao {
	private Connection conn = null;

	public int receiptsettle(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v1");
		String v1=node1.getText();
		System.out.println(v1);

		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v2");
		String v3=node3.getText();
		System.out.println(v3);
		Node node4 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v3");
		String v4=node4.getText();
		System.out.println(v4);
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v4");
		String v5=node5.getText();
		System.out.println(v5);
		
		int vv5=Integer.parseInt(v5);
		
		try {
			cs = conn.prepareCall("{call sp_receiptsettle(?,?,?,?)}");
			cs.setString(1, v1);
			cs.setString(2, v3);
			cs.setString(3, v4);
			cs.setInt(4, vv5);
			cs.execute();
			n = 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return n;
		} finally {
			try {
				cs.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	} catch (DocumentException e) {
		e.printStackTrace();
	}
		
		
		
		return 0;
	}
}
