package com.swengsol.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.swengsol.model.Customer;
import com.swengsol.model.Account;
import com.swengsol.model.Username;
import com.swengsol.service.CreateAccountService;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccount")
public class CreateAccountServlet extends HttpServlet
{
	 private static final long serialVersionUID = 1L;
     
	 public CreateAccountServlet() {
	        super();
	    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
			//Controller is tightly coupled to model and view
		 HttpSession session = request.getSession();
			
			//Step 1. Retrieve request parameters
		 if (session.getAttribute("form") != null)
		 {
			 String user = request.getParameter("username");
			 String pass = request.getParameter("password");
		 
		//Step 1a.Declarative lookup. Perform validation
		
			 
		//Step 1b. Set up the model components
		 Username username = new Username(user, pass);
		 
		//Step 2. Declarative lookup. Invoke request processing component to Deal with the Model
		 Account account = (Account)session.getAttribute("form");
		 account.setUsername(username);
		//Step 2a. Setup model for use by JSP
		 request.setAttribute("account",  account);
		 }
		//Step 3. Declarative lookup. Determine the View and Dispatch to it.
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AccountCreated.jsp");
		 dispatcher.forward(request,  response);
		 //}
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		        throws ServletException, IOException {
				doGet(request, response);
			}
		 
}
