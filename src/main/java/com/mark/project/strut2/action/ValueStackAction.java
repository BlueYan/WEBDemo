package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

/**
 * Created by Administrator on 2017/7/10.
 *
 * 演示value stack
 */
public class ValueStackAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		//获取ValueStack对象
		ValueStack valueStack1 = (ValueStack) ServletActionContext.getRequest().getAttribute("struts.valueStack");
		//该方法与上面的方法等价
		ValueStack valueStack2 = (ValueStack) ServletActionContext.getRequest().getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);
		//利用ActionContext获取
		ValueStack valueStack3 = ActionContext.getContext().getValueStack();

		/**
		 * valueStack的root区域是一个list结构,栈数据结构的存储方式.
		 * 所以操作方式就等同于操作栈一样.
		 */
		valueStack3.getRoot().add(0, "Mark");
		valueStack3.getRoot().push(18);
		valueStack3.set("sex", "male"); //set方法可以指定keyName

		/**
		 * valueStack的context区域是一个map的数据结构
		 * 所以操作方式就等同于操作map一样
		 */
		//获取valueStack的context对象
		valueStack3.getContext().put("name", "Tom");
		//使用ActionContext来存储
		ActionContext.getContext().put("age", 18);

		return SUCCESS;
	}
}
