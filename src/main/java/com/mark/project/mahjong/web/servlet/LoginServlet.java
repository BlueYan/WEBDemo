package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.proto.ProtoLogin;
import com.mark.project.mahjong.service.ILoginService;
import com.mark.project.mahjong.service.impl.LoginServiceImpl;
import com.mark.project.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4.
 * LoginServlet
 * 登录
 */
@WebServlet("/mahjong/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String openId = req.getParameter("openId");
		String cmd = req.getParameter("cmd");
		if ( CommonUtil.isNotEmpty(openId) && CommonUtil.isNotEmpty(cmd) ) {
			//操作
			ILoginService loginService = new LoginServiceImpl();
			ProtoLogin.LoginResponse loginResponse = loginService.onLogin(openId, cmd);
			if ( loginResponse.getCode() == 0 ) {
				//数据正确返回
				ProtoLogin.UserInfo user = loginResponse.getUserInfo();
				req.getSession().setAttribute("USER_IN_SESSION", user);
				req.getRequestDispatcher("/mahjong/main.jsp").forward(req, resp);
				return;
			}
		}
	}
}
