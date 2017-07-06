package com.mark.project.mahjong.service;

import com.mark.project.mahjong.param.CreateRoomParam;
import com.mark.project.mahjong.proto.ProtoGame;

/**
 * Created by Administrator on 2017/7/4.
 * 房间的业务逻辑处理
 */
public interface IRoomService {

	/**
	 * 创建房间
	 * @param roomParam 房间请求参数
	 * @return 创建房间响应体
	 */
	ProtoGame.CreateRoomResp onCreateRoom(CreateRoomParam roomParam);

	/**
	 * 获取房间资料
	 * @param roomId 房间号
	 * @return 房间资料响应体
	 */
	ProtoGame.RoomInfoResp onGetRoomInfo(String uuid, String roomId);

	/**
	 * 解散房阿健
	 * @param flag null表示申请 true表示同意解散 false表示拒绝
	 * @return 解散房间响应体
	 */
	ProtoGame.DismissRoomResp onDismissRoom(String uuid, Integer flag);
}
