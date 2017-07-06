package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/3.
 * 获取servlet api接口的方式
 * 实现三个作用域的感知接口
 * ServletRequestAware : 获取HttpServletRequest对象
 * ServletResponseAware : 获取HttpServletResponse对象
 * ServletSessionAware : 获取HttpSession对象
 *
 */
public class ServletApiAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest req = null;

	@Override
	public String execute() throws Exception {
		req.setAttribute("name", "Mark");
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.req = httpServletRequest;
	}
}
