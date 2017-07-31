package com.mark.project.hibernateDemo.combination_relation.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Mark_Yan on 2017/7/25.
 * 单据明细item实体类
 */
@Getter
@Setter
public class SaleBillItem {

	private Long id;

	private String name;

	private BigDecimal price;

	private Integer num;

	private SaleBill saleBill;

}
