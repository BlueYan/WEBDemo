package com.mark.project.springDemo.day01.domain;

/**
 * Created by Mark_Yan on 2017/8/1.
 */
public class HelloWorld {

	private String name;
	//提供set方法
	public void setName(String name) {
		this.name = name;
	}

	public void say() {
		System.out.println("hello " + name);
	}

}
