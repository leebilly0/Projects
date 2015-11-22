package com.swengsol.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.swengsol.model.Username;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/DisplayContacts.jsp" })
public class LoginFilter implements Filter {
	FilterConfig fc;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        String message = "Unauthenticated user must start at the first page in the flow";
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        boolean authenticated = false;
        String forwardPage = "/Login.jsp";
        synchronized(session) {
            if (req.getRequestURI().contains("DisplayContacts.jsp")) {
                if (null != session.getAttribute("username")) {
                    message = "You must enter a username and password";
                    authenticated = ((Username)session.getAttribute("username")).validate();
                }                                          
            }
            if (!authenticated) {
                request.setAttribute("message", message);
                fc.getServletContext().getRequestDispatcher(forwardPage).
                  forward(request, response);                                    
            }
        }

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
