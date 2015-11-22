package com.swengsol.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swengsol.model.Contact;
import com.swengsol.model.Customer;
import com.swengsol.model.Account;
import com.swengsol.model.Username;
import com.swengsol.service.CreateAccountService;

/**
 * Servlet implementation class CreateContactServlet
 */
@WebServlet("/EditContact")
public class EditContactServlet extends HttpServlet
{
	 private static final long serialVersionUID = 1L;
     
	 public EditContactServlet() {
	        super();
	    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			//Controller is tightly coupled to model and view
		 HttpSession session = request.getSession();
			
			//Step 1. Retrieve request parameters
		 if (session.getAttribute("form") != null)
		 {
			 String anniversaryOne = request.getParameter("anniversaryOne");
			 String anniversaryTwo = request.getParameter("anniversaryTwo");
			 String anniversaryThree = request.getParameter("anniversaryThree");
			 
			 Contact temp = (Contact)session.getAttribute("form2");
			 
			 temp.setAnniversaryOne(anniversaryOne);
			 temp.setAnniversaryTwo(anniversaryTwo);
			 temp.setAnniversaryThree(anniversaryThree);
			 
		//Step 2a. Setup model for use by JSP
		 request.setAttribute("contact",  temp);
		 }
		//Step 3. Declarative lookup. Determine the View and Dispatch to it.
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UpdateContact.jsp");
		 dispatcher.forward(request,  response);
		 //}
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		        throws ServletException, IOException {
				doGet(request, response);
			}
		 
}