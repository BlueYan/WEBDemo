package com.mark.project.springMVCDemo;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mark_Yan on 2017/8/17.
 * 定义一个controller的实现类
 */
public class HelloWorldController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		System.out.println("handleRequest");
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "hello world");
		mv.setViewName("/index.jsp");
		return mv;
	}
}
