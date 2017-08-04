package com.mark.project.springDemo.day02.staticProxy.dao.impl;

import com.mark.project.springDemo.day02.staticProxy.dao.IPersonDAO;
import com.mark.project.springDemo.day02.staticProxy.domain.Person;

/**
 * Created by Mark_Yan on 2017/8/4.
 */
public class PersonDAOImpl implements IPersonDAO {

	public void save(Person person) {
		System.out.println("保存用户: " + person.getName());
	}

	public void update(Person person) {
		System.out.println("更新用户: " + person.getName());
	}
}
