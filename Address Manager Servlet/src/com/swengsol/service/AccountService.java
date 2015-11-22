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
import com.swengsol.model.Username;

public class AccountService
{
	public static List<Account> getAllAccounts() {
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Account> acctList = new ArrayList<Account>();
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			String selectAll = "SELECT * FROM CUSTOMER NATURAL JOIN USERNAME";
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				Customer cust = new Customer();
				Username user = new Username();
				Account acct = new Account(cust, user);
				(acct.getCustomer()).setFname(rs.getString("first_name"));
				acct.getCustomer().setLname(rs.getString("last_name"));
				acct.getUsername().setUsername(rs.getString("username"));
				acctList.add(acct);
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
		return acctList;		
	}
}
