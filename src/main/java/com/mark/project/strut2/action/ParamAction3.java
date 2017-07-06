package com.mark.project.strut2.action;

import com.mark.project.strut2.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/7/6.
 * 将请求参数全部封装到独立的model类中
 * 并且为这个model类对象提供get和set方法
 * 前端页面提交的表单要用user.username这种ognl表达式
 */
public class ParamAction3 extends ActionSupport {

	@Getter
	@Setter
	private User user;

	@Override
	public String execute() throws Exception {
		System.out.println("username = " + user.getUsername() + " password = " + user.getPassword());
		return NONE;
	}

}
