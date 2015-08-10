package ensemble.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;


import ensemble.dbc.DbConnection;

public class fun7_LoanCreateDao {
	private Connection conn = null;

	public String loancreate(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		String ensemble2esb=null;
		
		try{
		//ESB收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
	
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v2");
		String v2=node2.getText();
		System.out.println("合同号："+v2);
		Node node3 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v3");
		String v3=node3.getText();
		System.out.println("客户号"+v3);
		Node node4 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v4");
		String v4=node4.getText();
		System.out.println("客户姓名"+v4);
		Node node5 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v5");
		String v5=node5.getText();
		System.out.println("贷款金额"+v5);
	
		int loankey;
		String loankey1;
		try {
			cs = conn.prepareCall("{call sp_loancreate(?,?,?,?,?)}");
			
			cs.setString(1, v2);
			cs.setString(2, v3);
			cs.setString(3, v4);
			cs.setFloat(4, Integer.parseInt(v5));
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			loankey=cs.getInt(5);
			loankey1=loankey+"";

			
			Element element = (Element) doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_OUT");//设置父节点	
			Element el = element.addElement("loankey");
			el.setText(loankey1);
			Element root = doc1.getRootElement();
			ensemble2esb = root.asXML();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
		
		
		
		return ensemble2esb;
	}
}
