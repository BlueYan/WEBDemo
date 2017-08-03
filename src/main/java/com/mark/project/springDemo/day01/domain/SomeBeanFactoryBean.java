package com.mark.project.springDemo.day01.domain;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Mark_Yan on 2017/8/2.
 */
public class SomeBeanFactoryBean implements FactoryBean<SomeBean> {
	public SomeBean getObject() throws Exception {
		return new SomeBean();
	}

	public Class<SomeBean> getObjectType() {
		return SomeBean.class;
	}

	public boolean isSingleton() {
		return false;
	}
}
