package com.mark.project.springMVCDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mark_Yan on 2017/8/17.
 * 注解方式配置URL映射
 *
 */
@Controller //controller控制器注解
public class AnnocationController {

	//requestMapping表示前台过来的url映射然后与下面的方法绑定
	@RequestMapping("/method")
	public ModelAndView method(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		System.out.println("handleRequest");
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "hello world");
		mv.setViewName("/index.jsp");
		return mv;
	}

}
