package com.mark.project.springDemo.day02.aopTest.dao.impl;

import com.mark.project.springDemo.day02.aopTest.dao.IProductDAO;
import com.mark.project.springDemo.day02.aopTest.domain.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
//表示dao组件 使用注解方式进行配置 Repository注解要放在实现类上
@Repository("productDAO")
public class ProductDAOImpl implements IProductDAO {

	public void save(Product p) {
		System.out.println("DAO层保存产品: " + p.getName());
	}

	public void update(Product p) {
		System.out.println("DAO层更新产品: " + p.getName());
	}
}
