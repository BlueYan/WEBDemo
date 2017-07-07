package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;

/**
 * Created by Administrator on 2017/7/7.
 * 登录Action
 */
public class LoginAction extends ActionSupport{

	@Setter
	private String username;
	@Setter
	private String password;

	@Override
	public String execute() throws Exception {
		//进行登录判断
		if ( "admin".equals(username) && "admin".equals(password) ) {
			//登录成功
			ActionContext.getContext().getSession().put("USER_IN_SESSION", username); //保存用户信息
			return SUCCESS;
		}

		return "LOGIN_FAIL"; //登录失败
	}
}
