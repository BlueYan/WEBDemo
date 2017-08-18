package com.mark.project.springMVCDemo;

import com.mark.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mark_Yan on 2017/8/18.
 * 后台返回值给前台
 */
@Controller
public class DataController {

	/**
	 * 用ModelAndView返回
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/data1")
	public ModelAndView data1() throws Exception {
		System.out.println("data1");
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "测试");
		mv.setViewName("/WEB-INF/data2.jsp");
		return mv;
	}

	/**
	 * 返回一个bean对象
	 * 如果我们没有配置前缀和后缀信息的话，返回的路径默认就是/data2这样就造成一个请求的死循环
	 * 所以我们需要去配置前缀和后缀信息
	 	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 		<property name="prefix" value="/WEB-INF/"/>
	 		<property name="suffix" value=".jsp"/>
	 	</bean>
	 	那么返回的路径就是 /WEB-INF/data2.jsp
	 	前台接收使用User类型的全小写字符串接收${user}
	 	@ModelAttribute和@RequestMapping一起使用可以修改前台接收参数的名称将user修改成u
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/data2")
	@ModelAttribute("u")
	public User data2() throws Exception {
		User u = new User();
		return u;
	}

	/**
	 * 返回值是String类型
	 * 返回的路径是 前缀+返回值+后缀 (/WEB-INF/data2.jsp)
	 * 如果我们要访问到webapp下的页面我们就要去除前缀和后缀的信息
	 * return "redirect:/index.jsp" 表示重定向到index.jsp页面 url地址会发现变化msg也会出现在地址栏上
	 * return "forward:/index.jsp" 表示请求转发到index.jsp页面
	 * @param model 作用是用来设置参数值 返回给前台 作用就跟ModelAndView差不多。但是不能设置view的名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/data3")
	public String data3(Model model) throws Exception {
		model.addAttribute("msg", "测试返回类型");
		return "redirect:/index.jsp";
	}

	@RequestMapping("/data4")
	@ResponseBody
	public String data4() throws Exception {
		return "{\"msg\" : \"曹尼玛\"}";
	}

}
