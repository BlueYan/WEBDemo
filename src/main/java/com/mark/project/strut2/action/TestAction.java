package com.mark.project.strut2.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2017/7/3.
 */
public class TestAction extends ActionSupport {


	public String list() {
		System.out.println("list");

		return NONE;
	}


	public String update() {
		System.out.println("update");

		return NONE;
	}

	public String save() {
		System.out.println("save");
		return NONE;
	}

	public String delete() {
		System.out.println("delete");
		return NONE;
	}

}
