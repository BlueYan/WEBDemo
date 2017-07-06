package com.mark.project.mahjong.web.servlet;

import com.mark.project.mahjong.param.CreateRoomParam;
import com.mark.project.mahjong.proto.ProtoGame;
import com.mark.project.mahjong.proto.ProtoLogin;
import com.mark.project.mahjong.service.IRoomService;
import com.mark.project.mahjong.service.impl.RoomServiceImpl;
import com.mark.project.util.CommonUtil;
import freemarker.template.utility.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/4.
 * 创建房间
 */
@WebServlet("/mahjong/create_room")
public class CreateRoomServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String payMethod = req.getParameter("pay"); // 支付方式
		String round = req.getParameter("round"); //局数
		String peopleNum = req.getParameter("people"); //人数
		String[] methodsreq = req.getParameterValues("method"); //玩法
		String hu = req.getParameter("hu"); // 胡牌
		String[] multiples = req.getParameterValues("multiple"); // 倍数
		String[] settlements = req.getParameterValues("settlement"); // 结算
		String awardHorse = req.getParameter("awardHorse"); // 奖马
		String buyOrPublishHorse = req.getParameter("buyHorse"); // 买马 罚马
		String maxMultiple = req.getParameter("maxMultiple"); //封顶
		CreateRoomParam roomParam = new CreateRoomParam();
		ProtoLogin.UserInfo user = (ProtoLogin.UserInfo) req.getSession().getAttribute("USER_IN_SESSION");
		//System.out.println(user);
		roomParam.setUuId(user.getUuid()); //存储uuid
		roomParam.setRoomType(1); //设置房间类型
		roomParam.setRound(Integer.parseInt(round)); // 设置局数
		if ( "4".equals(round) ) {
			roomParam.setDiamond(2); //4局2个钻石
		} else if ( "16".equals(round) ) {
			roomParam.setDiamond(6); // 16局6个钻石
		} else {
			roomParam.setDiamond(2); //8局3个钻石
		}
		//设置胡牌类型
		if ( CommonUtil.isNotEmpty(hu) ) {
			if ( "chiHu".equals(hu) ) {
				roomParam.setChihu(true);
			} else if ( "ziMo".equals(hu) ) {
				roomParam.setZiMo(true);
			} else if ( "tenTimes".equals(hu) ) {
				roomParam.setTenTimeNoCal(true);
			}
		}
		//设置倍数
		for ( String multiple : multiples ) {
			if ( CommonUtil.isNotEmpty(multiple) ) {
				if ( "xiaoHu".equals(multiple) ) {
					roomParam.setXiaoHu(true);
				} else if ( "jiHuCannotChi".equals(multiple) ) {
					roomParam.setJiHuCannotChiHu(true);
				} else if ( "genZhuang".equals(multiple) ) {
					roomParam.setGenZhuang(true);
				}
			}
		}
		//结算
		for ( String settlement : settlements ) {
			if ( CommonUtil.isNotEmpty(settlement) ) {
				if ( "tieStillKong".equals(settlement) ) {
					roomParam.setTieStilKong(true);
				} else if ( "kongBaoPayAll".equals(settlement) ) {
					roomParam.setKongBaoPayAll(true);
				} else if ( "lianZhuang".equals(settlement) ) {
					roomParam.setLianZhuang(true);
				}
			}
		}

		// 封顶
		if ( CommonUtil.isNotEmpty(maxMultiple) ) {
			if ( "fiveMultiple".equals(maxMultiple) ) {
				roomParam.setMaxMultiple(5);
			} else if ( "tenMultiple".equals(maxMultiple) ) {
				roomParam.setMaxMultiple(10);
			}
		}

		//奖马
		if ( CommonUtil.isNotEmpty(awardHorse) ) {
			if ( "0".equals(awardHorse) ) {
				roomParam.setAwardHorse(0);
			} else if ( "2".equals(awardHorse) ) {
				roomParam.setAwardHorse(2);
			} else if ( "5".equals(awardHorse) ) {
				roomParam.setAwardHorse(5);
			} else if ( "8".equals(awardHorse) ) {
				roomParam.setAwardHorse(8);
			} else {
				roomParam.setAwardHorse(0);
				roomParam.setKongFollHorseNum(true);
			}
		}

		//买马和罚马
		if ( CommonUtil.isNotEmpty(buyOrPublishHorse) ) {
			if ( "0".equals(buyOrPublishHorse) ) {
				roomParam.setBuyHorse(0);
				roomParam.setPublishHorse(0);
			} else if ( "1".equals(buyOrPublishHorse) ) {
				roomParam.setBuyHorse(1);
				roomParam.setPublishHorse(0);
			} else if ( "2".equals(buyOrPublishHorse) ) {
				roomParam.setBuyHorse(2);
				roomParam.setPublishHorse(0);
			} else if ( "3".equals(awardHorse) ) {
				roomParam.setBuyHorse(0);
				roomParam.setPublishHorse(1);
			} else if ( "4".equals(awardHorse) ) {
				roomParam.setBuyHorse(0);
				roomParam.setPublishHorse(2);
			}
		}
//		System.out.println(roomParam);
		IRoomService roomService = new RoomServiceImpl();
		ProtoGame.CreateRoomResp roomResp = roomService.onCreateRoom(roomParam);
		if ( roomResp.getCode() == 0 ) {
			req.getSession().setAttribute("ROOMID_IN_SESSION", roomResp.getRoomAttr().getRoomId());  //保存roomid
			System.out.println(roomResp.getRoomAttr());
		} else {
			req.setAttribute("errorMsg", roomResp.getCode());
			System.out.println("code = " + roomResp.getCode());
		}
		req.getRequestDispatcher("/mahjong/main.jsp").forward(req, resp);
	}
}
