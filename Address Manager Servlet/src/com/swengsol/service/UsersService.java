package com.swengsol.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.swengsol.model.Account;
import com.swengsol.model.Customer;
import com.swengsol.model.Contact;
import com.swengsol.model.Username;

public class UsersService
{
	public static List<Username> getAllUsernames() {
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Username> userList = new ArrayList<Username>();
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			String selectAll = "SELECT * FROM ACCOUNT";
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				Username user = new Username();
				user.setUsername(rs.getString("Username"));
				userList.add(user);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ps) ps.close();
				if (null != conn) conn.close();
				if (null != connDS) connDS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return userList;		
	}
}
