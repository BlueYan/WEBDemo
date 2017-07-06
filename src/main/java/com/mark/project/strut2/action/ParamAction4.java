package com.mark.project.strut2.action;

import com.mark.project.strut2.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/7/6.
 * 实现ModelDriven接口
 * 指定封装参数的model
 */
public class ParamAction4 extends ActionSupport implements ModelDriven<User> {

	private User user = new User();

	@Override
	public String execute() throws Exception {
		System.out.println("username = " + user.getUsername() + " password = " + user.getPassword());
		return NONE;
	}

	public User getModel() {
		return user;
	}
}
