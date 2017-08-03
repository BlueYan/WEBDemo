package com.mark.project.springDemo.day01.domain;

/**
 * Created by Mark_Yan on 2017/8/2.
 *
 * 静态工厂模式
 */
public class SomeBeanFactory {

	private SomeBeanFactory() {}

	public static SomeBean getSomeBean() {
		return new SomeBean();
	}

}
