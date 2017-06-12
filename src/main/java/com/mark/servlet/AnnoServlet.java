package com.mark.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/8.
 */
@WebServlet(value = "/anno", loadOnStartup = 0, initParams = {
		@WebInitParam(name = "name", value = "Mark"),
		@WebInitParam(name = "age", value = "12")
})
public class AnnoServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init servlet");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = getInitParameter("name");
		String age = getInitParameter("age");
		System.out.println("name = " + name + " age = " + age);
	}
}
