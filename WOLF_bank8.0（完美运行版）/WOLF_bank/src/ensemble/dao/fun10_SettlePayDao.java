package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import ensemble.dbc.DbConnection;

public class fun10_SettlePayDao {
	private Connection conn = null;

	public int settlepay(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 


		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/client");
		String v3=node3.getText();
	
		Node node4 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/acct");
		String v4=node4.getText();
	
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/payamt");
		String v5=node5.getText();
		int vv5=Integer.parseInt(v5);
		
		Node node6 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/baseacctno");
		String v6=node6.getText();
	
		
		try {
			cs = conn.prepareCall("{call sp_settlepay(?,?,?,?)}");
			cs.setString(1, v3);
			cs.setString(2, v4);
			cs.setInt(3, vv5);
			cs.setString(4, v6);
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
