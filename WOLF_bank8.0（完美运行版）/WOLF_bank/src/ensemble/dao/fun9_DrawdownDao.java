package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun9_DrawdownDao {
	private Connection conn = null;

	public int drawdown(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v1");
		String v1=node1.getText();
	
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v2");
		String v2=node2.getText();

		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v3");
		String v3=node3.getText();
	
		Node node4 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v4");
		String v4=node4.getText();
	
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v5");
		String v5=node5.getText();
		
		int loankey=Integer.parseInt(v1);
		int ddkey=Integer.parseInt(v2);
		
		try {
			cs = conn.prepareCall("{call sp_drawdown(?,?,?,?,?)}");
			cs.setInt(1, loankey);
			cs.setInt(2, ddkey);
			cs.setString(3, v3);
			cs.setString(4, v4);
			cs.setString(5, v5);
			cs.execute();
			
			cs = conn.prepareCall("{call sp_accruals(?,?)}");
			cs.setString(1, v1);
			cs.setString(2, v2);
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
