package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2017/7/3.
 * 获取请求参数.
 * 原始:Map<String,String[]> params = request.getParameterMap();
 * 现在:Map<String,String[]> params = ctx.getParameters();
 * 2):操作request作用域:
 * 原始:request.setAttribute(String name, Object value);    Object val = request.getAttribtue(String name);
 * 现在:ctx.put(String key,Object value);                   Object val = ctx.get(String name);
 * 3):操作session作用域:
 * 原始:HttpSession  session =request.getSession();
 * 现在:Map<String,Object> session = ctx.getSession(); 接下来就是操作Map对象
 * session.put("XXXX_IN_SESSION", "XXX");
 * 4):操作servletContext(application)作用域:
 * 原始:ServletContext  servletContext = request.getServletContext();
 * 现在:Map<String,Object> app = ctx.getApplication();
 */
public class ServletApiAction3 extends ActionSupport {


	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		ctx.put("name", "ServletApiAction3"); //放到request请求作用域中
		return SUCCESS;
	}
}
