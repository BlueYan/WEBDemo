package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2017/7/13.
 * 检验测试
 */
public class ValidateAction extends ActionSupport {


	public String list() {
		System.out.println("list...");
		return NONE;
	}

	@Override
	public void validate() {
		//编写校验规则
		System.out.println("validate...");
	}
}
