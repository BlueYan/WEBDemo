package com.mark.project.login_shopcart_checkcode.web.servlet;

import com.mark.project.login_shopcart_checkcode.dao.IUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/24.
 * 安全退出操作
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("/login_shopcart_checkcode/login.jsp"); //重定向到登录页面

	}
}
