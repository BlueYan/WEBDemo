package com.mark.project.springDemo.day02.staticProxy.service.impl;

import com.mark.project.springDemo.day02.staticProxy.dao.IPersonDAO;
import com.mark.project.springDemo.day02.staticProxy.domain.Person;
import com.mark.project.springDemo.day02.staticProxy.service.IPersonService;
import com.mark.project.util.TransactionManager;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * Person业务层
 * 主要就是处理业务
 *
 */
public class PersonServiceImpl implements IPersonService {
	//提供setter方法 用来注入
	@Setter
	private IPersonDAO personDAO;

	public void save(Person person) {
		try {
			System.out.println("真实对象开始保存用户业务开始");
			personDAO.save(person);
		} catch (Exception e) {

		}
	}

	public void update(Person person) {
		System.out.println("真实对象开始更新用户业务开始");
		personDAO.update(person);
		throw new RuntimeException("异常出现");
	}
}
