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

public class fun8_CheckAccountDao {
	private Connection conn = null;

	public String checkaccount(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		
		String ensemble2esb=null;
		
		try{
		//CORE收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/v1");
		String accountid=node1.getText();
		System.out.println("accountid:"+accountid);
		
		String a1,a2,a3;
		
		try {
			cs = conn.prepareCall("{call sp_checkaccount(?,?,?,?)}");
			cs.setString(1, accountid);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.DATE);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();

			a1=cs.getDate(2)+"";
			a2=cs.getString(3);
			a3=cs.getFloat(4)+"";
			//发送消息前组织报文				
			
			Element element = (Element) doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_OUT");//设置父节点	
			Element el = element.addElement("a1");
			el.setText(a1);
			el = element.addElement("a2");
			el.setText(a2);
			el = element.addElement("a3");
			el.setText(a3);
							
			Element root = doc1.getRootElement();
			ensemble2esb = root.asXML();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
		
		return ensemble2esb;
	}
}
