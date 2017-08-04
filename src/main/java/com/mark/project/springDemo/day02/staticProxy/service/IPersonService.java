package com.mark.project.springDemo.day02.staticProxy.service;

import com.mark.project.springDemo.day02.staticProxy.domain.Person;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * Person业务层
 *
 */
public interface IPersonService {

	void save(Person person);

	void update(Person person);

}
