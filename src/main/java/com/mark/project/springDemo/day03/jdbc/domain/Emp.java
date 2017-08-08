package com.mark.project.springDemo.day03.jdbc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
@Setter
@Getter
@ToString
public class Emp {

	private Long id;

	private String name;

	private BigDecimal salary;

	private Date hireDate;

	private Long deptId;

	public Emp() {
	}

	public Emp(String name, BigDecimal salary, Date hireDate) {
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
	}
}
