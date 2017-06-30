package com.mark.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/28.
 *
 * 过滤器
 *
 */
public class HelloFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		//进行各种业务逻辑处理
		System.out.println("hello filter");
		filterChain.doFilter(req, resp); //进行分发
	}

	public void destroy() {

	}
}
