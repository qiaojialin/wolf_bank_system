package Teller.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import bean.User;
import Teller.dbc.DbConnection;

public class fun4_RegisterDao {
	private Connection conn = null;

	public int register(User user) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		int n = 0;
		try {
			cs = conn.prepareCall("{call sp_register(?,?,?,?,?,?,?,?)}");
			cs.setString(1, user.getUserid());
			cs.setString(2, user.getName());
			cs.setString(3, user.getSex());
			cs.setString(4, user.getAge());
			cs.setString(5, user.getPhone());
			cs.setString(6, user.getPassword());
			cs.setString(7, user.getRoleid());
			cs.setString(8, user.getIdentity());
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
