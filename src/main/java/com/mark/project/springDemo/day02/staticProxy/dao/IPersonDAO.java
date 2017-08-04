package com.mark.project.springDemo.day02.staticProxy.dao;

import com.mark.project.springDemo.day02.staticProxy.domain.Person;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * PersonDAO层
 *
 */
public interface IPersonDAO {

	public void save(Person person);

	public void update(Person person);

}
