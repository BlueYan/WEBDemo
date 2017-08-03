package com.mark.project.springDemo.day01.domain;

/**
 * Created by Mark_Yan on 2017/8/2.
 *
 * 实例工厂方法
 * 提供一个获取实例的方法
 */
public class SomeBeanInstanceFactory {

	public SomeBean getSomeBean() {
		return new SomeBean();
	}

}
