package Esb.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import Esb.dbc.DbConnection;

public class ServerCheckDao {
	private Connection conn = null;

	public String getCheck(String teller_request) {
		this.conn = DbConnection.getConn();
		CallableStatement cs = null;
		String n = null;
		try {
			cs = conn.prepareCall("{call sp_check_server(?,?)}");
			cs.setString(1, teller_request);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			n = cs.getString(2);
			System.out.println("sp_check_server·µ»Ø£º"+n);
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
