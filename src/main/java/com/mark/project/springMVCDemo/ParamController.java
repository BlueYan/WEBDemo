package com.mark.project.springMVCDemo;

import com.mark.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mark_Yan on 2017/8/17.
 */
@Controller
public class ParamController {

	//第一种传递参数方式 使用httprequestservlet来接收
	@RequestMapping("/param1")
	public ModelAndView param1(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		httpServletRequest.getParameter("name");
		return null;
	}

	//第二种 使用同名参数 也就是前台和后台参数名字一致
	@RequestMapping("/param2")
	public ModelAndView param2(String username)
			throws Exception {
		System.out.println(username);
		return null;
	}

	//第三种 后台和前台名字不一致 前台参数名字是name 添加一个注解requestParam就会根据前台传递的name找到对应的username
	@RequestMapping("/param3")
	public ModelAndView param3(@RequestParam("name") String username)
			throws Exception {
		System.out.println(username);
		return null;
	}

	//第四种 模型传参 前台只要传递模型中的属性名字就可以
	@RequestMapping("/param4")
	public ModelAndView param4(User user)
			throws Exception {
		System.out.println(user);
		return null;
	}

	//第五种 url地址传参
	//前台url地址： localhost/param5/3243252353252 id=3243252353252 贴上PathVariable指定id跟/param5/{id}的一致
	@RequestMapping("/param5/{id}")
	public ModelAndView param5(@PathVariable("id") Long id)
			throws Exception {
		System.out.println(id);
		return null;
	}

}
