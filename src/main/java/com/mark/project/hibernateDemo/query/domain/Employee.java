package com.mark.project.hibernateDemo.query.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/26.
 * 员工实体类
 * 多个员工属于一个部门 many2one的映射
	 CREATE TABLE `t_employee` (
	 `id` bigint(20) NOT NULL AUTO_INCREMENT,
	 `name` varchar(255) DEFAULT NULL,
	 `salary` decimal(8,2) DEFAULT NULL,
	 `hireDate` date DEFAULT NULL,
	 `dept_id` bigint(20) DEFAULT NULL,
	 PRIMARY KEY (`id`),
	 UNIQUE KEY `t_employee_id_uindex` (`id`),
	 KEY `dept_id` (`dept_id`),
	 CONSTRAINT `dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Getter
@Setter
public class Employee {

	private Long id;

	private String name;

	private BigDecimal salary;

	private Date hireDate;

	private Department dept;

	private List<Project> projects = new ArrayList<Project>();

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", salary=" + salary +
				", hireDate=" + hireDate +
				'}';
	}
}
