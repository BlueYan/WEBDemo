package com.mark.project.mahjong.service.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.mark.project.mahjong.network.SocketClient;
import com.mark.project.mahjong.network.SocketClientManager;
import com.mark.project.mahjong.proto.ProtoLogin;
import com.mark.project.mahjong.service.ILoginService;

/**
 * Created by Administrator on 2017/7/4.
 */
public class LoginServiceImpl implements ILoginService {

	/**
	 * 登录
	 *
	 * @param openId
	 * @param cmd    登录命令105
	 * @return 登录响应体
	 */
	public ProtoLogin.LoginResponse onLogin(String openId, String cmd) {
		SocketClient client = new SocketClient();
		ProtoLogin.LoginRequest.Builder builder = ProtoLogin.LoginRequest.newBuilder();
		builder.setOpenId(openId);
		builder.setNickname("kobe bryant");
		builder.setUnionId("asdasd");
		builder.setSex(true);
		builder.setImage("http://www.google.com");
		builder.setProvince("广东");
		builder.setCity("深圳");
		ProtoLogin.LoginRequest loginRequest = builder.build();
		byte[] requestArray = loginRequest.toByteArray();
		client.onSendMessage(Integer.parseInt(cmd), 0, requestArray); // 发送请求体 请求服务器
		byte[] data = client.onGetMessage();
		ProtoLogin.LoginResponse loginResponse = null;
		try {
			loginResponse = ProtoLogin.LoginResponse.parseFrom(data); //解析数据
			SocketClientManager.add(loginResponse.getUserInfo().getUuid(), client);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return loginResponse;
	}
}
