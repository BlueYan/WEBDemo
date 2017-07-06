package com.mark.project.mahjong.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/4.
 * 客户端管理类
 * 为每一个登录的用户管理对应的服务器连接
 */
public class SocketClientManager {

	private SocketClientManager() {}

	private static Map<String, SocketClient> map = new HashMap<String, SocketClient>();

	public static void add(String uuid, SocketClient client) {
		if ( map != null ) {
			map.put(uuid, client);
		}
	}

	public static SocketClient get(String uuid) {
		if ( map != null ) {
			return map.get(uuid);
		}
		return null;
	}

	public static void remove(String uuid) {
		if ( map != null ) {
			SocketClient client = get(uuid);
			client.onDisconnection(); //关闭连接
			map.remove(uuid);
		}
	}

}
