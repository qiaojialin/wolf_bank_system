package Teller.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import bean.Login;
import Teller.dbc.DbConnection;

public class fun0_3_LoginHistoryDao {
	private Connection conn = null;

	public int loginHistory(Login login) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = 0;
		try {
			cs = conn.prepareCall("{call sp_loginhistory(?,?,?)}");
			cs.setString(1, login.getUserId());
			cs.setString(2, login.getLoginIp());
			cs.setString(3, login.getLoginTime());
			cs.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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
