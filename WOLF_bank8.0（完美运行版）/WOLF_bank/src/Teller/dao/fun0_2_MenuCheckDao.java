package Teller.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import Teller.dbc.DbConnection;

public class fun0_2_MenuCheckDao {
	private Connection conn = null;

	public List findMenu(User user) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		ResultSet rs = null;
		List menulist = new ArrayList();
		try {
			cs = conn.prepareCall("{call sp_menucheck(?,?)}");
			cs.setString(1, user.getUserid());
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(2);
			if (rs != null) {
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenuName(rs.getString(1));
					menu.setMenuUrl(rs.getString(2));
					menulist.add(menu);
				}
			}else{
				return null;
			}

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
		return menulist;

	}
}
