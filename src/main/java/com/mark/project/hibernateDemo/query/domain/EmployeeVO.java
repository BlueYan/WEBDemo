package com.mark.project.hibernateDemo.query.domain;

import java.math.BigDecimal;

/**
 * Created by Mark_Yan on 2017/7/28.
 */
public class EmployeeVO {

	private Long id;

	private String name;

	private BigDecimal salary;

	private Long deptId;

	private String deptName;

	//提供构造器
	public EmployeeVO(Long id, String name, BigDecimal salary, Long deptId, String deptName) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.deptId = deptId;
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "EmployeeVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", salary=" + salary +
				", deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				'}';
	}
}
