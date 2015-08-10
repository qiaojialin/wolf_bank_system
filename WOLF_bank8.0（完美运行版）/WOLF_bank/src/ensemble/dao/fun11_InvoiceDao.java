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

public class fun11_InvoiceDao {
	private Connection conn = null;

	public String invoice(String esb2ensemble) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		
		String ensemble2esb=null;
		
		try{
		//CORE收到消息后解析报文
		Document doc1 = DocumentHelper.parseText(esb2ensemble);
		//获取叶子节点b 
		Node node1 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/ddno");
		String ddkey=node1.getText();
		System.out.println("ddno:"+ddkey);
		
		Node node2 = doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_IN/loankey");
		String loankey=node2.getText();
		System.out.println("loankey:"+loankey);
		
		String a1,a2,a3,a4,a5;
		
		try {
			cs = conn.prepareCall("{call sp_invoice(?,?,?,?,?,?,?)}");
			cs.setInt(1, Integer.parseInt(ddkey));
			cs.setInt(2, Integer.parseInt(loankey));
			
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.DATE);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.DATE);
			cs.execute();
			a1 = cs.getFloat(3)+"";
			a2 = cs.getFloat(4)+"";;
			a3 = cs.getString(5);
			a4 = cs.getDate(6)+"";
			a5 = cs.getDate(7)+"";
			

			//发送消息前组织报文				
			
			Element element = (Element) doc1.selectSingleNode("ROOT/BODY/SVR_MESSAGE_OUT");//设置父节点	
			Element el = element.addElement("a1");
			el.setText(a1);
			el = element.addElement("a2");
			el.setText(a2);
			el = element.addElement("a3");
			el.setText(a3);
			el = element.addElement("a4");
			el.setText(a4);
			el = element.addElement("a5");
			el.setText(a5);
							
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
