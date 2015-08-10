package Teller.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import bean.User;
import Teller.dbc.DbConnection;

public class fun0_1_LoginCheckDao {
	private Connection conn = null;

	public int getCheck(User user) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = -1;
		try {
			cs = conn.prepareCall("{call sp_usercheck(?,?,?)}");
			cs.setString(1, user.getUserid());
			cs.setString(2, user.getPassword());
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			n = cs.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return n;
		} finally {
			try {
				cs.close();
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return n;
	}
}
