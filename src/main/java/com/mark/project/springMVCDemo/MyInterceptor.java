package com.mark.project.springMVCDemo;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mark_Yan on 2017/8/18.
 *
 * 自定义拦截器
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	//在控制器方法之前之前调用,如果返回false表示拦截请求,如果为true，放行请求
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		return false;
	}

	//在控制器执行完方法后视图结合之前调用
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
						   Object o, ModelAndView modelAndView) throws Exception {

	}

	//在控制器执行完方法后视图结合完后调用
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
								Object o, Exception e) throws Exception {

	}
}
