package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.HttpParameters;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/6.
 * 获取请求参数方式1:
 * 使用request获取
 * 缺点:
 * 极其麻烦
 */
public class ParamAction  extends ActionSupport {

	private String userName;

	private String password;

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		Map<String, String[]> maps = ctx.getParameters().toMap();
		String[] usernames = maps.get("username");
		System.out.println("username = " + usernames[0]);
		String[] passwords = maps.get("password");
		System.out.println("password = " + passwords[0]);
		return NONE;
	}
}
