package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun12_LoanReceiptDao {
	private Connection conn = null;

	public int loanreceipt(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/loankey");
		String loankey=node1.getText();
		System.out.println("贷款号:"+loankey);
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/ddno");
		String ddno=node2.getText();
		System.out.println("发放号:"+ddno);
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/prioutstanding");
		String prioutstanding=node5.getText();
		System.out.println(" 本金:"+prioutstanding);
		Node node6 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/intoutstanding");
		String intoutstanding=node6.getText();
		System.out.println("利息:"+intoutstanding);
		String priname = "本金";
		String intname = "利息";
		
		try {
			cs = conn.prepareCall("{call sp_loanreceipt(?,?,?,?,?,?)}");
			cs.setInt(1, Integer.parseInt(loankey));
			cs.setInt(2, Integer.parseInt(ddno));
			cs.setFloat(3, Float.parseFloat(prioutstanding));
			cs.setFloat(4, Float.parseFloat(intoutstanding));
			cs.setString(5, priname);
			cs.setString(6, intname);
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
