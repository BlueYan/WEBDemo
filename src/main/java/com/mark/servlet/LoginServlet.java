package com.mark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/6/8.
 */
@WebServlet("/cookie/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if ( "admin".equals(username) && "123".equals(pwd) ) {
			Cookie cookie = new Cookie("username", username);
			resp.addCookie(cookie);
			System.out.println("OK");
			out.println("欢迎[" + username + "]登录");
			req.getRequestDispatcher("/cookie/second").forward(req, resp);
		}

	}
}
