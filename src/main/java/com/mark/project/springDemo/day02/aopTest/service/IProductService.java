package com.mark.project.springDemo.day02.aopTest.service;

import com.mark.project.springDemo.day02.aopTest.domain.Product;
import org.springframework.stereotype.Service;

/**
 * Created by Mark_Yan on 2017/8/7.
 */

public interface IProductService {

	void save(Product p);

	void update(Product p);

}
