package com.mark.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/2.
 */
public class HelloServlet implements Servlet {

	public void init(ServletConfig servletConfig) throws ServletException {

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		System.out.println("HelloServlet.service()");

	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
