package com.mark.project.strut2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by Administrator on 2017/7/7.
 * 自定义Interceptor拦截器
 * 方式一: 实现Interceptor接口
 * 方式二: 继承AbstractInterceptor
 */
public class CheckLoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		//逻辑操作
		Object user = actionInvocation.getInvocationContext().getSession().get("USER_IN_SESSION");
		if ( user == null ) {
			//表示没有登录. 返回登录页面
			return "LOGIN_FAIL";
		}
		return actionInvocation.invoke();  //放行 执行后面的代码操作.
	}
}
