package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.proto.ProtoGame;
import com.mark.project.mahjong.proto.ProtoLogin;
import com.mark.project.mahjong.service.IRoomService;
import com.mark.project.mahjong.service.impl.RoomServiceImpl;
import com.mark.project.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/5.
 * 解散房间
 */
@WebServlet("/mahjong/dismiss_room")
public class DismissRoomServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String param = req.getParameter("isDismiss");
		IRoomService service = new RoomServiceImpl();
		ProtoLogin.UserInfo user = (ProtoLogin.UserInfo) req.getSession().getAttribute("USER_IN_SESSION");
		System.out.println(user);
		ProtoGame.DismissRoomResp dismissRoomResp = null;
		if ( CommonUtil.isNotEmpty(param) ) {
			//0不同意 1同意
			if ( "0".equals(param) ) {
				dismissRoomResp = service.onDismissRoom(user.getUuid(), 0);
			} else {
				dismissRoomResp = service.onDismissRoom(user.getUuid(), 1);
			}
		} else {
			//申请解散
			dismissRoomResp = service.onDismissRoom(user.getUuid(), null);
		}
		if ( dismissRoomResp.getCode() == 0 ) {
			req.getSession().removeAttribute("ROOMID_IN_SESSION"); //删除房间号
		} else {
			req.setAttribute("errorMsg", dismissRoomResp.getCode());
		}
		req.getRequestDispatcher("/mahjong/main.jsp").forward(req, resp);
	}
}
