package com.mark.project.springDemo.day02.aopTest.dao;

import com.mark.project.springDemo.day02.aopTest.domain.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark_Yan on 2017/8/7.
 */

public interface IProductDAO {

	void save(Product p);

	void update(Product p);

}
