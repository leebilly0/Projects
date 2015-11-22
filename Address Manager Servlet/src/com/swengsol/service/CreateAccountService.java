package com.swengsol.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.swengsol.model.Customer;
import com.swengsol.model.Account;
import com.swengsol.model.Username;

public class CreateAccountService
{
	public CreateAccountService()
	{
		
	}
	
	public static void persistAccount(Account acct) {
		Customer customer = acct.getCustomer();
		Username username = acct.getUsername();
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try { 
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String customerInsert = 
			"INSERT INTO CUSTOMER (first_name, last_name, street, city, state, zipcode, phone_number, email, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String usernameInsert = 
			"INSERT INTO Username (username, password) VALUES (?, ?)";
			
			ps = conn.prepareStatement(usernameInsert);
			ps.setString(1, username.getUsername());
			ps.setString(2, username.getPassword());
			ps.executeUpdate();
			stmt = conn.createStatement();
			rs  = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if (rs.next()) {
				int i = rs.getInt(1);
				ps = conn.prepareStatement(customerInsert);
				ps.setString(1, customer.getFname());
				ps.setString(2, customer.getLname());
				ps.setString(3, customer.getStreet());
				ps.setString(4, customer.getCity());
				ps.setString(5, customer.getState());
				ps.setString(6, customer.getZipcode());
				ps.setString(7, customer.getPhoneNumber());
				ps.setString(8, customer.getEmail());
				ps.setInt(9, i);
				ps.executeUpdate();
			} else {
				throw new IllegalStateException("Unable to retrieve last insert id");
			}
			conn.commit();
		} catch (Exception e) {
			if (null != conn) {
				try {
					conn.rollback();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (null != ps) ps.close();
				if (null != conn) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
