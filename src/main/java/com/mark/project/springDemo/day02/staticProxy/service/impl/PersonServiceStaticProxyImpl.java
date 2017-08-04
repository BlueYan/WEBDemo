package com.mark.project.springDemo.day02.staticProxy.service.impl;

import com.mark.project.springDemo.day02.staticProxy.domain.Person;
import com.mark.project.springDemo.day02.staticProxy.service.IPersonService;
import com.mark.project.util.TransactionManager;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * 代理对象也需要实现对应的真实对象的接口
 *
 */
public class PersonServiceStaticProxyImpl implements IPersonService {

	@Setter
	TransactionManager txManager; //事务操作
	@Setter
	IPersonService service; //真实对象的接口

	public void save(Person person) {
		System.out.println("代理对象开始做的事情....");
		txManager.openTransaction(); //代理所做的事情
		service.save(person); //真实对象所做的事情
		txManager.closeTransaction(); //代理所做的事情
	}

	public void update(Person person) {
		try {
			System.out.println("代理对象开始做的事情....");
			txManager.openTransaction();
			service.update(person);
			throw new RuntimeException();
		} catch (Exception e) {
			txManager.rollbackTransaction();
		}
	}
}
