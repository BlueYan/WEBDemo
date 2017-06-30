package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.network.SocketClient;
import com.mark.project.mahjong.proto.ProtoLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/30.
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SocketClient client = new SocketClient();
		client.onConnection();
		ProtoLogin.LoginRequest.Builder builder = ProtoLogin.LoginRequest.newBuilder();
		builder.setOpenId("Mark8");
		builder.setNickname("kobe bryant");
		builder.setUnionId("asdasd");
		builder.setSex(true);
		builder.setImage("http://www.google.com");
		builder.setProvince("广东");
		builder.setCity("深圳");
		ProtoLogin.LoginRequest loginRequest = builder.build();
		byte[] requestArray = loginRequest.toByteArray();
		client.onSendMessage(105, 0, requestArray);
		byte[] data = client.onGetMessage();
		ProtoLogin.LoginResponse loginResponse = ProtoLogin.LoginResponse.parseFrom(data);
		req.getSession().setAttribute("client", client);
		req.getSession().setAttribute("loginResponse", loginResponse);

	}
}
