package com.mark.project.mahjong.service;

import com.mark.project.mahjong.proto.ProtoLogin;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface ILoginService {

	/**
	 * 登录
	 * @param openId
	 * @param cmd 登录命令105
	 * @return 登录响应体
	 */
	ProtoLogin.LoginResponse onLogin(String openId, String cmd);

}
