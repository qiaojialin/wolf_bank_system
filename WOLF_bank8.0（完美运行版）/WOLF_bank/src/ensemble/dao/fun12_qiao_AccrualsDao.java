package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun12_qiao_AccrualsDao {
	private Connection conn = null;

	public int loancreate(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/'loan_no'");
		String v1=node1.getText();
	
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/'dd_no'");
		String v2=node2.getText();

		try {
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
