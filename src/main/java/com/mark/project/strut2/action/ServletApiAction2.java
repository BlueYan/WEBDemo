package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Administrator on 2017/7/3.
 * 通过ServletActionContext工具类来访问
 * ServletActionContext.getRequest()
 * ServletActionContext.getResponse()
 * ServletActionContext.getServletContext()
 * 一般这种方法比较好用
 */
public class ServletApiAction2 extends ActionSupport {


	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().setAttribute("name", "ServletApiAction2");
		return SUCCESS;
	}
}
