package com.mark.project.MyBatisDemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/25.
 *
 * 供应商
 *
 */
@Setter
@Getter
@ToString(exclude = "products")
public class Supplier {

	private Long id;

	private String name;

	//由供应商来维护与商品多对多的关系
	private List<Product> products = new ArrayList<Product>();

}
