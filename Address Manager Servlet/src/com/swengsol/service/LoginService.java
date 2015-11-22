package com.swengsol.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.swengsol.model.Account;
import com.swengsol.model.Customer;
import com.swengsol.model.Username;

public class LoginService
{
	public boolean checkLogin(HttpServletRequest request)
	{
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			String selectAll = "SELECT * FROM USERNAME";
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				if (request.getParameter("username").equals(rs.getString("username")) && request.getParameter("password").equals(rs.getString("password")))
				{
					return true;
				}
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
		
		return false;
	}
}
