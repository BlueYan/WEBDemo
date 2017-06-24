package com.mark.project.login_shopcart_checkcode.web.servlet;

import com.mark.project.login_shopcart_checkcode.dao.IUserDao;
import com.mark.project.login_shopcart_checkcode.dao.impl.UserDaoImpl;
import com.mark.project.login_shopcart_checkcode.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/24.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


	private IUserDao userDao = null;

	@Override
	public void init() throws ServletException {
		if ( userDao == null ) {
			userDao = new UserDaoImpl();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = userDao.login(username, password);
		if ( user == null ) {
			//提示用户名或者密码错误
//			resp.setAttribute(); 并不存在setAttribute的方法
			req.setAttribute("errorMsg", "用户名或者密码错误");
			//返回登录页面 该方法会继续往下执行即使跳转了页面 如果要终止就要调用return
			req.getRequestDispatcher("/login_shopcart_checkcode/login.jsp").forward(req, resp);
		} else {
			req.getSession().setAttribute("USER_IN_SESSION", user); //放到作用域中
			resp.sendRedirect("/Student?cmd=list"); //重定向
		}
	}
}
