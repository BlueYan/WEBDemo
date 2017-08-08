package com.mark.project.springDemo.day02.aopTest.service.impl;

import com.mark.project.springDemo.day02.aopTest.dao.IProductDAO;
import com.mark.project.springDemo.day02.aopTest.domain.Product;
import com.mark.project.springDemo.day02.aopTest.service.IProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
//Service组件注解要放在实现类上
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDAO productDAO;

	public void save(Product p) {
		System.out.println("Service层保存产品操作");
		productDAO.save(p);
	}

	public void update(Product p) {
		System.out.println("Service层更新产品操作");
		productDAO.update(p);
	}
}
