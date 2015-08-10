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

public class fun14_AcctCreateDao {
	private Connection conn = null;

	public int acctcreate(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/internalkey");
		String v1=node1.getText();
		System.out.println(v1);
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/ledgerbal");
		String v2=node2.getText();
		System.out.println(v2);
		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/clientno");
		String v3=node3.getText();
		System.out.println(v3);

		try {
			
			cs = conn.prepareCall("{call sp_clientcheck(?,?)}");
			cs.setString(1, v3);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			if(cs.getInt(2)==1)
			{
				cs = conn.prepareCall("{call sp_acctcreate(?,?,?)}");
				cs.setString(1, v1);
				cs.setString(2, v2);
				cs.setString(3, v3);
				cs.execute();
				n = 0;
			}
			else 
				n=-1;
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
		
		
		
		return n;
	}
}
