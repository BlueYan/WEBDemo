package com.mark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/9.
 */
@WebServlet("/cookie/second")
public class SecondServlet extends HttpServlet {


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收cookie
		Cookie[] cookies = req.getCookies();
		for( Cookie c : cookies ) {
			//获取资源
			String username = c.getName();
			if ( "username".equals(username) ) {
				String value = c.getValue();
				System.out.println("value = " + value);
			}
		}
	}
}
