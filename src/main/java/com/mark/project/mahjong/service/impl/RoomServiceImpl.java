package com.mark.project.mahjong.service.impl;

import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mark.project.mahjong.network.SocketClient;
import com.mark.project.mahjong.network.SocketClientManager;
import com.mark.project.mahjong.param.CreateRoomParam;
import com.mark.project.mahjong.proto.ProtoGame;
import com.mark.project.mahjong.service.IRoomService;

/**
 * Created by Administrator on 2017/7/4.
 */
public class RoomServiceImpl implements IRoomService {

	/**
	 * 创建房间
	 *
	 * @param roomParam 房间请求参数
	 * @return 创建房间响应体
	 */
	public ProtoGame.CreateRoomResp onCreateRoom(CreateRoomParam roomParam) {
		SocketClient client = SocketClientManager.get(roomParam.getUuId()); //取出对应的客户端
		ProtoGame.CreateRoomReq.Builder builder = ProtoGame.CreateRoomReq.newBuilder(); //实例一个builder对象
		builder.setRoomType(roomParam.getRoomType()); // 房间类型
		builder.setDiamondId(roomParam.getDiamond()); //钻石个数
		builder.setCanChiHu(roomParam.isChihu()); // 能否吃胡
		builder.setMustZimo(roomParam.isZiMo()); // 自摸
		builder.setTenTimesNoLose(roomParam.isTenTimeNoCal()); //10倍不计分
		builder.setGenZhuang(roomParam.isGenZhuang()); // 跟庄
		builder.setTieStillKong(roomParam.isTieStilKong()); //流局算杠
		builder.setKongBaoPayAll(roomParam.isKongBaoPayAll()); //吃杠杠爆全包
		builder.setLianZhuang(roomParam.isLianZhuang()); //连庄
		if( roomParam.getBuyHorse() != null ) {
			builder.setBuyHorseNum(roomParam.getBuyHorse());
		}
		if ( roomParam.getAwardHorse() != null ) {
			if ( roomParam.getBuyHorse() == 10 ) {
				builder.setKongFollowHorse(true); //马跟杠
			} else {
				builder.setAwardHorseNum(roomParam.getAwardHorse());
			}
		}
		if ( roomParam.getPublishHorse() != null ) {
			builder.setPunishHorseNum(roomParam.getPublishHorse());
		}
		if ( roomParam.getMaxMultiple() != null ) {
			builder.setMaxMultiple(roomParam.getMaxMultiple());
		}
		ProtoGame.CreateRoomReq createRoomReq = builder.build();
		byte[] reqArray = createRoomReq.toByteArray();
		client.onSendMessage(1010, 0, reqArray);
		byte[] data = client.onGetMessage();
		ProtoGame.CreateRoomResp resp = null;
		try {
			resp = ProtoGame.CreateRoomResp.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 获取房间资料
	 *
	 * @param roomId 房间号
	 * @return 房间资料响应体
	 */
	public ProtoGame.RoomInfoResp onGetRoomInfo(String uuid, String roomId) {
		SocketClient client = SocketClientManager.get(uuid);
		ProtoGame.RoomInfoReq.Builder builder = ProtoGame.RoomInfoReq.newBuilder();
		builder.setRoomId(Integer.parseInt(roomId));
		byte[] reqArray = builder.build().toByteArray();
		client.onSendMessage(1021, 0, reqArray);
		byte[] data = client.onGetMessage();
		ProtoGame.RoomInfoResp resp = null;
		try {
			resp = ProtoGame.RoomInfoResp.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 解散房阿健
	 *
	 * @param flag null表示申请 1表示同意解散 0表示拒绝
	 * @return 解散房间响应体
	 */
	public ProtoGame.DismissRoomResp onDismissRoom(String uuid, Integer flag) {
		SocketClient client = SocketClientManager.get(uuid);
		ProtoGame.DismissRoomReq.Builder builder = ProtoGame.DismissRoomReq.newBuilder();
		builder.setIsDismiss(0);
		Integer cmd = 1014;
		if ( flag != null ) {
			cmd = 1015;
			builder.setIsDismiss(flag);
		}
		byte[] reqArray = builder.build().toByteArray();
		client.onSendMessage(cmd, 0, reqArray);
		byte[] data = client.onGetMessage();
		ProtoGame.DismissRoomResp resp = null;
		try {
			resp = ProtoGame.DismissRoomResp.parseFrom(data);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
