package com.swengsol.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.swengsol.model.Contact;
import com.swengsol.model.Customer;
import com.swengsol.model.Account;
import com.swengsol.model.Username;

public class ContactsService {
	
	//fix this later - billy
	public static void persistContact(Contact contact, String in_username) {
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try { 			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			int userid = getUserID(in_username);	//returns integer of primary key number for user (ID)
			
			String contactInsert = 
			"INSERT INTO CONTACT (first_name, last_name, street, city, state, zipcode, "
			+ "phone_number_one, phone_number_two, email_one, email_two, anniversary_one, "
			+ "anniversary_two, anniversary_three, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(contactInsert);
			ps.setString(1, contact.getFname());
			ps.setString(2, contact.getLname());
			ps.setString(3, contact.getStreet());
			ps.setString(4, contact.getCity());
			ps.setString(5, contact.getState());
			ps.setString(6, contact.getZipcode());
			ps.setString(7, contact.getPhoneNumberOne());
			ps.setString(8, contact.getPhoneNumberTwo());
			ps.setString(9, contact.getEmailOne());
			ps.setString(10, contact.getEmailTwo());
			ps.setString(11, contact.getAnniversaryOne());
			ps.setString(12, contact.getAnniversaryTwo());
			ps.setString(13, contact.getAnniversaryThree());
			ps.setInt(14, userid);
			ps.executeUpdate();
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
	
	public static int getUserID(String in_username)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		int temp = 0;
		try { 			
			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			//grabs id of username
			String selectAll = "SELECT id FROM username where username='" + in_username + "'";
			
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				
				temp = rs.getInt("id");
			}
			return temp;
			
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
		return 0;
	}
	
	
	public static List<Contact> getAllContacts(String in_user) {
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			String selectAll = "select * from contact left join username on contact.user_id = username.id where username = '" + in_user + "'";
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				Contact contact = new Contact(rs.getString("first_name"), rs.getString("last_name"), rs.getString("street"), rs.getString("city"), 
						rs.getString("state"), rs.getString("zipcode"), rs.getString("phone_number_one"), rs.getString("phone_number_two"), rs.getString("email_one"), 
						rs.getString("email_two"), rs.getString("anniversary_one"), rs.getString("anniversary_two"), rs.getString("anniversary_three"));
				
				contact.setId(rs.getInt(1));
				
				contactList.add(contact);
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
		return contactList;			
	}
	
	public Contact getContact(int in_id)
	{
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			String selectAll = "SELECT * FROM CONTACT where id=" + in_id;
			stmt = conn.createStatement();
			rs  = stmt.executeQuery(selectAll);
			while (rs.next()) {
				Contact contact = new Contact(rs.getString("first_name"), rs.getString("last_name"), rs.getString("street"), rs.getString("city"), 
						rs.getString("state"), rs.getString("zipcode"), rs.getString("phone_number_one"), rs.getString("phone_number_two"), rs.getString("email_one"), 
						rs.getString("email_two"), rs.getString("anniversary_one"), rs.getString("anniversary_two"), rs.getString("anniversary_three"));
				
				contact.setId(rs.getInt(1));
				return contact;
				
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
		return null;
	}
	
	public void updateContact(HttpServletRequest request, int in_id)
	{
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			conn.setAutoCommit(false);
			String contactInsert = "update CONTACT set first_name='" + request.getParameter("firstName") 
					+ "', last_name='"  + request.getParameter("lastName")
					+ "', street='"  + request.getParameter("street")
					+ "', city='"  + request.getParameter("city")
					+ "', state='"  + request.getParameter("state")
					+ "', zipcode='"  + request.getParameter("zipcode")
					+ "', phone_number_one='"  + request.getParameter("phoneNumberOne")
					+ "', phone_number_two='"  + request.getParameter("phoneNumberTwo")
					+ "', email_one='"  + request.getParameter("emailOne")
					+ "', email_two='"  + request.getParameter("emailTwo")
					+ "', anniversary_one='"  + request.getParameter("anniversaryOne")
					+ "', anniversary_two='"  + request.getParameter("anniversaryTwo")
					+ "', anniversary_three='"  + request.getParameter("anniversaryThree")
					+ "' where id=" + in_id;
			
			ps = conn.prepareStatement(contactInsert);
			ps.executeUpdate();
			conn.commit();
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
	}
	
	public void deleteContact(int in_id)
	{
		Connection conn = null, connDS = null;
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ics425homework");
			conn = ds.getConnection();				
			conn.setAutoCommit(false);
			String contactInsert = "delete from contact where id=" + in_id;
			
			ps = conn.prepareStatement(contactInsert);
			ps.executeUpdate();
			conn.commit();
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
	}
}
