package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.domain.RoomInfo;
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
 */
@WebServlet("/mahjong/get_room_info")
public class GetRoomInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String roomId = req.getParameter("roomId");
		System.out.println("roomId = " + roomId);
		if ( CommonUtil.isNotEmpty(roomId) ) {
			IRoomService roomService = new RoomServiceImpl();
			ProtoLogin.UserInfo user = (ProtoLogin.UserInfo) req.getSession().getAttribute("USER_IN_SESSION");
			System.out.println(user);
			ProtoGame.RoomInfoResp roomInfoResp = roomService.onGetRoomInfo(user.getUuid(), roomId);
			if ( roomInfoResp.getCode() == 0 ) {
				ProtoGame.PRoomInfo roomInfo = roomInfoResp.getRoomInfo();
				String playMethod = CommonUtil.unicode2String(roomInfo.getPlayMethod());
				String roomOwner  = CommonUtil.unicode2String(roomInfo.getRoomOwner());
				Integer round = roomInfo.getRound();
				Integer diamond = roomInfo.getDiamond();
				String extend = CommonUtil.unicode2String(roomInfo.getExtend());
				String awardHorse = CommonUtil.unicode2String(roomInfo.getAwardHorse());
				String settlement = CommonUtil.unicode2String(roomInfo.getSettlement());
				RoomInfo info = new RoomInfo(playMethod, roomOwner, round, diamond, extend, awardHorse, settlement);
				System.out.println(info);
				req.getSession().setAttribute("ROOMINFO_IN_SESSION", info);
				req.getRequestDispatcher("/mahjong/main.jsp").forward(req, resp);
			}
		}

	}
}
