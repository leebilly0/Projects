package com.swengsol.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swengsol.model.Username;
import com.swengsol.service.LoginService;

/**
 * Servlet implementation class NameServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String nextPage = "/DisplayContacts.jsp";
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
        String forwardedPage = nextPage;
        synchronized(request.getSession() ) {
            Username username = (Username) request.getSession().getAttribute("username");
            if (null == username) {
            	username = new Username();
                request.getSession().setAttribute("username", username);
            }
            
            if (request.getParameter("username").isEmpty() 
                    || (request.getParameter("password").isEmpty())) {
                request.setAttribute("message", "Username and/or Password is incorrect");
                forwardedPage = "/Login.jsp";
            } else {
            	
            	if ( (new LoginService()).checkLogin(request) == false)
            	{
            		request.setAttribute("message", "Username and/or Password is incorrect");
                    forwardedPage = "/Login.jsp";
            	}
            	else
            	{
            		username.setUsername(request.getParameter("username"));
            		username.setPassword(request.getParameter("password"));
            	}
            }            
        }        
        getServletContext().getRequestDispatcher(forwardedPage).
          forward(request, response);
    }
}
