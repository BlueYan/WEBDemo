package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 * 使用对属性添加set方法.
 */
public class ParamAction2 extends ActionSupport {

	private String userName;

	private String password;

	@Override
	public String execute() throws Exception {
		System.out.println("username = " + userName + " password = " + password);
		return NONE;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
