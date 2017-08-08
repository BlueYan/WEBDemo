package com.mark.project.springDemo.day02.aopTest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
@Setter
@Getter
public class Product {

	private String name;

	public Product(String name) {
		this.name = name;
	}
}
